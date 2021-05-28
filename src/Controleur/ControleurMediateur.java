package Controleur;


import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Modele.PartiesSauvegardees;
import Structures.Mouvement;
import Structures.SequenceListe;
import Vue.*;

import javax.swing.*;
import java.awt.*;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceUtilisateur jeuint;
    boolean shouldMove;
    int startCaseI,startCaseJ;
    boolean activeA, activeB;
    IA IA_A, IA_B;
    Timer time;
    SequenceListe<Animation> animations;
    PaletteDeCouleurs palette;
    EffetsSonores sonCtrl;

    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
        activeA = false;
        activeB = false;
        animations = new SequenceListe<>();
        palette=new PaletteDeCouleurs();
        sonCtrl = new EffetsSonores();
    }


    public void activerJoueurA() {
        if(activeA) {
            activeA = false;
            IA_A = null;
        } else {
            String IA = choisirAI("");
            if (IA != null) {
                activateOne(IA, "A");
                if (!jeu.estFini()){
                    if(jeu.getTour()==0) {
                        Mouvement coup = IA_A.joue();
                        jeu.bouge(coup.getDepart(), coup.getArrivee());
                        jeuint.metAJour();
                    }
                }
            } else {
                System.out.println("Cancelling AI activation for player A.");
            }
        }
    }
    public void activerJoueurB() {
        if(activeB) {
            activeB = false;
            IA_B = null;
        } else {
            String IA = choisirAI("");
            if (IA != null) {
                activateOne(IA, "B");
                if (!jeu.estFini()){
                    if(jeu.getTour()==1) {
                        Mouvement coup = IA_B.joue();
                        jeu.bouge(coup.getDepart(), coup.getArrivee());
                        jeuint.metAJour();
                    }
                }
            } else {
                System.out.println("Cancelling AI activation for player B.");
            }
        }
    }


    public void activateOne(String IAstr, String letter) {
        System.out.println("Activation de l'" + IAstr + " pour le joueur "+letter+".");
        int id;
        if(letter.equals("A")) {
            IA_A = createIA(IAstr, 0);
            id = 1;
            activeA = true;
        }
        else {
            IA_B = createIA(IAstr, 1);
            id = 2;
            activeB = true;
        }
    }

    public IA createIA(String IAstr, int joueur) {
        if(IAstr.equals("IAAleatoire")) {
            return new IAAleatoire(jeu, joueur);
        } else if(IAstr.equals("IAFort")) {
            return new IAFort(jeu, joueur);
        } else if(IAstr.equals("IAFortCoup")) {
            return new IAFortCoup(jeu, joueur);
        } else {
            return new IABasique(jeu, joueur);
        }
    }



    private String choisirAI(String text) {
        Object[] possibilities = {"IAAleatoire", "IABasique", "IAFort", "IAFortCoup"};
        String message = "Choose your AI in the following list.\n" + text ;
        String title = "AI Choice";

        return (String) jeuint.choisirItem(title, message, possibilities, JOptionPane.QUESTION_MESSAGE);
    }





    @Override
    public boolean commande(String c) {
        switch (c) {
            case "ActiveA":
                activerJoueurA();
                break;
            case "ActiveB":
                activerJoueurB();
                break;
            case "fullscreen":
                jeuint.basculePleinEcran();
                break;
            default:
                return false;
        }
        return true;
    }

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
        //System.out.println("Jeu fini : " + jeu.estFini());
        //System.out.println("Tour : " + jeu.getTour());

        boolean animationRunning = time!=null && time.isRunning();
        if(animationRunning) System.out.println("Animation running, ignoring clic.");
        if(!jeu.estFini() && !animationRunning ) {
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
            //Si on la navigation n'est pas activée (ou qu'elle vient d'être désactivée)
            if(!jeu.getHistorique().isNavigationOn()) {
                if (jeu.bouge(m.getDepart(), m.getArrivee())) {
                    jeuint.metAJour();
                    System.out.println("Jeu fini : " + jeu.estFini());
                    Mouvement to_clic = null;
                    if (activeA) {
                        to_clic = IA_A.joue();
                    }
                    if (activeB) {
                        to_clic = IA_B.joue();
                    }
                    if (activeA || activeB) {
                        animations.insereTete(new AnimationJoueurIA(1, jeuint, to_clic));
                        time = new Timer(1500, new AdaptateurTemps(this));
                        time.start();
                    }
                }
            }
        }

    }


    @Override
    public void movePionTo(Point point) {
        if(shouldMove&&(startCaseI<jeu.getTaille().l)&&(startCaseJ<jeu.getTaille().h)){
            PionComponent Pc = jeuint.getAireDeDessin().getPionComponent(startCaseI,startCaseJ);
            Pc.movePile(point.x,point.y);
        }
    }

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
        //Tictac servant pour l'animation qui sépare l'affichage du coup du joueur et l'affichage du coup de l'IA
        if(animations!=null && !animations.estVide()) {
            Animation a = animations.extraitTete();
            a.ticTac();
        } else {
            time.stop();
            animations = new SequenceListe<>();
        }
    }
    public void jouer_en_local(){
        jeuint.setGameScreen();
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

    @Override
    public void parametres() {
        jeuint.setParametres();
    }

    @Override
    public void mainmenu() {
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
        jeu.getHistorique().atteindreFinHistorique();
        jeuint.metAJour();
    }

    @Override
    public void next_historique() {
        if(jeu.getHistorique().aSuivant()) {
            jeu.getHistorique().suivant();
            jeuint.metAJour();
        }
    }

    @Override
    public void stop_historique() {
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
    public void previous_historique() {
        if(jeu.getHistorique().aPrecedent()) {
            jeu.getHistorique().precedent();
            jeuint.metAJour();
        }
    }

    @Override
    public void first_historique() {
        jeu.getHistorique().atteindreDebutHistorique();
        jeuint.metAJour();
    }

    @Override
    public void relancerPartie() {
        if(jeu.estFini()) {
            jeu.relancerPartie();
            jeuint.metAJour();
        }
    }

    @Override
    public void abandonner() {
        if(!jeu.estFini()) {
            jeu.abandonner();
            jeuint.metAJour();
        }
    }
}