package Controleur;

import Modele.Jeu;
import Structures.Iterateur;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IAFort extends IA{
    private final int INF = 1000;
    HashMap<String, Integer> configurationDejaVu;
    int indice;
    int indiceVoisin;
    int nombreCoup;

    public IAFort(Jeu j, int joueur) {
        super(j, joueur);
        nombreCoup = 0;
    }

    /* Prends le valeur d'une hashtable et rends la couleur de sommet d'un pile */
    byte getCouleur(byte value){
        return ((value & (1<<7)) == 0) ? (byte) 0 : (byte)1;
    }

    /* Prends le valeur d'une hashtable et rends la hauteur de sommet d'un pile */
    byte getHauteur(byte value){
        return (byte) (value & (~(1 << 7)));
    }

    /* Prends la couleur d'un sommet et sa hauteur, rends la valeur d'un hashtable */
    byte setValue(byte couleur, byte hauteur){
        return (byte)((couleur << 7) | hauteur);
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

    /* Prends un numéro de case et décode en Point */
    private Point decoderIndice(int indice){
        int i = 0;
        int j = 0;
        if (indice <= 1){
            i = 0;
            j = indice + 4;
        } else if ((indice >=2)&&(indice <= 6)){
            i = 1;
            j = indice - 1;
        } else if ((indice >=7)&&(indice <= 13)){
            i = 2;
            j = indice - 7;
        } else if ((indice >=14)&&(indice <= 20)){
            i = 3;
            j = indice - 14;
        } else if ((indice >=21)&&(indice <= 23)){
            i = 4;
            j = indice - 20;
        } else if ((indice >=24)&&(indice <= 26)){
            i = 4;
            j = indice - 19;
        } else if ((indice >=27)&&(indice <= 33)){
            i = 5;
            j = indice - 25;
        } else if ((indice >=34)&&(indice <= 40)){
            i = 6;
            j = indice - 32;
        } else if ((indice >=41)&&(indice <= 45)){
            i = 7;
            j = indice - 38;
        } else if ((indice >=46)&&(indice <= 47)){
            i = 8;
            j = indice - 43;
        }
        return new Point(i, j);
    }

    /* Copy une table */
    byte[] copyTable(byte[] original){
        int length = original.length;
        byte[] copy = new byte[length];
        for (int i = 0; i < length; i++){
            copy[i] = original[i];
        }
        return copy;
    }

    /* Prends deux indice, indiceDepart et indiceArrivee, teste si on peut bouger le pile de pions de depart à l'arrivée */
    private boolean peutBouger(byte[] config, int indiceDepart, int indiceArrivee){
        if ((indiceArrivee >= 0) && (indiceArrivee < config.length)){
            if (config[indiceArrivee] != 0){
                int hauteurDepart = getHauteur(config[indiceDepart]);
                int hauteurArrivee = getHauteur(config[indiceArrivee]);
                if (hauteurDepart + hauteurArrivee <= 5)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }

    private boolean nonMilieu(int i, int j){
        if ((i == 4)&&(j == 4))
            return false;
        else
            return true;
    }


    /* Prends une indice de tableau de configuration comme le depart et rends les indices comme arrivées accessibles */
    ArrayList<Integer> voisinsAccessibles(byte[] config, int indiceDepart){
        ArrayList<Integer> resultat = new ArrayList<>();
        if (getHauteur(config[indiceDepart]) == 0)
            return resultat;
        int h = decoderIndice(indiceDepart).x;
        int l = decoderIndice(indiceDepart).y;
        int indiceArrivee;
        if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l)))&&(nonMilieu(h-1, l))){        //(h-1, l)
            indiceArrivee = coderPoint(h-1, l);
            if (peutBouger(config, indiceDepart, indiceArrivee))
                resultat.add(indiceArrivee);
        }
        if ((h+1 < jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l)))&&(nonMilieu(h+1, l))){         //(h+1, l)
            indiceArrivee = coderPoint(h+1, l);
            if (peutBouger(config, indiceDepart, indiceArrivee))
                resultat.add(indiceArrivee);
        }
        if (l-1 >= 0){
            if (jeu.estCaseValide(new Point(h, l-1))&&(nonMilieu(h, l-1))){
                indiceArrivee = coderPoint(h, l-1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }
            if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l-1)))&&(nonMilieu(h-1, l-1))){    //(h-1, l-1)
                indiceArrivee = coderPoint(h-1, l-1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }
            if ((h+1 <  jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l-1)))&&(nonMilieu(h+1, l-1))){  //(h+1, l-1)
                indiceArrivee = coderPoint(h+1, l-1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }
        }
        if (l+1 < jeu.getTaille().l){
            if (jeu.estCaseValide(new Point(h, l+1))&&(nonMilieu(h, l+1))){
                indiceArrivee = coderPoint(h, l+1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }

            if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l+1)))&&(nonMilieu(h-1, l+1))){    //(h-1, l+1)
                indiceArrivee = coderPoint(h-1, l+1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }
            if ((h+1 < jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l+1)))&&(nonMilieu(h+1, l+1))){  //(h+1, l+1)
                indiceArrivee = coderPoint(h+1, l+1);
                if (peutBouger(config, indiceDepart, indiceArrivee))
                    resultat.add(indiceArrivee);
            }
        }
        return resultat;
    }

    /* Prends une configuration, teste s'il est une feuille */
    private boolean estFeuille(byte[] configuration){
        for (int i = 0; i < configuration.length; i++) {
            if (configuration[i] != 0){
                ArrayList<Integer> voisinsAcc = voisinsAccessibles(configuration, i);
                if (voisinsAcc != null) {
                    if (voisinsAcc.size() > 0)
                        return false;
                }
            }
        }
        return true;
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

    /* Evaluer la note d'une feuille */
    public int evaluation(byte[] configuration){
        int nombre0 = 0;
        int nombre1 = 0;
        for (int i = 0; i < configuration.length; i++){
            if (configuration[i] != 0){
                int couleur = getCouleur(configuration[i]);
                if (couleur == 0)
                    nombre0++;
                else
                    nombre1++;
            }
        }
        if (nombre0 == nombre1)
            return 0;
        else
            return nombre0 > nombre1 ? 1 : -1;
    }

    /* Evaluer la note d'une feuille */
    public int evaluerNoeud(byte[] configuration){
        int nombre0 = 0;
        int nombre1 = 0;
        for (int i = 0; i < configuration.length; i++) {
            ArrayList<Integer> voisins = voisinsAccessibles(configuration, i);
            if (getCouleur(configuration[i]) == joueur) {
                if (getHauteur(configuration[i]) == 5)
                    nombre0++;
                else if (voisins.size() == 0)
                    nombre0++;
                else if (voisins.size() > 0) {
                    int nombreDeJoueur = 0;
                    for (int j = 0; j < voisins.size(); j++) {
                        if (getCouleur(configuration[voisins.get(j)]) == joueur)
                            nombreDeJoueur++;
                    }
                    if (nombreDeJoueur == voisins.size())
                        nombre0++;
                }
            }
            if (getCouleur(configuration[i]) != joueur) {
                if (getHauteur(configuration[i]) == 5)
                    nombre1++;
                else if (voisins.size() == 0)
                    nombre1++;
                else if (voisins.size() > 0) {
                    int nombreDeJoueur = 0;
                    for (int j = 0; j < voisins.size(); j++) {
                        if (getCouleur(configuration[voisins.get(j)]) != joueur)
                            nombreDeJoueur++;
                    }
                    if (nombreDeJoueur == voisins.size())
                        nombre1++;
                }
            }
        }
        return nombre0 - nombre1;
    }

    public Iterateur<byte[]> configurationSuivants(byte[] configuration){
        indice = 0;
        indiceVoisin = 0;
        return new Iterateur<byte[]>() {
            public boolean aProchain() {
                if(indice < configuration.length - 1)
                    return true;
                else if (indice == configuration.length - 1){
                    ArrayList<Integer> voisinsAcc = voisinsAccessibles(configuration, indice);
                    return  (indiceVoisin < voisinsAcc.size()) ? true : false;
                }
                else
                    return false;
            }

            public byte[] prochain() {
                ArrayList<Integer> voisinsAcc = voisinsAccessibles(configuration, indice);
                int indiceDepart = indice;
                byte couleurDepart = getCouleur(configuration[indice]);
                byte hauteurDeaprt = getHauteur(configuration[indice]);
                byte[] copy = copyTable(configuration);
                int indiceArrivee = voisinsAcc.get(indiceVoisin);
                byte hauteurArrivee = getHauteur(configuration[indiceArrivee]);
                byte nouveauHauteur = (byte) (hauteurDeaprt + hauteurArrivee);
                byte nouveauValueArrivee = setValue(couleurDepart, nouveauHauteur);
                copy[indiceDepart] = 0;
                copy[indiceArrivee] = nouveauValueArrivee;
                if (indiceVoisin+1 < voisinsAcc.size()){
                    indiceVoisin++;
                } else {
                    indiceVoisin = 0;
                    indice++;
                    if (indice < 48){
                        ArrayList<Integer> voisinsAc = voisinsAccessibles(configuration, indice);
                        while (voisinsAc.size() == 0){
                            indice++;
                            voisinsAc = voisinsAccessibles(configuration, indice);
                        }
                    }
                }
                return copy;
            }
        };
    }

    /* A partir d'une configuration qu'on a trouver pour IA, retourner la case de depart et la case d'arrivée */
    public Mouvement configurationVersMouvement(byte[] configuration, byte[] suivant){
        int indiceDepart = 0;
        int indiceArrivee = 0;
        for (int i = 0; i < configuration.length; i++){
            if (configuration[i] != suivant[i]){
                if (suivant[i] == 0){
                    indiceDepart = i;
                } else{
                    indiceArrivee = i;
                }
            }
        }
        Point depart = decoderIndice(indiceDepart);
        Point arrivee = decoderIndice(indiceArrivee);
        return new Mouvement(depart, arrivee);
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

    /* Fonction minmax alpha beta */
    private int minmaxAlphaBeta(byte[] config, int alpha, int beta, boolean estMax, int horizon){
        if ((estFeuille(config)) || (horizon == 0)){
            int value;
            String key = hashCode(config);
            if (configurationDejaVu.containsKey(key)){
                value = configurationDejaVu.get(key);
            } else {
                value = evaluerNoeud(config);
                configurationDejaVu.put(hashCode(config), value);
            }
            return value;
        }
        IterateurIA it = new IterateurIA(this, config);
        if (estMax){
            int maxValeur = -INF;
            while (it.aProchain()){
                byte[] configSuivant = it.prochain();
                int valeur;
                if (configurationDejaVu.containsKey(hashCode(config))){
                    valeur = configurationDejaVu.get(hashCode(config));
                } else {
                    valeur = minmaxAlphaBeta(configSuivant, alpha, beta, false, horizon-1);
                }
                maxValeur = Math.max(maxValeur, valeur);
                if (beta <= alpha)
                    break;

            }
            configurationDejaVu.put(hashCode(config), maxValeur);
            return maxValeur;
        } else {
            int minValeur = INF;
            while (it.aProchain()){
                byte[] configSuivant = it.prochain();
                int valeur;
                if (configurationDejaVu.containsKey(hashCode(config))){
                    valeur = configurationDejaVu.get(hashCode(config));
                } else {
                    valeur = minmaxAlphaBeta(configSuivant, alpha, beta, true, horizon-1);
                }
                minValeur = Math.min(minValeur, valeur);
                beta = Math.min(beta, valeur);
                if (beta <= alpha)
                    break;
            }
            configurationDejaVu.put(hashCode(config), minValeur);
            return minValeur;
        }
    }

    private byte[] trouverGagnant(byte[] config, int horizon){
        byte[] gagnant = null;
        int imax = 0;
        int max = -INF;
        IterateurIA it = new IterateurIA(this, config);
        while (it.aProchain()){
            byte[] configSuivant = it.prochain();
            int courant = minmaxAlphaBeta(configSuivant, -INF, INF,true, horizon);
            if (courant > max){
                max = courant;
                gagnant = configSuivant;
            }
        }
        return gagnant;
    }


    @Override
    public Mouvement joue() {
        long start = System.currentTimeMillis();
        configurationDejaVu = new HashMap<>();
        byte[] config = configuration();
        byte[] gagnant = null;
        if (nombreCoup < 3){
            gagnant = trouverGagnant(config, 1);
            nombreCoup++;
        } else if (nombreCoup < 6){
            gagnant = trouverGagnant(config, 2);
            nombreCoup++;
        } else if (nombreCoup < 9){
            gagnant = trouverGagnant(config, 2);
            nombreCoup++;
        } else
            gagnant = trouverGagnant(config, 2);
        Mouvement resultat = configurationVersMouvement(config, gagnant);
        long end=System.currentTimeMillis();
        System.out.println("nombreCoup = " + nombreCoup);
        System.out.println("Temps d'exécution ："+(end-start)+"ms");
        return resultat;
    }

}
