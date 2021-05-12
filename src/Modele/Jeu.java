package Modele;

import Structures.*;

public class Jeu {
    byte [][] grille;
    Size taille;
    Joueur j1,j2;
    public Jeu(int l, int h){
        taille =new Size(l,h);
        grille = new byte[l][h];
        init_grille();
    }
    void init_grille(){

    }
}
