package Controleur;


import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Modele.PartiesSauvegardees;
import Structures.Mouvement;
import Structures.SequenceListe;
import Vue.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        } else {
            return new IABasique(jeu, joueur);
        }
    }



    private String choisirAI(String text) {
        Object[] possibilities = {"IAAleatoire", "IABasique", "IAFort"};
        String message = "Choose your AI in the following list.\n" + text ;
        String title = "AI Choice";

        return (String) JOptionPane.showInputDialog(
                jeuint.getFrame(),
                message,
                title,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "IAAleatoire"
        );
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
//        jeu.bouge(m.getDepart(),m.getArrivee());
        jeuint.metAJour();
        sonCtrl.moveEnd();
        //System.out.println("Jeu fini : " + jeu.estFini());
        //System.out.println("Tour : " + jeu.getTour());

        boolean animationRunning = time!=null && time.isRunning();
        if(animationRunning) System.out.println("Animation running, ignoring clic.");
        if(!jeu.estFini() && !animationRunning ) {
            if(jeu.bouge(m.getDepart(),m.getArrivee())) {
                jeuint.metAJour();
                System.out.println("Jeu fini : " + jeu.estFini());
                Mouvement to_clic = null;
                if (activeA) {
                    to_clic = IA_A.joue();
                }
                if (activeB) {
                    to_clic = IA_B.joue();
                }
                if(activeA || activeB) {
                    animations.insereTete(new AnimationJoueurIA(1, jeuint, to_clic));
                    time = new Timer(1500, new AdaptateurTemps(this));
                    time.start();
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
        String nom_partie = (String) choisirItem(titre, description, parties, JOptionPane.QUESTION_MESSAGE);

        if(nom_partie!=null) {
            Jeu j = Jeu.recupererPartie(this, nom_partie);
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
        String nom = (String) choisirItem(titre, description, null, JOptionPane.PLAIN_MESSAGE);
        if(nom!=null && nom.length()>2) {
            this.jeu.enregistrerPartie(nom.replace(" ","_"));
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

    @Override
    public boolean valideAction(String titre, String description, String choix_valider, String choix_annuler) {
        Object[] options = {choix_annuler, choix_valider};
        int n = JOptionPane.showOptionDialog(
                jeuint.getFrame(),
                description,
                titre,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return n==1;
    }

    public Object choisirItem(String titre, String description, Object[] items, int message) {
        return JOptionPane.showInputDialog(
                jeuint.getFrame(),
                description,
                titre,
                message,
                null,
                items,
                (items!=null && items.length>0) ? items[0] : null
        );
    }
    public PaletteDeCouleurs getPalette(){
        return palette;
    }

    @Override
    public void revalidateInterface() {
        jeuint.revalidateInterface();
    }
}