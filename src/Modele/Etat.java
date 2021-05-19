package Modele;

import Structures.Iterateur;
import Structures.SequenceListe;
import Structures.Size;

import java.awt.*;
import java.io.PrintWriter;

public class Etat {
    public static final int DEFAULT_SIZE = 9;
    Case[][] grille;
    Size taille;
    int tour;

    public Etat(Case [][]grille, int tour) {
        this.taille = new Size(grille.length,grille.length);
        this.grille = new Case[this.taille.h][this.taille.l];
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                this.grille[i][j] = (Case) grille[i][j].clone();
            }
        }
        this.tour = tour;
    }

    public Etat() {
        taille = new Size(DEFAULT_SIZE, DEFAULT_SIZE);
        grille = new Case[this.taille.h][this.taille.l];
    }

    public String toString() {
        String s = "";
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                s += grille[i][j];
            }
            s += "\n";
        }
        return s;
    }

    public Case[][] getCopieGrille() {
        Case[][] copy_grid = new Case[this.taille.h][this.taille.l];
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                copy_grid[i][j] = (Case) grille[i][j].clone();
            }
        }
        return copy_grid;
    }

    public Size getTaille() {
        return this.taille;
    }

    public int getTour() {
        return this.tour;
    }

    public Case getCase(int i , int j){
        return grille[i][j];
    }

    public void setTour(int tour) {
        this.tour = tour;
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

    protected boolean coordonneesValides(Point pt) {
        return (pt.x>=0 && pt.x<taille.h && pt.y>=0 && pt.y<taille.l);
    }

    public boolean estCaseValide(Point pt) {
        return coordonneesValides(pt) && grille[pt.x][pt.y].estValide();
    }

    public static void printGrille(PrintWriter out, Case [][]grille, int h, int l) {
        for(int i=0; i<h; i++) {
            for(int j=0; j<l; j++) {
                out.print(grille[i][j].estValide());
                if(grille[i][j].estValide()) {
                    out.print(",");
                    Iterateur<Pion> it = grille[i][j].getPions().iterateur();
                    while (it.aProchain()) {
                        out.print(it.prochain().getCouleur());
                    }
                }
                if(j!=l-1) out.print("/");
            }
            if(i!=h-1) out.print(":");
        }
        out.println();
    }

    public static Case[][] readGrille(String strGrille, int h, int l) {
        Case [][] grille = new Case[h][l];
        String[] lignes = strGrille.split(":");
        for(int i=0; i<lignes.length && i<h; i++) {
            String[] cases = lignes[i].split("/");
            for(int j=0; j<cases.length && j<l; j++) {
                String[] attributs = cases[j].split(",");
                boolean valide = Boolean.parseBoolean(attributs[0]);
                grille[i][j] = new Case(valide);
                if(valide && attributs.length==1) {
                    grille[i][j].pions = new SequenceListe<>();
                    grille[i][j].tete = null;
                } else if(valide) {
                    SequenceListe<Pion> seq = new SequenceListe<>();
                    for(char c : attributs[1].toCharArray()) {
                        seq.insereTete(new Pion(Character.getNumericValue(c)));
                    }
                    grille[i][j].pions = seq;
                    grille[i][j].tete = (seq.taille()>0) ? seq.teteReturn() : null;
                }
            }
        }
        return grille;
    }

    public void print(PrintWriter out) {
        printGrille(out, grille, taille.h, taille.l);
        out.println(tour);
    }
}
