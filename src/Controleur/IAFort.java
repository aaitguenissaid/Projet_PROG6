package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.HashMap;

public class IAFort extends IA{

    public IAFort(Jeu j, int joueur) {
        super(j, joueur);
    }


    public HashMap<Byte, Byte> configuration(){
        HashMap<Byte, Byte> resultat = new HashMap<>();
        for (int i = 0; i < 9; i++){        // modifier 10 après taille est pulblic dans jeu
            for (int j = 0; j < 9; j++){    // modifier 10 après taille est pulblic dans jeu
                if (jeu.estCaseValide(new Point(i, j))){
                    byte key = super.hashCode(i, j);
                    byte hauteur = (byte) jeu.getCase(i, j).hauteur();
                    byte couleur = (byte) jeu.getCase(i, j).getTete().getCouleur();
                    byte value = (byte) ((hauteur << 7) | couleur);
                    resultat.put(key, value);
                }
            }
        }
        return resultat;
    }

    @Override
    public Mouvement joue() {
        return null;
    }
}
