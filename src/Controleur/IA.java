package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    public Jeu jeu;
    public ArrayList<Integer> voisinsAccessible;

    public IA (Jeu j) {
        jeu = j;
        voisinsAccessible = new ArrayList<>();
    }


    public abstract Mouvement joue();
}
