package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

public class IAFortTableau extends IA{
    private final int INF = 1000;

    public IAFortTableau(Jeu j, int joueur) {
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
    private byte[] copyTable(byte[] original){
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
    private ArrayList<Integer> voisinsAccessibles(byte[] config, int indiceDepart){
        ArrayList<Integer> resultat = new ArrayList<>();
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
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).hauteur() > 0)){
                    int indice = coderPoint(i, j);
                    byte hauteur = (byte) jeu.getCase(i, j).hauteur();
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
        if (joueur == 0)
            return nombre0 - nombre1;
        else
            return nombre1 - nombre0;
    }

    /* A partir d'une configuration courante, retourne toutes les configurations possibles pour en une étape*/
    public ArrayList<byte[]> configurationSuivants(byte[] configuration){
        ArrayList<byte[]> resultat = new ArrayList<>();
        for (int i = 0; i < configuration.length; i++){
            if (configuration[i] != 0){
                ArrayList<Integer> voisinsAcc = voisinsAccessibles(configuration, i);
                int indiceDepart = i;
                byte couleurDepart = getCouleur(configuration[i]);
                byte hauteurDeaprt = getHauteur(configuration[i]);
                if (voisinsAcc != null){
                    if (voisinsAcc.size() > 0){
                        for (int j = 0; j < voisinsAcc.size(); j++){
                            byte[] copy = copyTable(configuration);
                            int indiceArrivee = voisinsAcc.get(j);
                            byte hauteurArrivee = getHauteur(configuration[indiceArrivee]);
                            byte nouveauHauteur = (byte) (hauteurDeaprt + hauteurArrivee);
                            byte nouveauValueArrivee = setValue(couleurDepart, nouveauHauteur);
                            copy[indiceDepart] = 0;
                            copy[indiceArrivee] = nouveauValueArrivee;
                            resultat.add(copy);
                        }
                    }
                }
            }
        }
        return resultat;
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

    /* Fonction minmax */
    private int minmax(byte[] config, boolean estMax){
        if (estFeuille(config))
            return evaluation(config);
        if (estMax){
            int maxValeur = -INF;
            ArrayList<byte[]> configSuivants = configurationSuivants(config);
            if (configSuivants != null){
                for (int i = 0; i < configSuivants.size(); i++){
                    byte[] configSuivant = configSuivants.get(i);
                    int valeur = minmax(configSuivant, false);
                    maxValeur = Math.max(maxValeur, valeur);
                    return maxValeur;
                }
            }
        } else {
            int minValeur = INF;
            ArrayList<byte[]> configSuivants = configurationSuivants(config);
            if (configSuivants != null){
                for (int i = 0; i < configSuivants.size(); i++){
                    byte[] configSuivant = configSuivants.get(i);
                    int valeur = minmax(configSuivant, true);
                    minValeur = Math.min(minValeur, valeur);
                    return minValeur;
                }
            }
        }
        return 0;
    }


    /* Fonction minmax alpha beta */
    private int minmaxAlphaBeta(byte[] config, int alpha, int beta, boolean estMax){
        if (estFeuille(config))
            return evaluation(config);
        if (estMax){
            int maxValeur = -INF;
            ArrayList<byte[]> configSuivants = configurationSuivants(config);
            if (configSuivants != null){
                for (int i = 0; i < configSuivants.size(); i++){
                    byte[] configSuivant = configSuivants.get(i);
                    int valeur = minmaxAlphaBeta(configSuivant, alpha, beta,false);
                    maxValeur = Math.max(maxValeur, valeur);
                    alpha = Math.max(alpha, valeur);
                    if (beta <= alpha)
                        break;
                    return maxValeur;
                }
            }
        } else {
            int minValeur = INF;
            ArrayList<byte[]> configSuivants = configurationSuivants(config);
            if (configSuivants != null){
                for (int i = 0; i < configSuivants.size(); i++){
                    byte[] configSuivant = configSuivants.get(i);
                    int valeur = minmaxAlphaBeta(configSuivant, alpha, beta, true);
                    minValeur = Math.min(minValeur, valeur);
                    beta = Math.min(beta, valeur);
                    if (beta <= alpha)
                        break;
                    return minValeur;
                }
            }
        }
        return 0;
    }

    @Override
    public Mouvement joue() {
        byte[] config = configuration();
        byte[] gagnant = null;
        ArrayList<byte[]> configSuivants = configurationSuivants(config);

        int imax = 0;
        int max = -INF;
        System.out.println("configSuivants.size() = " + configSuivants.size());
        for (int i = 0; i < configSuivants.size(); i++){
            byte[] configSuivant = configSuivants.get(i);
            /* Utiliser l'algorithme minmax */
            //int courant = minmax(configSuivant, true);
            // Utiliser l'algorithme minmax alpha beta
            int courant = minmaxAlphaBeta(configSuivant, -INF, INF,true);
            if (courant > max){
                max = courant;
                imax = i;
            }
        }
        gagnant = configSuivants.get(imax);
        Mouvement resultat = configurationVersMouvement(config, gagnant);
        return resultat;
    }

}
