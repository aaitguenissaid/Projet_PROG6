package Vue;

import Structures.Mouvement;

import java.awt.*;

public interface CollecteurEvenements {
    boolean commande(String c);

    void mouvementFini(Mouvement m);

    void movePionTo(Point point);

    void startMove(int i, int i1);

    void ticTac();

    void jouer_en_local();

    void parametres();

    void mainmenu();

    void reagles();

    boolean valideAction(String titre, String description, String choix_valider, String choix_annuler);
}

