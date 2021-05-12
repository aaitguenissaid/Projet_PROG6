package Modele;

import Structures.*;

public class Jeu {
    byte [][] grille;
    Size taille;
    Joueur j1,j2;
    Jeu(int l,int h){
        taille =new Size(l,h);
        grille = new byte[l][h];
        init_grille();
    }
    void init_grille(){
        int centerL = taille.l/2+1;
        int centerH = taille.h/2+1;

    }
}
