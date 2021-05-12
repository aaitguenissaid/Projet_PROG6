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
        int centerL = taille.l/2+1;
        int centerH = taille.h/2+1;
        ArrayList<Point> emptyPoints = Configuration.instance().getEmptyPoints();
        for(int i=0; i<taille.l; i++) {
            for(int j=0; j< taille.h; j++) {
                if(emptyPoints.contains(new Point(i,j))) {
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
    public Size getTaille(){
        return taille;
    }
    public Case  getCase(int i , int j){
        return grille[i][j];
    }
}
