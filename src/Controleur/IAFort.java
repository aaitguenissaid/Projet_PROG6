package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class IAFort extends IA{

    public IAFort(Jeu j, int joueur) {
        super(j, joueur);
    }

    /* Prends le valeur d'une hashtable et rends la couleur de sommet d'un pile */
    private byte getCouleur(byte value){
        return (byte) (value >> 7);
    }

    /* Prends le valeur d'une hashtable et rends la hauteur de sommet d'un pile */
    private byte getHauteur(byte value){
        return (byte) (value & (~(1 << 7)));
    }

    /* Prends la couleur d'un sommet et sa hauteur, rends la valeur d'un hashtable */
    private byte setValue(byte couleur, byte hauteur){
        return (byte)((couleur << 7) | hauteur);
    }

    /* Copy une hashtable */
    private HashMap<Byte, Byte> copyHashMap(HashMap<Byte, Byte> original){
        HashMap<Byte, Byte> copy = new HashMap<>();
        for (HashMap.Entry<Byte, Byte> entry : original.entrySet()){
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    /* Prends deux key, keyDepart et keyArrivee, teste si on peut bouger le pile de pions de depart à l'arrivée */
    private boolean peutBouger(HashMap<Byte, Byte> config, byte keyDepart, byte keyArrivee){
        if (config.containsKey(keyArrivee)){
            int hauteurDepart = getHauteur(config.get(keyDepart));
            int hauteurArrivee = getHauteur(config.get(keyArrivee));
            if (hauteurDepart + hauteurArrivee <= 5)
                return true;
            else
                return false;
        }
        return false;
    }

    /* Prends un key de hashtable comme le depart et rends les keys comme arrivées accessibles */
    private ArrayList<Byte> voisinsAccessibles(HashMap<Byte, Byte> config, byte keyDepart){
        ArrayList<Byte> resultat = new ArrayList<>();
        int h = super.hashCodeVerH(keyDepart);
        int l = super.hashCodeVerL(keyDepart);
        byte keyArrivee;
        if (h-1 >= 0){        //(h-1, l)
            keyArrivee = super.hashCode(h-1, l);
            if (peutBouger(config, keyDepart, keyArrivee))
                resultat.add(keyArrivee);
        }
        if (h+1 < jeu.getTaille().h){         //(h+1, l)
            keyArrivee = super.hashCode(h+1, l);
            if (peutBouger(config, keyDepart, keyArrivee))
                resultat.add(keyArrivee);
        }
        if (l-1 >= 0){
            keyArrivee = super.hashCode(h, l-1);         //(h, l-1)
            if (peutBouger(config, keyDepart, keyArrivee))
                resultat.add(keyArrivee);
            if (h-1 >= 0){    //(h-1, l-1)
                keyArrivee = super.hashCode(h-1, l-1);
                if (peutBouger(config, keyDepart, keyArrivee))
                    resultat.add(keyArrivee);
            }
            if (h+1 <  jeu.getTaille().h){  //(h+1, l-1)
                keyArrivee = super.hashCode(h+1, l-1);
                if (peutBouger(config, keyDepart, keyArrivee))
                    resultat.add(keyArrivee);
            }
        }
        if (l+1 < jeu.getTaille().l){
            keyArrivee = super.hashCode(h, l+1);         //(h, l+1)
            if (peutBouger(config, keyDepart, keyArrivee))
                resultat.add(keyArrivee);
            if (h-1 >= 0){    //(h-1, l+1)
                keyArrivee = super.hashCode(h-1, l+1);
                if (peutBouger(config, keyDepart, keyArrivee))
                    resultat.add(keyArrivee);
            }
            if (h+1 < jeu.getTaille().h){  //(h+1, l+1)
                keyArrivee = super.hashCode(h+1, l+1);
                if (peutBouger(config, keyDepart, keyArrivee))
                    resultat.add(keyArrivee);
            }
        }
        return resultat;
    }

    /* Prends une configuration, teste s'il est une feuille */
    private boolean estFeuille(HashMap<Byte, Byte> configuration){
        for (HashMap.Entry<Byte, Byte> entry : configuration.entrySet()) {
            byte keyDepart = entry.getKey();
            ArrayList<Byte> voisinsAcc = voisinsAccessibles(configuration, keyDepart);
            if (voisinsAcc != null) {
                if (voisinsAcc.size() > 0)
                    return false;
            }
        }
        return true;
    }

    /* Prends le jeu courant et encode la configuration de ce jeu */
    public HashMap<Byte, Byte> configuration(){
        HashMap<Byte, Byte> resultat = new HashMap<>();
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if (jeu.estCaseValide(new Point(i, j))){
                    byte key = super.hashCode(i, j);
                    byte hauteur = (byte) jeu.getCase(i, j).hauteur();
                    byte couleur = (byte) jeu.getCase(i, j).getTete().getCouleur();
                    byte value = setValue(couleur, hauteur);
                    resultat.put(key, value);
                }
            }
        }
        return resultat;
    }



    /* Evaluer la note d'une feuille */
    public int evaluation(HashMap<Byte, Byte> configuration){
        int nombre0 = 0;
        int nombre1 = 0;
        for(byte value : configuration.values()) {
            int couleur = value >> 7;
            if (couleur == 0)
                nombre0++;
            else
                nombre1++;
        }
        if (nombre0 == nombre1)
            return 0;
        else{
            if (joueur == 0)
                return nombre0 > nombre1 ? 1 : -1;
            else
                return nombre1 > nombre0 ? 1 : -1;
        }
    }

    /* A partir d'une configuration courante, retourne toutes les configurations possibles pour en une étape*/
    public ArrayList<HashMap<Byte, Byte>> configurationSuivants(HashMap<Byte, Byte> configuration){
        ArrayList<HashMap<Byte, Byte>> resultat = new ArrayList<HashMap<Byte, Byte>>();
        for (HashMap.Entry<Byte, Byte> entry : configuration.entrySet()){
            byte keyDepart = entry.getKey();
            byte valueDepart = entry.getValue();
            byte hauteurDepart = getHauteur(valueDepart);
            byte color = getCouleur(valueDepart);
            ArrayList<Byte> voisinsAcc = voisinsAccessibles(configuration, keyDepart);
            if (voisinsAcc != null){
                if (voisinsAcc.size() > 0){
                    for (int i = 0; i < voisinsAcc.size(); i++){
                        byte keyArrivee = voisinsAcc.get(i);
                        byte valueArrivee = configuration.get(keyArrivee);
                        byte hauteurArrivee = getHauteur(valueArrivee);
                        HashMap<Byte, Byte> copy = copyHashMap(configuration);
                        copy.remove(keyDepart);
                        byte nouveauCouleurArrivee = color;
                        byte nouvaueHauteurArrivee = (byte) (hauteurDepart+hauteurArrivee);
                        byte nouveauValueArrivee = setValue(nouveauCouleurArrivee, nouvaueHauteurArrivee);
                        copy.replace(keyArrivee, nouveauValueArrivee);
                        resultat.add(copy);
                    }
                }
            }
        }
        return resultat;
    }


    /* A partir d'une configuration qu'on a trouver pour IA, retourner la case de depart et la case d'arrivée */
    public Mouvement configurationVersMouvement(HashMap<Byte, Byte> configuration, HashMap<Byte, Byte> suivant){
        Iterator<HashMap.Entry<Byte, Byte>> iterator;
        iterator = configuration.entrySet().iterator();
        byte keyDepart = 0;
        byte keyArrivee = 0;
        while (iterator.hasNext()) {
            HashMap.Entry<Byte, Byte> entry = iterator.next();
            keyDepart = entry.getKey();
            if (!suivant.containsKey(keyDepart))
                break;
        }
        int hDepart = super.hashCodeVerH(keyDepart);
        int lDepart = super.hashCodeVerL(keyDepart);
        Point depart = new Point(hDepart, lDepart);
        iterator = configuration.entrySet().iterator();
        int valueDepart;
        int valueArrivee;
        while (iterator.hasNext()) {
            HashMap.Entry<Byte, Byte> entry = iterator.next();
            keyArrivee = entry.getKey();
            valueDepart = entry.getValue();
            if (keyArrivee != keyDepart) {
                valueArrivee = suivant.get(keyDepart);
                if (valueDepart != valueArrivee)
                    break;
            }
        }
        int hArrivee = super.hashCodeVerH(keyArrivee);
        int lArrivee = super.hashCodeVerL(keyArrivee);
        Point arrivee = new Point(hArrivee, lArrivee);
        return new Mouvement(depart, arrivee);
    }




    @Override
    public Mouvement joue() {
        return null;
    }
}
