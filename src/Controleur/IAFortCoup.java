package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IAFortCoup extends IA{
    private final int INF = 1000;
    HashMap<String, Integer> configurationDejaVu;

    public IAFortCoup(Jeu j, int joueur) {
        super(j, joueur);

    }


    /* Prends le valeur d'une hashtable et rends la couleur de sommet d'un pile */
    private byte getCouleur(byte value){
        return ((value & (1<<7)) == 0) ? (byte) 0 : (byte)1;
    }

    /* Prends le valeur d'une hashtable et rends la hauteur de sommet d'un pile */
    private byte getHauteur(byte value){
        return (byte) (value & (~(1 << 7)));
    }

    /* Prends la couleur d'un sommet et sa hauteur, rends la valeur d'un hashtable */
    private byte setValue(byte couleur, byte hauteur){
        return (byte)((couleur << 7) | hauteur);
    }

    private String codage(byte value){
        int hauteur = getHauteur(value);
        if (getCouleur(value) == 0){
            return Integer.toString(hauteur);
        } else {
            if (hauteur == 1)
                return "A";
            else if (hauteur == 2)
                return "B";
            else if (hauteur == 3)
                return "C";
            else if (hauteur == 4)
                return "D";
            else
                return "E";
            }
    }

    public String hashCode(byte[] configuration) {
        String resultat = codage(configuration[0]);;
        for (int i = 1; i < configuration.length; i++){
            resultat += codage(configuration[i]);
        }
        return resultat;
    }

    /* Prends un Point et code le numéro de case */
    private int coderPoint(int i, int j){
        int resultat = 0;
        switch (i){
            case 0:
                resultat = j - 4;
                break;
            case 1:
                resultat = j + 1;
                break;
            case 2:
                resultat = j + 7;
                break;
            case 3:
                resultat = j + 14;
                break;
            case 4:
                if (j < 4){
                    resultat = j + 20;
                } else if(j > 4){
                    resultat = j + 19;
                }
                break;
            case 5:
                resultat = j + 25;
                break;
            case 6:
                resultat = j + 32;
                break;
            case 7:
                resultat = j + 38;
                break;
            case 8:
                resultat = j + 43;
                break;
        }
        return resultat;
    }

    private ArrayList<Mouvement> EtapesPossibles(){
        ArrayList<Mouvement> etapesPossibles = new ArrayList<>();
        ArrayList<Point> casePeutBouger = jeu.trouveCasePeutBouger();
        for (int i = 0; i < casePeutBouger.size(); i++){
            Point depart = casePeutBouger.get(i);
            int h = depart.x;
            int l = depart.y;
            ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(h, l);
            for (int j = 0; j < voisinsAccessible.size(); j++){
                Point arrivee = voisinsAccessible.get(j);
                etapesPossibles.add(new Mouvement(depart, arrivee));
            }
        }
        return etapesPossibles;
    }
    /*
    private Iterable<Mouvement> EtapesPossible(){

    }*/


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
        ArrayList<Point> casePeutBouger = jeu.trouveCasePeutBouger();
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
                if ((jeu.estCaseValide(new Point(i, j))) && (jeu.getCase(i, j).nbPions() > 0)) {
                    ArrayList<Point> voisinsAccessible = jeu.voisinsAccessibles(i, j);
                    if ((voisinsAccessible == null) | (voisinsAccessible.size() == 0)) {
                        if (jeu.getCase(i, j).getTete().getCouleur() == joueur)
                            nombre0++;
                        else
                            nombre1++;
                    } else {
                        if (memeCouleur(new Point(i, j), voisinsAccessible))
                            if (jeu.getCase(i, j).getTete().getCouleur() == joueur)
                                nombre0++;
                            else nombre1++;
                    }
                }
            }
        }
        return nombre0 - nombre1;
    }


    /* Prends le jeu courant et encode la configuration de ce jeu */
    public byte[] configuration(){
        byte[] resultat = new byte[48];
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0)){
                    int indice = coderPoint(i, j);
                    byte hauteur = (byte) jeu.getCase(i, j).nbPions();
                    byte couleur = (byte) jeu.getCase(i, j).getTete().getCouleur();
                    byte value = setValue(couleur, hauteur);
                    resultat[indice] = value;
                }
            }
        }
        return resultat;
    }


    /* Fonction minmax alpha beta
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
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee);
                byte[] config = configuration();
                int valeur;
                if (configurationDejaVu.containsKey(hashCode(config))){
                    valeur = configurationDejaVu.get(hashCode(config));
                } else {
                    valeur = minmaxAlphaBeta(alpha, beta, false, horizon-1);
                    maxValeur = Math.max(maxValeur, valeur);
                }
                alpha = Math.max(alpha, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            byte[] config = configuration();
            configurationDejaVu.put(hashCode(config), maxValeur);
            return maxValeur;

        } else {
            int minValeur = INF;
            ArrayList<Mouvement> etapesSuivant = EtapesPossibles();
            for (int i = 0; i < etapesSuivant.size(); i++){
                Mouvement m = etapesSuivant.get(i);
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee);
                byte[] config = configuration();
                int valeur;
                if (configurationDejaVu.containsKey(hashCode(config))){
                    valeur = configurationDejaVu.get(hashCode(config));
                } else {
                    valeur = minmaxAlphaBeta(alpha, beta, true, horizon-1);
                    minValeur = Math.min(minValeur, valeur);
                }
                alpha = Math.max(alpha, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            byte[] config = configuration();
            configurationDejaVu.put(hashCode(config), minValeur);
            return minValeur;
        }
    } */

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
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee);
                byte[] config = configuration();
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
            ArrayList<Mouvement> etapesSuivant = EtapesPossibles();
            for (int i = 0; i < etapesSuivant.size(); i++){
                Mouvement m = etapesSuivant.get(i);
                Point depart = m.getDepart();
                Point arrvee = m.getArrivee();
                int hauteur = jeu.getCase(depart.x, depart.y).nbPions();
                jeu.bouge(depart, arrvee);
                byte[] config = configuration();
                int valeur;

                valeur = minmaxAlphaBeta(alpha, beta, true, horizon-1);
                minValeur = Math.min(minValeur, valeur);

                alpha = Math.max(alpha, valeur);
                jeu.annule(depart, arrvee, hauteur);
                if (beta <= alpha)
                    break;
            }
            byte[] config = configuration();
            configurationDejaVu.put(hashCode(config), minValeur);
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
        long start = System.currentTimeMillis();
        Mouvement resultat;
        configurationDejaVu = new HashMap<>();
        ArrayList<Mouvement> configSuivants = EtapesPossibles();
        /*
        System.out.println("configSuivants.size() = " + configSuivants.size());
        if (configSuivants.size() > 200){
//            resultat = trouverGagnant(configSuivants, 2);
            IABasique iaB = new IABasique(jeu, joueur);
            return iaB.joue();
        } else if (configSuivants.size() > 150){
//            resultat = trouverGagnant(configSuivants, 2);
            IABasique iaB = new IABasique(jeu, joueur);
            return iaB.joue();
        } else if (configSuivants.size() > 100){
            resultat = trouverGagnant(configSuivants, 2);
        } else if (configSuivants.size() > 50){
            resultat = trouverGagnant(configSuivants, 3);
        } else {
            resultat = trouverGagnant(configSuivants, 4);
        } */
        resultat = trouverGagnant(configSuivants, 2);
        long end=System.currentTimeMillis();
        System.out.println("Coup程序运行时间： "+(end-start)+"ms");
        return resultat;



    }
}
