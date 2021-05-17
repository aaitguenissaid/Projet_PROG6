package Controleur;


import Modele.Jeu;
import Structures.Mouvement;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;
import Vue.PionComponent;

import java.awt.*;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceUtilisateur jeuint;
    boolean shouldMove;
    int startCaseI,startCaseJ;
    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
    }
    @Override
    public void taper(int x, int y) {
        System.out.println("Test passed");
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