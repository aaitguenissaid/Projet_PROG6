package Controleur;

import Modele.Jeu;
import Structures.Mouvement;


import java.util.ArrayList;

abstract class IA {
    public Jeu jeu;
    public ArrayList<Integer> voisinsAccessible;

    public IA (Jeu j) {
        jeu = j;
        voisinsAccessible = new ArrayList<>();
    }

    public int hashCode(int l, int h){
        return l*10 + h;
    }

    public int hashCodeVerL(int code){
        return code/10;
    }

    public int hashCodeVerH(int code){
        return code%10;
    }


    public boolean peutEtreBouger(int hauteurDepart, int hauteurArrivee){
        if (hauteurDepart + hauteurArrivee <= 5)
            return true;
        else
            return false;
    }


    public void trouveAccessible(int l, int h){
/*      Attendre le class jeu
        int hauteurDepart = jeu.;
        int hauteurArrivee;
       if (h-1 >= 0){        //(l, h-1)

        }
        if (h+1 <= ){         //(l, h+1)

        }

        if (l-1 >= 0){
            if (){            //(l-1, h)

            }
            if (h-1 >= 0){    //(l-1, h-1)

            }
            if (h+1 < jeu.){  //(l-1, h+1)

            }
        }
        if (l+1 < ){
            if (){            //(l+1, h)

            }
            if (h-1 >= 0){    //(l+1, h-1)

            }
            if (h+1 < jeu.){  //(l+1, h+1)

            }

        }
*/
    }



    public abstract Mouvement joue();
}
