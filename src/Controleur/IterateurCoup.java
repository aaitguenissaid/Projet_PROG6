package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

public class IterateurCoup {
    int indice;
    int indiceVoisin;
    IAFort ia;
    ArrayList<Point> casePeutBouger;
    int peutBougerLength;
    Jeu j;


    public IterateurCoup(IAFort ia){
        int indice = 0;
        int indiceVoisin = 0;
        this.ia = ia;
        j = ia.jeu;
        casePeutBouger = j.trouveCasePeutBouger();
        peutBougerLength = casePeutBouger.size();
    }

    public boolean aProchain() {
        if(indice < peutBougerLength - 1)
            return true;
        else if (indice == peutBougerLength - 1){
            int h = casePeutBouger.get(indice).x;
            int l = casePeutBouger.get(indice).y;
            ArrayList<Point> voisinsAcc = j.voisinsAccessibles(h, l);
            return  (indiceVoisin < voisinsAcc.size()) ? true : false;
        }
        else
            return false;
    }

    public Mouvement prochain() {
        int h = casePeutBouger.get(indice).x;
        int l = casePeutBouger.get(indice).y;
        ArrayList<Point> voisinsAcc = j.voisinsAccessibles(h, l);
        Point depart = casePeutBouger.get(indice);
        Point arrivee = voisinsAcc.get(indiceVoisin);

        if (indiceVoisin+1 < voisinsAcc.size()){
            indiceVoisin++;
        } else {
            indiceVoisin = 0;
            indice++;
        }
        return new Mouvement(depart, arrivee);
    }

}
