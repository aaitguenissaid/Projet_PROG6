package Modele;

import Structures.Size;

public class Etat {
    Case[][] grille;
    Size taille;

    public Etat(Case [][]grille) {
        this.taille = new Size(grille.length,grille.length);
        this.grille = new Case[this.taille.h][this.taille.h];
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                this.grille[i][j] = (Case) grille[i][j].clone();
            }
        }
    }

    public String toString() {
        String s = "";
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                s += grille[i][j];
                this.grille[i][j] = (Case) grille[i][j].clone();
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
}
