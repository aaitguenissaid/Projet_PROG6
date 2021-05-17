package Vue;

import Structures.Mouvement;

import java.awt.*;

public interface CollecteurEvenements {
    void taper(int x, int y);

    void mouvementFini(Mouvement m);

    void movePionTo(Point point);

    void startMove(int i, int i1);
}

