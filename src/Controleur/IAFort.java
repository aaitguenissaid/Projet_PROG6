package Controleur;

import Global.Configuration;
import Modele.Jeu;
import Structures.Mouvement;
import Structures.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class IAFort extends IA{
    private final int INF = 1000;
    int nombreCoup;
    HashMap<String, Integer> configurationDejaVu;
    int initDepartI, initDepartJ, initArriveeI, initArriveeJ, initNbPionsDepl;
    int hauteur;

    public IAFort(Jeu j, int joueur, String nom) {
        super(j, joueur);
        nombreCoup = 0;
        hauteur = Integer.parseInt(Configuration.instance().get(nom));
    }

    public IAFort(Jeu j, int joueur, int hauteur) {
        super(j, joueur);
        nombreCoup = 0;
        this.hauteur = hauteur;
    }

    private boolean estFeuille(){
        ArrayList<Point> casePeutBouger = jeu.trouveCasePeutBouger();
        return ((casePeutBouger == null) || (casePeutBouger.size() == 0));
    }

    private int evaluerNoeud(){
        int nombre0 = 0;
        int nombre1 = 0;
        for (int i = 0; i < jeu.getTaille().h; i++) {
            for (int j = 0; j < jeu.getTaille().l; j++) {
                if ((jeu.estCaseValide(new Point(i, j))) && (jeu.getCase(i, j).nbPions() > 0)) {
                    ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(i, j);
                    if ((voisinsAccessible == null) || (voisinsAccessible.size() == 0)) {
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
        if ( (horizon == 0)||(estFeuille())){
            int value;
            String key = HashCode();
            if (configurationDejaVu.containsKey(key)){
                value = configurationDejaVu.get(key);
            } else {
                value = evaluerNoeud();
                configurationDejaVu.put(key, value);
            }
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
                String key = HashCode();
                if (configurationDejaVu.containsKey(key)){
                    valeur = configurationDejaVu.get(key);
                } else {
                    valeur = minmaxAlphaBeta(alpha, beta, false, horizon-1);
                }
                maxValeur = Math.max(maxValeur, valeur);
                alpha = Math.max(alpha, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            String key = HashCode();
            configurationDejaVu.put(key, maxValeur);
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

                String key = HashCode();
                if (configurationDejaVu.containsKey(key)){
                    valeur = configurationDejaVu.get(key);
                } else {
                    valeur = minmaxAlphaBeta(alpha, beta, true, horizon - 1);
                }
                minValeur = Math.min(minValeur, valeur);
                beta = Math.min(beta, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            String key = HashCode();
            configurationDejaVu.put(key, minValeur);
            return minValeur;
        }
    }

    private Mouvement trouverGagnant(int horizon){
        Mouvement gagnant = null;
        int max = -INF;
        int courant;
        IterateurCoup it = new IterateurCoup(this);
        while (it.aProchain()){
            Mouvement configSuivant = it.prochain();
            Point depart = configSuivant.getDepart();
            Point arrvee = configSuivant.getArrivee();
            int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
            jeu.bouge(depart, arrvee, false);
            courant = minmaxAlphaBeta( -INF, INF,false, horizon);
            if (courant > max){
                max = courant;
                gagnant = configSuivant;
            }
            jeu.annule(depart, arrvee, hauteur);
        }
        return gagnant;
    }


    @Override
    public Mouvement joue() {
        initDepartI=jeu.lastDepI;
        initDepartJ=jeu.lastDepJ;
        initArriveeI=jeu.lastArrI;
        initArriveeJ=jeu.lastArrJ;
        initNbPionsDepl=jeu.getNbPionsDepl();
        long start = System.currentTimeMillis();
        Mouvement resultat;
        configurationDejaVu = new HashMap<>();
        resultat = trouverGagnant(hauteur - 1);
        nombreCoup++;
        long end=System.currentTimeMillis();
        jeu.lastDepI=initDepartI;
        jeu.lastDepJ=initDepartJ;
        jeu.lastArrI=initArriveeI;
        jeu.lastArrJ=initArriveeJ;
        jeu.setNbPionsDepl(initNbPionsDepl);
        return resultat;
    }
}
