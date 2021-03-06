package Controleur;

import Structures.Mouvement;

import Modele.Jeu;
import Structures.Point;
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
        ArrayList<Point> casePeutBouger = jeu.trouveCasePeutBouger();
        if (casePeutBouger.size() > 0){
            int d = r1.nextInt(casePeutBouger.size());
            Point depart = casePeutBouger.get(d);
            int h = depart.x;
            int l = depart.y;
            ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(h, l);
            int a;
            if (voisinsAccessible.size() >1){
                a = r2.nextInt(voisinsAccessible.size());
            } else {
                a = 0;
            }
            Point arrivee = voisinsAccessible.get(a);
            return new Mouvement(depart, arrivee);
        }
        return null;
    }
}
