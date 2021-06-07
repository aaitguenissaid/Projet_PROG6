package Controleur;

import Modele.Jeu;
import Structures.Mouvement;
import Structures.Point;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IAFort extends IA{
    private final int INF = 1000;
    int nombreCoup;

    public IAFort(Jeu j, int joueur) {
        super(j, joueur);
        nombreCoup = 0;
    }

    private boolean memeCouleur(int i, int j){
        int couleurDepart = jeu.getCase(i, j).getTete().getCouleur();
        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
        for (int s = 0; s < voisins.size(); s++){
            int x = voisins.get(s).x;
            int y = voisins.get(s).y;
            int couleurArrivee = jeu.getCase(x, y).getTete().getCouleur();
            if (couleurDepart != couleurArrivee)
                return false;
        }
        return true;
    }


    private boolean estFeuille(){
        ArrayList<Point> casePeutBouger = jeu.trouveCasePeutBouger();
        return ((casePeutBouger == null) | (casePeutBouger.size() == 0));
    }

    private int nonJoueur(int j){
        return j == 0 ? 1 : 0;
    }
    private ArrayList<Point> aCouleur(int x, int y, int couleur){
        ArrayList<Point> resultat = new ArrayList<>();
        int c = jeu.getCase(x, y).getTete().getCouleur();
        if (c == couleur)
            resultat.add(new Point(x, y));
        ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(x, y);
        for (int i = 0; i < voisinsAccessible.size(); i++){
            int getX = voisinsAccessible.get(i).x;
            int getY = voisinsAccessible.get(i).y;
            c = jeu.getCase(getX, getY).getTete().getCouleur();
            if (c == couleur)
                resultat.add(voisinsAccessible.get(i));
        }
        return resultat;
    }

    private int evaluerNoeud(){
        int nombre0 = 0;
        int nombre1 = 0;
        for (int i = 0; i < jeu.getTaille().h; i++) {
            for (int j = 0; j < jeu.getTaille().l; j++) {
                if ((jeu.estCaseValide(new Point(i, j))) && (jeu.getCase(i, j).nbPions() > 0)) {
                    ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(i, j);
                    if ((voisinsAccessible == null) | (voisinsAccessible.size() == 0)) {
                        if (jeu.getCase(i, j).getTete().getCouleur() == joueur)
                            nombre0++;
                        else
                            nombre1++;
                    }
                }
            }
        }
        return nombre0 - nombre1;
    }


    private int minmaxAlphaBeta(int alpha, int beta, boolean estMax, int horizon){
        if ( (horizon == 0)|(estFeuille())){
            int value = evaluerNoeud();
            return value;
        }
        IterateurCoup it = new IterateurCoup(this);
        if (estMax){
            int maxValeur = -INF;
            while (it.aProchain()){
                Mouvement m = it.prochain();
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee,false);
                int valeur;
                valeur = minmaxAlphaBeta(alpha, beta, false, horizon-1);
                maxValeur = Math.max(maxValeur, valeur);
                alpha = Math.max(alpha, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            return maxValeur;
        } else {
            int minValeur = INF;
            int noteNoeudPrecedent = evaluerNoeud();
            while (it.aProchain()){
                Mouvement m = it.prochain();
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee, false);
                int valeur;
                int noteNoeudCourant = evaluerNoeud();
                if (noteNoeudCourant < noteNoeudPrecedent){
                    minValeur = noteNoeudCourant;
                    jeu.annule(depart, arrvee, hauteur);
                    continue;
                }
                valeur = minmaxAlphaBeta(alpha, beta, true, horizon - 1);
                minValeur = Math.min(minValeur, valeur);
                beta = Math.min(beta, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            return minValeur;
        }
    }

    private Mouvement trouverGagnant(int horizon){
        Mouvement gagnant = null;
        int max = -INF;
        int courant = -INF;
        IterateurCoup it = new IterateurCoup(this);
        int noteNoeudPrecedent = evaluerNoeud();
        while (it.aProchain()){
            Mouvement configSuivant = it.prochain();
            Point depart = configSuivant.getDepart();
            Point arrvee = configSuivant.getArrivee();
            int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
            jeu.bouge(depart, arrvee, false);
            int noteNoeud = evaluerNoeud();
            if (noteNoeud > noteNoeudPrecedent){
                gagnant = configSuivant;
                jeu.annule(depart, arrvee, hauteur);
                break;
            } else {
                courant = minmaxAlphaBeta( -INF, INF,false, horizon);
                if (courant > max){
                    max = courant;
                    gagnant = configSuivant;
                }
            }
            jeu.annule(depart, arrvee, hauteur);
        }
        return gagnant;
    }


    @Override
    public Mouvement joue() {
        long start = System.currentTimeMillis();
        Mouvement resultat;
        resultat = trouverGagnant(2);
        nombreCoup++;
        long end=System.currentTimeMillis();
        System.out.println("Temps d'exécution： "+(end-start)+"ms");
        return resultat;
    }
}
