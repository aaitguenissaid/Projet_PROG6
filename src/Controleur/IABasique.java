package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

public class IABasique extends IA{

    public IABasique(Jeu j) {
        super(j);
    }

    @Override
    public Mouvement joue() {
        return null;
    }
}
