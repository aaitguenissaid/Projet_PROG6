package Modele;

import Structures.Joueur;
import Structures.Size;

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

    }
}
