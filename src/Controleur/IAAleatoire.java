package Controleur;

import Structures.Mouvement;

import Modele.Jeu;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IAAleatoire extends IA{
    Random r1, r2;

    public IAAleatoire(Jeu j, boolean joueur) {
        super(j, joueur);
        r1 = new Random();
        r2 = new Random();
    }

    @Override
    public Mouvement joue() {
        super.trouveCasePeutBouger();
        int d = r1.nextInt(casePeutBouger.size());
        Point depart = casePeutBouger.get(d);
        ArrayList<Point> voisinsAccessible = super.trouveVosinsAccessible(depart);
        int a = r2.nextInt(voisinsAccessible.size());
        Point arrivee = voisinsAccessible.get(a);
        return new Mouvement(depart, arrivee);
    }
}
