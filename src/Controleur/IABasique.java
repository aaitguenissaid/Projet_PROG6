package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

public class IABasique extends IA{

    public IABasique(Jeu j, int joueur) {
        super(j, joueur);
    }

    @Override
    public Mouvement joue() {
        return null;
    }
}
