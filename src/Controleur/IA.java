package Controleur;

import Modele.Jeu;
import Structures.Mouvement;


import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    Jeu jeu;
    boolean joueur;
    ArrayList<Point> casePeutBouger;

    public IA (Jeu j, boolean joueur) {
        jeu = j;
        this.joueur = joueur;
        casePeutBouger = new ArrayList<>();
    }

    public byte hashCode(int l, int h){
        return (byte) ((l << 4) | h);
    }

    public int hashCodeVerL(byte code){
        return (code >> 4);
    }

    public int hashCodeVerH(byte code){
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
        if (h+1 < 9){         //(h+1, l)
            if (jeu.estMouvementPossible(depart, new Point(h+1, l)))
                voisinsAccessible.add(new Point(h+1, l));
        }
        if (l-1 >= 0){
            if (jeu.estMouvementPossible(depart, new Point(h, l-1)))     //(h, l-1)
                voisinsAccessible.add(new Point(h, l-1));
            if (h-1 >= 0){    //(h-1, l-1)
                if (jeu.estMouvementPossible(depart, new Point(h-1, l-1)))     //(h-1, l-1)
                    voisinsAccessible.add(new Point(h-1, l-1));
            }
            if (h+1 < 9){  //(h+1, l-1)
                if (jeu.estMouvementPossible(depart, new Point(h+1, l-1)))     //(h+1, l-1)
                    voisinsAccessible.add(new Point(h-1, l-1));
            }
        }
        if (l+1 < 9){
            if (jeu.estMouvementPossible(depart, new Point(h, l+1)))     //(h, l+1)
                voisinsAccessible.add(new Point(h, l+1));
            if (h-1 >= 0){    //(h-1, l+1)
                if (jeu.estMouvementPossible(depart, new Point(h-1, l+1)))     //(h, l+1)
                    voisinsAccessible.add(new Point(h-1, l+1));
            }
            if (h+1 < 9){  //(h+1, l+1)
                if (jeu.estMouvementPossible(depart, new Point(h+1, l+1)))     //(h, l+1)
                    voisinsAccessible.add(new Point(h+1, l+1));
            }
        }
        return voisinsAccessible;
    }

    public void trouveCasePeutBouger(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                ArrayList<Point> vosinsAccessible = trouveVosinsAccessible(new Point(i, j));
                if (vosinsAccessible != null){
                    if (vosinsAccessible.size() != 0)
                        casePeutBouger.add(new Point(i, j));
                }
            }
        }
    }

    public abstract Mouvement joue();
}
