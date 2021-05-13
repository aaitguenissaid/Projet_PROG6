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

    private int getCouleur(byte value){
        return value >> 7;
    }

    private int getHauteur(byte value){
        return (value & (byte)(1 << 7));
    }

    private byte setValue(byte couleur, byte hauteur){
        return (byte)((couleur << 7) | hauteur);
    }

    /* Prends le jeu courant et encode la configuration de ce jeu */
    public HashMap<Byte, Byte> configuration(){
        HashMap<Byte, Byte> resultat = new HashMap<>();
        for (int i = 0; i < 9; i++){        // modifier 10 après taille est pulblic dans jeu
            for (int j = 0; j < 9; j++){    // modifier 10 après taille est pulblic dans jeu
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
        ArrayList<HashMap<Byte, Byte>> resultat = new ArrayList<>();
        //TOTO
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
