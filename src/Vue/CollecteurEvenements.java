package Vue;

import Structures.Mouvement;

import java.awt.*;

public interface CollecteurEvenements {
    boolean commande(String c);

    void mouvementFini(Mouvement m);

    void movePionTo(Point point);

    void startMove(int i, int i1);
}

