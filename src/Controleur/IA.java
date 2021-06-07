package Controleur;

import Modele.Jeu;
import Structures.Mouvement;
import Structures.Point;

import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    Jeu jeu;
    int joueur;

    public IA (Jeu j, int joueur) {
        jeu = j;
        this.joueur = joueur;

    }

    private String codage(int hauteur){
        if (hauteur == 1)
            return "A";
        else if (hauteur == 2)
            return "B";
        else if (hauteur == 3)
            return "C";
        else if (hauteur == 4)
            return "D";
        else
            return "E";
    }


    public String HashCode(){
        StringBuffer resultat = new StringBuffer();
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if (jeu.estCaseValide(new Point(i, j))){
                    int hauteur = jeu.getCase(i, j).nbPions();
                    if (hauteur == 0)
                        resultat.append(0);
                    else {
                        if (jeu.getCase(i, j).getTete().getCouleur() == 0)
                            resultat.append(hauteur);
                        else
                            resultat.append(codage(hauteur));
                    }
                }
            }
        }
        return resultat.toString();
    }

    public abstract Mouvement joue();
}
