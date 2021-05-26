package Controleur;

import Modele.Jeu;
import Structures.Mouvement;


import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    Jeu jeu;
    int joueur;

    public IA (Jeu j, int joueur) {
        jeu = j;
        this.joueur = joueur;

    }

    public abstract Mouvement joue();
}
