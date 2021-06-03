package Controleur;


import Global.Configuration;
import Modele.*;
import Structures.Mouvement;
import Structures.SequenceListe;
import Vue.*;

import javax.swing.*;
import java.awt.*;

public class ControleurMediateur implements CollecteurEvenements {
    public static final int MODE_JvsJ=0;
    public static final int MODE_JvsIA=1;
    public static final int MODE_IAvsIA=2;
    Jeu jeu;

    //Attributs pour les IAs
    int mode; //0=JvsJ, 1=JvsIA, 2=IAvsIA
    IA IAAffrontement, IA_1, IA_2;
    Joueur JoueurIA;

    //Attributs pour les animations
    Timer time;
    SequenceListe<Animation> animations;

    //Attributs pour l'interface
    InterfaceUtilisateur jeuint;
    boolean shouldMove;
    int startCaseI,startCaseJ;
    PaletteDeCouleurs palette;
    EffetsSonores sonCtrl;
    Classement classement;
    boolean suggestion=false;



    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
        animations = new SequenceListe<>();
        palette=new PaletteDeCouleurs();
        sonCtrl = new EffetsSonores();
        classement = new Classement(jeu);
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
        System.out.print("Start :");
        System.out.println("x-"+m.getDepart().x+" y-"+m.getDepart().y);
        System.out.print("End :");
        System.out.println("x-"+m.getArrivee().x+" y-"+m.getArrivee().y);
        jeuint.metAJour();
        sonCtrl.moveEnd();

