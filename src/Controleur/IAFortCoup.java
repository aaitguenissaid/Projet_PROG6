package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

public class IAFortCoup extends IA{
    private final int INF = 1000;

    public IAFortCoup(Jeu j, int joueur) {
        super(j, joueur);
    }

    private ArrayList<Mouvement> EtapesPossibles(){
        ArrayList<Mouvement> etapesPossibles = new ArrayList<>();
        ArrayList<Point> casePeutBouger = super.trouveCasePeutBouger();
        for (int i = 0; i < casePeutBouger.size(); i++){
            Point depart = casePeutBouger.get(i);
            int h = depart.x;
            int l = depart.y;
            ArrayList<Point> voisinsAccessible = super.voisinsAccessibles(h, l);
            for (int j = 0; j < voisinsAccessible.size(); j++){
                Point arrivee = voisinsAccessible.get(j);
                etapesPossibles.add(new Mouvement(depart, arrivee));
            }
        }
        return etapesPossibles;
    }

    private boolean memeCouleur(Point depart, ArrayList<Point> voisins){
        int i = 0;
        int couleurDepart = jeu.getCase(depart.x, depart.y).getTete().getCouleur();
        while (i < voisins.size()){
            int couleurArrivee = jeu.getCase(voisins.get(i).x, voisins.get(i).y).getTete().getCouleur();
            if (couleurDepart == couleurArrivee)
                i++;
            else
                break;
        }
        return i == voisins.size() ? true : false;
    }


    private boolean estFeuille(){
        ArrayList<Point> casePeutBouger = super.trouveCasePeutBouger();
        if ((casePeutBouger == null) | (casePeutBouger.size() == 0))
            return true;
        else
            return false;
    }

    private int evaluerNoeud(){
        int nombre0 = 0;
        int nombre1 = 0;
        for (int i = 0; i < jeu.getTaille().h; i++) {
            for (int j = 0; j < jeu.getTaille().l; j++) {
                if ((jeu.estCaseValide(new Point(i, j))) && (jeu.getCase(i, j).hauteur() > 0)) {
                    ArrayList<Point> voisinsAccessible = super.voisinsAccessibles(i, j);
                    if ((voisinsAccessible == null) | (voisinsAccessible.size() == 0)) {
                        if (jeu.getCase(i, j).aTeteBlanche())
                            nombre0++;
                        else
                            nombre1++;
                    } else {
                        if (memeCouleur(new Point(i, j), voisinsAccessible))
                            if (jeu.getCase(i, j).aTeteBlanche())
                                nombre0++;
                            else nombre1++;
                    }
                }
            }
        }
        return joueur == 0 ? nombre0 - nombre1 : nombre1 - nombre0;
    }

        /* Fonction minmax alpha beta */
    private int minmaxAlphaBeta(int alpha, int beta, boolean estMax, int horizon){
        if ((estFeuille()) || (horizon == 0))
            return evaluerNoeud();
        if (estMax){
            int maxValeur = -INF;
            ArrayList<Mouvement> etapesSuivant = EtapesPossibles();
            for (int i = 0; i < etapesSuivant.size(); i++){
                Mouvement m = etapesSuivant.get(i);
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).hauteur();
                jeu.bouge(depart, arrvee);
                int valeur = minmaxAlphaBeta(alpha, beta, false, horizon-1);
                jeu.annule(depart, arrvee, hauteur);
                maxValeur = Math.max(maxValeur, valeur);
                alpha = Math.max(alpha, valeur);
                if (beta <= alpha)
                    break;
            }
            return maxValeur;

        } else {
            int minValeur = INF;
            ArrayList<Mouvement> etapesSuivant = EtapesPossibles();
            for (int i = 0; i < etapesSuivant.size(); i++){
                Mouvement m = etapesSuivant.get(i);
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).hauteur();
                jeu.bouge(depart, arrvee);
                int valeur = minmaxAlphaBeta(alpha, beta, true, horizon-1);
                jeu.annule(depart, arrvee, hauteur);
                minValeur = Math.min(minValeur, valeur);
                alpha = Math.max(alpha, valeur);
                if (beta <= alpha)
                    break;
            }
            return minValeur;
        }
    }


    private Mouvement trouverGagnant(ArrayList<Mouvement> configSuivants, int horizon){
        Mouvement gagnant = null;
        int imax = 0;
        int max = -INF;
        for (int i = 0; i < configSuivants.size(); i++){
            Mouvement configSuivant = configSuivants.get(i);
            int courant = minmaxAlphaBeta( -INF, INF,true, horizon);
            if (courant > max){
                max = courant;
                imax = i;
            }
        }
        gagnant = configSuivants.get(imax);
        return gagnant;
    }


    @Override
    public Mouvement joue() {
        ArrayList<Mouvement> configSuivants = EtapesPossibles();
        Mouvement resultat = trouverGagnant(configSuivants, 5);
        return resultat;
    }
}
