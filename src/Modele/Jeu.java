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
        grille = new Case[taille.h][taille.l];
        init_grille();
    }
    private void init_grille(){
        int centerL = taille.l/2;
        int centerH = taille.h/2;
        ArrayList<Point> emptyPoints = Configuration.instance().getEmptyPoints();
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j< taille.l; j++) {
                if(emptyPoints.contains(new Point(i,j)) || (i==centerH && j==centerL)) {
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
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                if(grille[i][j].estValide() && grille[i][j].aTeteBlanche())
                    sum++;
            }
        }
        return sum;
    }

    public int nbPileNoire() {
        int sum = 0;
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
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
        if(estCaseValide(depart)
            && estCaseValide(arrive)
            && (arrive.x==depart.x+1 || arrive.x==depart.x-1 || arrive.x==depart.x)
            && (arrive.y==depart.y+1 || arrive.y==depart.y-1 || arrive.y==depart.y)
            && (depart.x!=arrive.x || depart.y!=arrive.y) ) {
                return (grille[depart.x][depart.y].nbPions()>0)
                    && (grille[depart.x][depart.y].nbPions() + grille[arrive.x][arrive.y].nbPions()) <= 5;
        }
        return false;
    }

    private boolean coordonneesValides(Point pt) {
        return (pt.x>=0 && pt.x<taille.h && pt.y>=0 && pt.y<taille.l);
    }

    public boolean estCaseValide(Point pt) {
        return coordonneesValides(pt) && grille[pt.x][pt.y].estValide();
    }

    public String toString() {
        String s = "";
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                s += grille[i][j].toString();
            }
            s += "\n";
        }
        return s;
    }
    public Case getCase(int i , int j){
        return grille[i][j];
    }

}
