package Vue;

import Modele.PaletteDeCouleurs;
import Structures.Mouvement;

import java.awt.*;

public interface CollecteurEvenements {
    boolean commande(String c);

    void mouvementFini(Mouvement m);

    void movePionTo(Point point);

    void startMove(int i, int i1);

    void ticTac();

    void jouer_en_local();

    void reprendre_une_partie();

    void enregistrer_la_partie();

    void parametres();

    void mainmenu();

    void reagles();

    PaletteDeCouleurs getPalette();

    void revalidateInterface();

    void reaglesBack();

    void last_historique();
    void next_historique();
    void stop_historique();
    void previous_historique();
    void first_historique();

    void relancerPartie();
    void abandonner();
}

