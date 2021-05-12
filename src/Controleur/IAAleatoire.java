package Controleur;

import Structures.Mouvement;

import Modele.Jeu;
import java.util.Random;

public class IAAleatoire extends IA{
    Random r1, r2;

    public IAAleatoire(Jeu j) {
        super(j);
        r1 = new Random();
        r2 = new Random();
    }

    @Override
    public Mouvement joue() {
        return null;
    }
}
