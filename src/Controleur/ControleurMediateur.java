package Controleur;


import Modele.Jeu;
import Structures.Mouvement;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;
import Vue.PionComponent;

import javax.swing.*;
import java.awt.*;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceUtilisateur jeuint;
    boolean shouldMove;
    int startCaseI,startCaseJ;
    boolean activeA, activeB, activeAB;
    IA IA_A, IA_B;
    Timer time;
    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
        activeA = false;
        activeB = false;
    }


    public void activerJoueurA() {
        if(activeA) {
            activeA = false;
            IA_A = null;
        } else {
            String IA = choisirAI("");
            if (IA != null) {
                activateOne(IA, "A");
                if (activeB) {
                    //Les deux IA sont actives
                    activateBoth();
                } else {
                    if(jeu.getTour() == 0) {
                        Point clic = IA_A.joue();
                        jeu.clic(clic.x, clic.y);
                        gaufre.metAJour();
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
            if(activeAB) time.stop();
            activeAB = false;
            updatePlayerName("Joueur B", 2);
        } else {
            String IA = choisirAI("");
            if (IA != null) {
                activateOne(IA, "B");
                if (activeA) {
                    //Les deux IA sont actives
                    activateBoth();
                } else {
                    if(jeu.getTour()==2) {
                        Point clic = IA_A.joue();
                        jeu.clic(clic.x, clic.y);
                        gaufre.metAJour();
                    }
                }
            } else {
                System.out.println("Cancelling AI activation for player B.");
            }
        }
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
        jeu.bouge(m.getDepart(),m.getArrivee());
        jeuint.metAJour();
        System.out.println("Jeu fini : " + jeu.estFini());
        System.out.println("Tour : " + jeu.getTour());
    }


    @Override
    public void movePionTo(Point point) {
        if(shouldMove){
            PionComponent Pc = jeuint.getAireDeDessin().getPionComponent(startCaseI,startCaseJ);
            Pc.movePile(point.x,point.y);
        }
    }

    @Override
    public void startMove(int i, int j) {
        jeuint.getAireDeDessin().startMove();
        shouldMove=true;
        startCaseI=i;
        startCaseJ=j;
    }
}