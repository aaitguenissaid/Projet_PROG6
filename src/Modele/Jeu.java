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
                if(emptyPoints.contains(new Point(i,j))) {
                    grille[i][j] = new Case(false);
                } else if((i==centerH && j==centerL)) {
                    grille[i][j] = new Case(true);
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

    public Size getTaille() {
        return this.taille;
    }

    public boolean estFini() {
        //On recherche les piles que l'on peut déplacer
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                //S'il est envisageable de déplacer la tour
                if(grille[i][j].estValide() && grille[i][j].nbPions()>0 && grille[i][j].nbPions()<5) {
                    int nbPions = grille[i][j].nbPions();
                    ArrayList<Point> voisins = new ArrayList<>();
                    voisins.add(new Point(i,j-1));
                    voisins.add(new Point(i-1,j-1));
                    voisins.add(new Point(i-1,j));
                    voisins.add(new Point(i-1,j+1));
                    voisins.add(new Point(i,j+1));
                    voisins.add(new Point(i+1,j+1));
                    voisins.add(new Point(i+1,j));
                    voisins.add(new Point(i+1,j-1));
                    //On parcourt tous les voisins
                    for(int k=0; k<voisins.size(); k++) {
                        Point voisin = voisins.get(k);
                        if(estCaseValide(voisin)) {
                            int nbPionsVoisin = grille[voisin.x][voisin.y].nbPions();
                            if (nbPionsVoisin > 0 && (nbPionsVoisin + nbPions) < 5) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
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
        if(estCaseValide(depart)        //On vérifie les coordonnées
            && estCaseValide(arrive)    //On vérifie les coordonnées
            && (arrive.x==depart.x+1 || arrive.x==depart.x-1 || arrive.x==depart.x) //On vérifie qu'on se déplace d'une case
            && (arrive.y==depart.y+1 || arrive.y==depart.y-1 || arrive.y==depart.y) //On vérifie qu'on se déplace d'une case
            && (depart.x!=arrive.x || depart.y!=arrive.y) ) { //On vérifie qu'on ne reste pas sur la même case
                return (grille[depart.x][depart.y].nbPions()>0) // On vérifie qu'on ne déplace pas une tour vide
                    && (grille[arrive.x][arrive.y].nbPions()>0)
                    && (grille[depart.x][depart.y].nbPions() + grille[arrive.x][arrive.y].nbPions()) <= 5; //On vérifie qu'il n'y aura pas trop de pions sur la tour
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