        boolean animationRunning = time!=null && time.isRunning();
        if(animationRunning) System.out.println("Animation running, ignoring clic.");
        else if(!jeu.estFini()) {
            stop_historique();
            //Si on la navigation n'est pas activée (ou qu'elle vient d'être désactivée)
            if(!jeu.getHistorique().isNavigationOn()) {
                if (bouge(m.getDepart(), m.getArrivee())) {
                    if(mode==MODE_JvsIA) {
                        lancerAnimationCoupIA(IAAffrontement);
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
        if (jeu.estFini()){
            time.stop();
        } else {
            if(animations!=null && !animations.estVide()) {
                Animation a = animations.extraitTete();
                a.ticTac();
            } else {
                time.stop();
                animations = new SequenceListe<>();
            }
        }
    }

    @Override
    public void jouer_en_local(){
        mode=0;
        jeu.setNomJ1(Configuration.instance().get(Configuration.PSEUDO_J1));
        jeu.setNomJ2(Configuration.instance().get(Configuration.PSEUDO_J2));
        jeuint.setStatistiques();
        jeuint.setGameScreen();
    }

    @Override
    public void jouer_contre_ia() {
        mode=1;

        //Création de l'IA
        int id_ia = (Boolean.parseBoolean(Configuration.instance().get(Configuration.IA_COMMENCE))) ? Jeu.COULEUR1 : Jeu.COULEUR2;

        String nom_ia = Configuration.instance().get(Configuration.IA_AFFRONTEMENT);
        IAAffrontement = construireIA(nom_ia, id_ia);

        //Mise à jour du jeu
        JoueurIA = (id_ia==0) ? jeu.getJ1() : jeu.getJ2();
        JoueurIA.setNom(nom_ia);

        //Mise à jour de l'interface
        jeuint.setGameScreen();
        jeuint.setStatistiques();

        //Si c'est son tour, l'IA joue un premier coup
        if(id_ia==jeu.getTour()) {
            lancerAnimationCoupIA(IAAffrontement);
        }
    }

    @Override
    public void lancer_demo() {
        mode=2;

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

        lancerAnimationCoupIA(IA_1);
    }

    private IA construireIA(String nom_ia, int id_ia) {
        return switch (nom_ia) {
            case "IAAleatoire" -> new IAAleatoire(jeu, id_ia);
            case "IABasique" -> new IABasique(jeu, id_ia);
            case "IAFort" -> new IAFort(jeu, id_ia);
            default -> new IAAleatoire(jeu, id_ia);
        };
    }

    private void lancerAnimationCoupIA(IA ia) {
        lancerAnimationCoupIA(ia, 0);
    }

    private void lancerAnimationCoupIA(IA ia, int id) {
        if(time!=null && time.isRunning()) time.stop();
        Mouvement toClic = ia.joue();
        animations.insereTete((new AnimationJoueurIA(1, jeuint, toClic, this, id)));
        time = new Timer(1500, new AdaptateurTemps(this));
        time.start();
    }

    public void lancerAnimationAdversaire(int id) {
        IA iaAutre = (id==1) ? IA_2 : IA_1;
        lancerAnimationCoupIA(iaAutre, (id==0) ? 1 : 0);
    }

    public void reprendre_une_partie() {
        Object[] parties = PartiesSauvegardees.getNomsParties();
        String titre = "Choix d'une partie";
        String description = "Veuillez choisir une partie parmis celles sauvegardées.";
        String nom_partie = (String) jeuint.choisirItem(titre, description, parties, JOptionPane.QUESTION_MESSAGE);

        if(nom_partie!=null) {
            Jeu j = PartiesSauvegardees.recupererPartie(nom_partie);
            if (j != null) {
                this.jeu = j;
                jeuint.setJeu(j);
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
        String titre = "Choix du nom";
        String description = "Veuillez entrer un nom pour votre partie.";
        String nom = (String) jeuint.choisirItem(titre, description, null, JOptionPane.PLAIN_MESSAGE);
        if(nom!=null && nom.length()>2) {
            PartiesSauvegardees.enregistrerPartie(nom.replace(" ","_"), this.jeu);
        }
    }

    public boolean bouge(Point depart, Point arrivee) {
        boolean ret = jeu.bouge(depart, arrivee);
        jeuint.metAJour();
        System.out.println("Jeu fini : " + jeu.estFini());
        jeuint.setStatistiques();
        if(jeu.estFini()) {
            classement.enregistrerScore(jeu.getNomJ1(), jeu.getNomJ2(), jeu.quiAGagnee());
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
        System.out.println("Nombre de pions déplacés : " + jeu.getNbPionsDepl());
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
    }

    @Override
    public void parametres() {
        jeuint.setParametres();
    }

    @Override
    public void mainmenu() {
        if(mode!=MODE_JvsJ && time!=null && time.isRunning()) {
            time.stop();
        }
        jeuint.setMainMenu();
    }

    @Override
    public void reagles() { jeuint.setReagles();}

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
    }

    @Override
    public boolean next_historique() {
        if(jeu.getHistorique().aPrecedent()) {
            jeu.getHistorique().precedent();
            jeuint.metAJour();
            return true;
        }
        return false;
    }

    @Override
    public void play_pause_historique() {
        animations.insereTete(new AnimationHistorique(1, this));
        time = new Timer(1500, new AdaptateurTemps(this));
        time.start();
    }

    @Override
    public boolean previous_historique() {
        if(jeu.getHistorique().aSuivant()) {
            jeu.getHistorique().suivant();
            jeuint.metAJour();
            return true;
        }
        return false;
    }

    @Override
    public void first_historique() {
        jeu.getHistorique().atteindreFinHistorique();
        jeuint.metAJour();
    }

    @Override
    public void relancerPartie() {
        if(jeu.estFini()) {
            jeu.relancerPartie();
            //TODO relancer le timer des IAs selon le mode de jeu
            jeuint.metAJour();
            if(mode==MODE_IAvsIA) {
                lancerAnimationCoupIA(IA_1, 0);
            } else if(mode==MODE_JvsIA && Boolean.parseBoolean(Configuration.instance().get(Configuration.IA_COMMENCE))) {
                lancerAnimationCoupIA(IAAffrontement, 0);
            }
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
                jeu.relancerPartie();
                jeuint.metAJour();
            }
            classement.enregistrerScore(jeu.getNomJ1(), jeu.getNomJ2(), (jeu.getTour()==Jeu.COULEUR1) ? 2 : 1);
        }
    }
    public void deisabel_enabel_son(){
        sonCtrl.deisabel_enabel_son();
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

    public boolean suggestion(){
        return suggestion;
    }
    public Mouvement suggestionMouvement(){
        IABasique iaB = new IABasique(jeu, jeu.getTour());
        return iaB.joue();
    }



}