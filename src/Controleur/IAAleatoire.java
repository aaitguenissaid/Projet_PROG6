package Controleur;

import Structures.Mouvement;

import Modele.Jeu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IAAleatoire extends IA{
    Random r1, r2;

    public IAAleatoire(Jeu j, int joueur) {
        super(j, joueur);
        r1 = new Random();
        r2 = new Random();
    }

    @Override
    public Mouvement joue() {
        ArrayList<Point> casePeutBouger = super.trouveCasePeutBouger();
        int d = r1.nextInt(casePeutBouger.size());
        Point depart = casePeutBouger.get(d);
        ArrayList<Point> voisinsAccessible = super.trouveVosinsAccessible(depart);
        int a;
        if (voisinsAccessible.size() >1){
            a = r2.nextInt(voisinsAccessible.size());
        } else {
            a = 0;
        }
        Point arrivee = voisinsAccessible.get(a);
        return new Mouvement(depart, arrivee);
    }
}
