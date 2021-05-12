package Modele;

import Global.Configuration;
import Structures.*;

import java.awt.*;
import java.util.ArrayList;

public class Jeu {
    Case[][] grille;
    Size taille;
    Joueur j1,j2;

    public Jeu(){
        taille = new Size(9,9);
        grille = new Case[taille.l][taille.h];
        init_grille();
    }
    private void init_grille(){
        int centerL = taille.l/2;
        int centerH = taille.h/2;
        ArrayList<Point> emptyPoints = Configuration.instance().getEmptyPoints();
        for(int i=0; i<taille.l; i++) {
            for(int j=0; j< taille.h; j++) {
                if(emptyPoints.contains(new Point(i,j)) || (i==centerL && j==centerH)) {
                    grille[i][j] = new Case(false);
                } else {
                    if(i%2==0)
                        grille[i][j] = new Case(true, new Pion(j%2));
                    else
                        grille[i][j] = new Case(true, new Pion((j+1)%2));
                }
                System.out.print(grille[i][j]);
            }
            System.out.println();
        }
    }

    public SequenceListe<Pion> contenuCase(int i, int j) {
        if(!grille[i][j].estValide()) return null;
        return grille[i][j].getPions();
    }

    public Size getTaille() {
        return this.taille;
    }

    public boolean estFini() {
        //TODO
        return false;
    }

    public int nbPileBlanche() {
        int sum = 0;
        for(int i=0; i<taille.l; i++) {
            for (int j = 0; j < taille.h; j++) {
                if(grille[i][j].estValide() && grille[i][j].aTeteBlanche())
                    sum++;
            }
        }
        return sum;
    }

    public int nbPileNoire() {
        int sum = 0;
        for(int i=0; i<taille.l; i++) {
            for (int j = 0; j < taille.h; j++) {
                if(grille[i][j].estValide() && grille[i][j].aTeteNoire())
                    sum++;
            }
        }
        return sum;
    }

    public boolean bouge(Point depart, Point arrive) {
        if(!estMouvementPossible(depart, arrive)) return false;
        SequenceListe<Pion> pions = grille[depart.x][depart.y].getPions();
        grille[depart.x][depart.y].supprimePions();
        grille[arrive.x][arrive.y].ajoutePions(pions);
        return true;
    }

    public boolean estMouvementPossible(Point depart, Point arrive) {
        //TODO
        return false;
    }

    public boolean estCaseValide(int i, int j) {
        return grille[i][j].estValide();
    }

}
