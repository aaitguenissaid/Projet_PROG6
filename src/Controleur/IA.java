package Controleur;

import Modele.Jeu;
import Structures.Mouvement;


import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    Jeu jeu;
    int joueur;

    public IA (Jeu j, int joueur) {
        jeu = j;
        this.joueur = joueur;

    }

    /* Prends le valeur d'une hashtable et rends la couleur de sommet d'un pile */
    public byte getCouleur(byte value){
        return ((value & (1<<7)) == 0) ? (byte) 0 : (byte)1;
    }

    /* Prends le valeur d'une hashtable et rends la hauteur de sommet d'un pile */
    public byte getHauteur(byte value){
        return (byte) (value & (~(1 << 7)));
    }

    /* Prends la couleur d'un sommet et sa hauteur, rends la valeur d'un hashtable */
    public byte setValue(byte couleur, byte hauteur){
        return (byte)((couleur << 7) | hauteur);
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

    public String hashCode(byte[] configuration) {
        String resultat = codage(configuration[0]);;
        for (int i = 1; i < configuration.length; i++){
            resultat += codage(configuration[i]);
        }
        return resultat;
    }

    public String HashCode(){
        String resultat = null;
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if (jeu.getCase(i, j).nbPions() == 0)
                    resultat += Integer.toString(0);
                else {
                    int hauteur = jeu.getCase(i, j).nbPions();
                    if (jeu.getCase(i, j).getTete().getCouleur() == 0)
                        resultat += Integer.toString(hauteur);
                     else
                        resultat += codage(hauteur);
                    }
                }
        }
        return resultat;
    }

    /* Prends un Point et code le numéro de case */
    public int coderPoint(int i, int j){
        int resultat = 0;
        switch (i){
            case 0:
                resultat = j - 4;
                break;
            case 1:
                resultat = j + 1;
                break;
            case 2:
                resultat = j + 7;
                break;
            case 3:
                resultat = j + 14;
                break;
            case 4:
                if (j < 4){
                    resultat = j + 20;
                } else if(j > 4){
                    resultat = j + 19;
                }
                break;
            case 5:
                resultat = j + 25;
                break;
            case 6:
                resultat = j + 32;
                break;
            case 7:
                resultat = j + 38;
                break;
            case 8:
                resultat = j + 43;
                break;
        }
        return resultat;
    }

    /* Prends un numéro de case et décode en Point */
    public Point decoderIndice(int indice){
        int i = 0;
        int j = 0;
        if (indice <= 1){
            i = 0;
            j = indice + 4;
        } else if ((indice >=2)&&(indice <= 6)){
            i = 1;
            j = indice - 1;
        } else if ((indice >=7)&&(indice <= 13)){
            i = 2;
            j = indice - 7;
        } else if ((indice >=14)&&(indice <= 20)){
            i = 3;
            j = indice - 14;
        } else if ((indice >=21)&&(indice <= 23)){
            i = 4;
            j = indice - 20;
        } else if ((indice >=24)&&(indice <= 26)){
            i = 4;
            j = indice - 19;
        } else if ((indice >=27)&&(indice <= 33)){
            i = 5;
            j = indice - 25;
        } else if ((indice >=34)&&(indice <= 40)){
            i = 6;
            j = indice - 32;
        } else if ((indice >=41)&&(indice <= 45)){
            i = 7;
            j = indice - 38;
        } else if ((indice >=46)&&(indice <= 47)){
            i = 8;
            j = indice - 43;
        }
        return new Point(i, j);
    }

    public abstract Mouvement joue();
}
