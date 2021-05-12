package Vue;

import Structures.Mouvement;

public interface CollecteurEvenements {
    void clic(int x, int y);

    void mouvementFini(Mouvement m);
}

