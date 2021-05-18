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

    public byte hashCode(int h, int l){
        return (byte) ((h << 4) | l);
    }

    public int hashCodeVerH(byte code){
        return (byte)(((int)code & (0XF << 4)) >> 4);
    }

    public int hashCodeVerL(byte code){
        return (code & 0XF);
    }

    public ArrayList<Point> trouveVosinsAccessible(Point depart){
        ArrayList<Point> voisinsAccessible = new ArrayList<>();
        int h = depart.x;
        int l = depart.y;

        if (h-1 >= 0){        //(h-1, l)
            if (jeu.estMouvementPossible(depart, new Point(h-1, l)))
                voisinsAccessible.add(new Point(h-1, l));
        }
        if (h+1 < jeu.getTaille().h){         //(h+1, l)
            if (jeu.estMouvementPossible(depart, new Point(h+1, l)))
                voisinsAccessible.add(new Point(h+1, l));
        }
        if (l-1 >= 0){
            if (jeu.estMouvementPossible(depart, new Point(h, l-1)))     //(h, l-1)
                voisinsAccessible.add(new Point(h, l-1));
            if (h-1 >= 0){    //(h-1, l-1)
                if (jeu.estMouvementPossible(depart, new Point(h-1, l-1)))
                    voisinsAccessible.add(new Point(h-1, l-1));
            }
            if (h+1 < jeu.getTaille().h){  //(h+1, l-1)
                if (jeu.estMouvementPossible(depart, new Point(h+1, l-1)))
                    voisinsAccessible.add(new Point(h-1, l-1));
            }
        }
        if (l+1 < jeu.getTaille().l){
            if (jeu.estMouvementPossible(depart, new Point(h, l+1)))     //(h, l+1)
                voisinsAccessible.add(new Point(h, l+1));
            if (h-1 >= 0){    //(h-1, l+1)
                if (jeu.estMouvementPossible(depart, new Point(h-1, l+1)))
                    voisinsAccessible.add(new Point(h-1, l+1));
            }
            if (h+1 < jeu.getTaille().h){  //(h+1, l+1)
                if (jeu.estMouvementPossible(depart, new Point(h+1, l+1)))
                    voisinsAccessible.add(new Point(h+1, l+1));
            }
        }
        return voisinsAccessible;
    }

    public ArrayList<Point> trouveCasePeutBouger(){
        ArrayList<Point> casePeutBouger = new ArrayList<>();
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                ArrayList<Point> vosinsAccessible = trouveVosinsAccessible(new Point(i, j));
                if (vosinsAccessible != null){
                    if (vosinsAccessible.size() != 0)
                        casePeutBouger.add(new Point(i, j));
                }
            }
        }
        return casePeutBouger;
    }

    public abstract Mouvement joue();
}
