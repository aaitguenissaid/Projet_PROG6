package Controleur;


import Global.Configuration;
import Modele.*;
import Structures.Iterateur;
import Structures.Mouvement;
import Structures.SequenceListe;
import Vue.*;

import javax.swing.*;
import Structures.Point;
import java.util.ArrayList;

import java.util.Arrays;

public class ControleurMediateur implements CollecteurEvenements {
    public static final int MODE_JvsJ=0;
    public static final int MODE_JvsIA=1;
    public static final int MODE_IAvsIA=2;
    Jeu jeu;

    //Attributs pour les IAs
    int mode; //0=JvsJ, 1=JvsIA, 2=IAvsIA
    IA IAAffrontement, IA_1, IA_2;
    Joueur JoueurIA;
    HistoriqueTimeLine histAffControl;

    //Attributs pour les animations
    Timer time;
    SequenceListe<Animation> animations;
    boolean navigationHistoriqueRunning;

    //Attributs pour l'interface
    InterfaceUtilisateur jeuint;
    boolean shouldMove;
    int startCaseI,startCaseJ;
    PaletteDeCouleurs palette;
    EffetsSonores sonCtrl;
    Classement classement;
    boolean suggestion=false;

    public Classement getClassement(){
        return classement;
    }
    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
        animations = new SequenceListe<>();
        palette=new PaletteDeCouleurs();
        sonCtrl = new EffetsSonores();
        classement = new Classement(jeu);
        navigationHistoriqueRunning=false;
    }





    @Override
    public boolean commande(String c) {
        switch (c) {
            case "ActiveA":
                System.out.println("Cette fonctionnalité n'est plus disponible");
                break;
            case "ActiveB":
                System.out.println("Cette fonctionnalité n'est plus disponible");
                break;
            case "fullscreen":
                jeuint.basculePleinEcran();
                break;
            default:
                return false;
        }
        return true;
    }

    //Fin d'un mouvement (l'utilisateur relache la souris)
    @Override
    public void mouvementFini(Mouvement m) {
        shouldMove = false;
        jeuint.getAireDeDessin().stopMove();
//        System.out.print("Start :");
//        System.out.println("x-"+m.getDepart().x+" y-"+m.getDepart().y);
//        System.out.print("End :");
///        System.out.println("x-"+m.getArrivee().x+" y-"+m.getArrivee().y);

        boolean animationRunning = time!=null && time.isRunning();
        if(animationRunning) System.out.println("Animation running, ignoring clic.");
        else if(!jeu.estFini()) {
            stop_historique();
            //Si on la navigation n'est pas activée (ou qu'elle vient d'être désactivée)
            if(!jeu.getHistorique().isNavigationOn()) {
                if (bouge(m.getDepart(), m.getArrivee())) {
                    jeuint.metAJour();
                    if(mode==MODE_JvsIA && jeu.getTour()==JoueurIA.getId()) {
                        lancerAnimationCoupIA(IAAffrontement, JoueurIA.getId());
                    }
                }
            }
        }

    }


    //Mouvement de la pile de pions qui suit le curseur, le mouvement n'est pas terminé
    @Override
    public void movePionTo(Point point) {
        if(shouldMove&&(startCaseI<jeu.getTaille().l)&&(startCaseJ<jeu.getTaille().h)){
            PionComponent Pc = jeuint.getAireDeDessin().getPionComponent(startCaseI,startCaseJ);
            Pc.movePile(point.x,point.y);
        }
    }

    //Démarrage d'un mouvement (on presse la souris sur la case de départ)
    @Override
    public void startMove(int i, int j) {
        sonCtrl.moveStart();
        jeuint.getAireDeDessin().startMove();
        shouldMove=true;
        startCaseI=i;
        startCaseJ=j;
    }

    @Override
    public void ticTac() {
        if (jeu.estFini() && !navigationHistoriqueRunning){
            time.stop();
        } else {
            if(animations!=null && !animations.estVide()) {
                Animation a = animations.extraitTete();
                if(a.getClass()!=AnimationJoueurIA.class || (a.getClass()==AnimationJoueurIA.class && !jeu.getHistorique().isNavigationOn())) {
                    a.ticTac();
                } else {
                    animations.insereTete(a);
                }
            } else {
                time.stop();
                animations = new SequenceListe<>();
            }
        }
    }

    @Override
    public void jouer_en_local(){
        mode=MODE_JvsJ;
        jeu.setNomJ1(Configuration.instance().get(Configuration.PSEUDO_J1));
        jeu.setNomJ2(Configuration.instance().get(Configuration.PSEUDO_J2));
        jeuint.setStatistiques();
        jeuint.setGameScreen();
    }

    @Override
    public void jouer_contre_ia() {
        mode=MODE_JvsIA;

        //Création de l'IA
        int id_ia = (Boolean.parseBoolean(Configuration.instance().get(Configuration.IA_COMMENCE))) ? Jeu.COULEUR1 : Jeu.COULEUR2;

        String nom_ia = Configuration.instance().get(Configuration.IA_AFFRONTEMENT);
        IAAffrontement = construireIA(nom_ia, id_ia);

        //Mise à jour du jeu
        JoueurIA = (id_ia==Jeu.COULEUR1) ? jeu.getJ1() : jeu.getJ2();
        JoueurIA.setNom(nom_ia);
        Joueur vraiJoueur = (id_ia==Jeu.COULEUR1) ? jeu.getJ2() : jeu.getJ1();
        String pseudoID = (id_ia==Jeu.COULEUR1) ? Configuration.PSEUDO_J2 : Configuration.PSEUDO_J1;
        vraiJoueur.setNom(Configuration.instance().get(pseudoID));

        //Mise à jour de l'interface
        jeuint.setGameScreen();
        jeuint.setStatistiques();

        //Si c'est son tour, l'IA joue un premier coup
        if(id_ia==jeu.getTour()) {
            lancerAnimationCoupIA(IAAffrontement, JoueurIA.getId());
        }
    }

    @Override
    public void lancer_demo() {
        mode=MODE_IAvsIA;
        jeu =new Jeu();
        jeuint.setJeu(jeu);
        //Création des IAs
        String nom_ia1 = Configuration.instance().get(Configuration.IA_1);
        IA_1 = construireIA(nom_ia1, Jeu.COULEUR1);
        String nom_ia2 = Configuration.instance().get(Configuration.IA_2);
        IA_2 = construireIA(nom_ia2, Jeu.COULEUR2);

        //Mise à jour du jeu
        jeu.getJ1().setNom(nom_ia1);
        jeu.getJ2().setNom(nom_ia2);

        //Mise à jour de l'interface
        jeuint.setGameScreen();
        jeuint.setStatistiques();

        lancerAnimationCoupIA(IA_1, Jeu.COULEUR1);
    }

    private IA construireIA(String nom_ia, int id_ia) {
        String nom="";
        if(mode==MODE_IAvsIA) {
            if(id_ia==Jeu.COULEUR1) {
                nom = Configuration.HAUTEUR_IA1;
            } else {
                nom = Configuration.HAUTEUR_IA2;
            }
        } else if(mode==MODE_JvsIA) {
            nom = Configuration.HAUTEUR_IA_AFFRONTEMENT;
        }

        switch (nom_ia) {
            case "IAAleatoire":
                return new IAAleatoire(jeu, id_ia);
            case "IABasique":
                return new IABasique(jeu, id_ia);
            case "IAFort":
                return new IAFort(jeu, id_ia, nom);
            default:
                return new IAAleatoire(jeu, id_ia);
        }
    }

    private void lancerAnimationCoupIA(IA ia, int id) {
        if(time!=null && time.isRunning()) time.stop();
        Mouvement toClic = ia.joue();
        animations.insereTete((new AnimationJoueurIA(1, jeuint, toClic, this, id)));
        time = new Timer(1500, new AdaptateurTemps(this));
        time.start();
        jeuint.setStatistiques();
    }

    public void lancerAnimationAdversaire(int id) {
        IA iaAutre = (id==1) ? IA_2 : IA_1;
        lancerAnimationCoupIA(iaAutre, (id==Jeu.COULEUR1) ? Jeu.COULEUR2 : Jeu.COULEUR1);
    }

    public void reprendre_une_partie() {
        Object[] parties = PartiesSauvegardees.getNomsParties();
        String titre = "Choix d'une partie";
        String description = "Veuillez choisir une partie parmis celles sauvegardées.";
        String nom_partie = (String) jeuint.choisirItem(titre, description, parties, JOptionPane.QUESTION_MESSAGE);

        if(nom_partie!=null) {
            Jeu j = PartiesSauvegardees.recupererPartie(nom_partie, this);
            if (j != null) {
                this.jeu = j;
                jeuint.setJeu(j);

                //Récupération des IAs
                String [] IA_names = Configuration.instance().lis("IA_names").split(",");

                java.util.List<String> list = new ArrayList<>();
                for (String IA_name : IA_names) {
                    list.add(IA_name);
                }
                boolean j1EstIA = list.contains(j.getNomJ1());

                java.util.List<String> result = new ArrayList<>();
                for (String IA_name : IA_names) {
                    result.add(IA_name);
                }
                boolean j2EstIA = result.contains(j.getNomJ2());

                if(j1EstIA && j2EstIA) {
                    mode = MODE_IAvsIA;
                    IA_1 = construireIA(jeu.getNomJ1(), Jeu.COULEUR1);
                    IA_2 = construireIA(jeu.getNomJ2(), Jeu.COULEUR2);
                } else if(j1EstIA || j2EstIA) {
                    mode = MODE_JvsIA;
                    JoueurIA = (j1EstIA) ? jeu.getJ1() : jeu.getJ2();
                    IAAffrontement = construireIA(JoueurIA.getNom(), JoueurIA.getId());
                } else {
                    mode = MODE_JvsJ;
                }

                lancerPartie();
                jeuint.setGameScreen();
            } else {
                JOptionPane.showMessageDialog(jeuint.getFrame(),
                        "Désolé, la partie "+nom_partie+" n'existe plus",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void enregistrer_la_partie() {
        String nom = null;
        do {
            String titre = "Choix du nom";
            String description = "Veuillez entrer un nom pour votre partie.";
            nom = (String) jeuint.choisirItem(titre, description, null, JOptionPane.PLAIN_MESSAGE);
            if (nom != null && nom.length() > 0) {
                PartiesSauvegardees.enregistrerPartie(nom.replace(" ", "_"), this.jeu);
            }
        } while(
                (nom==null || nom.length()<=0)
                && jeuint.valideAction(
                        "Partie non enregistrée",
                        "Partie non enregistrée. Voulez-vous enregistrer votre partie ?",
                        "Enregistrer",
                        "Ne pas enregistrer"));
    }

    public boolean bouge(Point depart, Point arrivee) {
        boolean ret = jeu.bouge(depart, arrivee);
        jeuint.metAJour();
        jeuint.setStatistiques();
        sonCtrl.moveEnd();
        if(jeu.estFini()) {
            classement.enregistrerScore(jeu.getNomJ1(), jeu.getNomJ2(), jeu.quiAGagnee());
            jeuint.revalidateInterface();
            if(Boolean.parseBoolean(Configuration.instance().get(Configuration.RELANCE_AUTOMATIQUE))) {
                //Indiquer qui a gagné
                String commentaire = "La partie est finie.";
                int gagnant = jeu.quiAGagnee();

                if(gagnant==0) commentaire += " C'est une égalité.";
                else if(gagnant==1) commentaire += " Le gagnant est " + jeu.getNomJ1() + ".";
                else commentaire += " Le gagnant est " + jeu.getNomJ2() + ".";

                jeuint.informer(commentaire);

                //Relancer la partie
                relancerPartie();
            }
        }
        //System.out.println("Nombre de pions déplacés : " + jeu.getNbPionsDepl());
        suggestion=false;
        return ret;
    }

    private void stop_historique() {
        if(jeu.getHistorique().isNavigationOn()) {
            //#### Demander une validation à l'utilisateur pour retourner en arrière dans l'historique ####
            String titre = "Validation navigation historique";
            String description = "Attention, vous êtes sur le point de retourner à un état antérieur de la partie.\n"
                    +"Si vous n'avez pas enregistré votre partie, certains coups risquent d'être perdus.";
            String choix_valide = "Continuer";
            String choix_annule = "Annuler";
            if(jeuint.valideAction(titre, description, choix_valide, choix_annule)) {
                jeu.getHistorique().validerNavigation();
            }
        }
        histAffControl.repaint();
    }

    @Override
    public void parametres() {
        jeuint.setParametres();
    }

    @Override
    public void mainmenu() {
        if(jeuint.getPageActuelle().equals("GAMESCREEN")) {
            animations = new SequenceListe<>();
            if (mode != MODE_JvsJ && time != null && time.isRunning()) {
                time.stop();
            }
            if(jeu.estPartieNonSauvegardee) {
                if (jeuint.valideAction(
                        "Enregistrement Partie",
                        "Souhatez-vous enregistrer la partie en cours avant de quitter ?",
                        "Enregistrer",
                        "Ne pas enregistrer")) {
                    enregistrer_la_partie();
                }
            }
            jeu = new Jeu();
            jeuint.setJeu(jeu);
        }
        jeuint.setMainMenu();
    }

    @Override
    public void reagles() { jeuint.setReagles();}

    @Override
    public void classement() { jeuint.setClassement(); }
    public PaletteDeCouleurs getPalette(){
        return palette;
    }

    @Override
    public void revalidateInterface() {
        jeuint.revalidateInterface();
    }

    @Override
    public void reaglesBack() {
        jeuint.reaglesBack();
    }

    @Override
    public void last_historique() {
        jeu.getHistorique().atteindreDebutHistorique();
        jeuint.metAJour();
        jeuint.setStatistiques();
    }

    @Override
    public boolean next_historique() {
        if(jeu.getHistorique().aPrecedent()) {
            jeu.getHistorique().precedent();
            jeuint.metAJour();
            jeuint.setStatistiques();
            return true;
        }
        return false;
    }

    @Override
    public void play_pause_historique() {
        if(navigationHistoriqueRunning) {
            navigationHistoriqueRunning=false;
            Iterateur<Animation> it = animations.iterateur();
            while (it.aProchain()) {
                if(it.prochain() instanceof AnimationHistorique)
                    it.supprime();
            }
            playBoutonHistorique();
        } else {
            navigationHistoriqueRunning=true;
            animations.insereTete(new AnimationHistorique(1, this));
            time = new Timer(1500, new AdaptateurTemps(this));
            time.start();
            pauseBoutonHistorique();
        }
    }

    public void playBoutonHistorique() {
        jeuint.playBoutonHistorique();
    }

    public void pauseBoutonHistorique() {
        jeuint.pauseBoutonHistorique();
    }

    @Override
    public boolean previous_historique() {
        if(jeu.getHistorique().aSuivant()) {
            jeu.getHistorique().suivant();
            jeuint.metAJour();
            jeuint.setStatistiques();
            return true;
        }
        return false;
    }

    @Override
    public void first_historique() {
        jeu.getHistorique().atteindreFinHistorique();
        jeuint.metAJour();
        jeuint.setStatistiques();
    }

    @Override
    public void relancerPartie() {
        if(jeu.estFini()) {
            jeu.relancerPartie();
            lancerPartie();
        }
    }
    private void lancerPartie() {
        animations = new SequenceListe<>();
        jeuint.metAJour();
        jeuint.setStatistiques();
        if(mode==MODE_IAvsIA) {
            lancerAnimationCoupIA(IA_1, 0);
        } else if(mode==MODE_JvsIA && jeu.getTour()==IAAffrontement.joueur) {
            lancerAnimationCoupIA(IAAffrontement, 0);
        }
    }

    @Override
    public void abandonner() {
        if(!jeu.estFini()) {
            String titre = "Abandon";
            String description = "Vous vous apprêtez à abandonner une partie.\n Si vous abandonnez, vous allez perdre la partie.";
            String choix_valide = "Continuer";
            String choix_annule = "Annuler";
            if(jeuint.valideAction(titre, description, choix_valide, choix_annule)) {
                jeu.partieAbandonnee=true;
                classement.enregistrerScore(jeu.getNomJ1(), jeu.getNomJ2(), (jeu.getTour()==Jeu.COULEUR1) ? 2 : 1);
                jeuint.revalidateInterface();
                if(Boolean.parseBoolean(Configuration.instance().get(Configuration.RELANCE_AUTOMATIQUE))) {
                    String nom_gagnant = (jeu.getTour()==Jeu.COULEUR1) ? jeu.getNomJ2() : jeu.getNomJ1();
                    jeuint.informer("Le joueur " + nom_gagnant + " a gagné !");
                    jeu.relancerPartie();
                } else {

                }
                jeuint.metAJour();
            }
        }
    }
    public void disable_enable_son(){
        sonCtrl.disable_enable_son();
    }
    public boolean getSonState(){
        return sonCtrl.getSonState();
    }
    public void demanderSuggestion(){
        jeuint.getAireDeDessin().repaint();
        suggestion = true;
        jeuint.getAireDeDessin().repaint();
    }

    @Override
    public void setNomJ1(String text) {
        if(jeu.setNomJ1(text))
            Configuration.instance().set(Configuration.PSEUDO_J1, text);
        jeuint.setStatistiques();
    }

    @Override
    public void setNomJ2(String text) {
        if(jeu.setNomJ2(text))
            Configuration.instance().set(Configuration.PSEUDO_J2, text);
        jeuint.setStatistiques();
    }

    @Override
    public void inverserJoueurs() {
        String tmp = Configuration.instance().get(Configuration.PSEUDO_J1);
        Configuration.instance().set(Configuration.PSEUDO_J1, Configuration.instance().get(Configuration.PSEUDO_J2));
        Configuration.instance().set(Configuration.PSEUDO_J2, tmp);
    }
/*
    @Override
    public void animatePion(Point depart, Point arrive) {
        jeuint.getAireDeDessin().animatePion(depart.x,depart.y,arrive.x,arrive.y);
        int i,j;
        i=jeuint.getAireDeDessin().paddingW+jeuint.getAireDeDessin().largeurCase*depart.x+jeuint.getAireDeDessin().largeurCase/2;
        j=jeuint.getAireDeDessin().paddingH+jeuint.getAireDeDessin().hauteurCase*depart.y+jeuint.getAireDeDessin().hauteurCase/2;
        while((i!=jeuint.getAireDeDessin().paddingW+jeuint.getAireDeDessin().largeurCase*arrive.x+jeuint.getAireDeDessin().largeurCase/2)&&(j!=jeuint.getAireDeDessin().paddingH+jeuint.getAireDeDessin().hauteurCase*arrive.y+jeuint.getAireDeDessin().hauteurCase/2)){
            if(i>arrive.x){
                i--;
            }else if (i<arrive.x){
                i++;
            }
            if(j>arrive.y){
                j--;
            }else if (j<arrive.y){
                j++;
            }
            System.out.println("i:"+i+" j:"+j);
            jeuint.getAireDeDessin().tab[depart.x][depart.y].movePile(i,j);
        }
    }
*/
    public boolean suggestion(){
        return suggestion;
    }
    public Mouvement suggestionMouvement(){
        IAFort iaF = new IAFort(jeu, jeu.getTour(), 3);
        return iaF.joue();
    }

    public EffetsSonores getEffetsSonores(){
        return sonCtrl;
    }
    public Jeu getJeu(){
        return jeu;
    }

    public void setHistAffControl(HistoriqueTimeLine h){
        histAffControl = h;
    }

}