package Vue;

import Modele.Classement;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Structures.Mouvement;
import Structures.Point;
import java.awt.*;

public interface CollecteurEvenements {
    boolean commande(String c);

    void mouvementFini(Mouvement m);

    void movePionTo(Point point);

    void startMove(int i, int i1);

    void ticTac();

    void jouer_en_local();

    void jouer_contre_ia();

    void lancer_demo();

    void reprendre_une_partie();

    void enregistrer_la_partie();

    void parametres();

    void mainmenu();

    void reagles();

    PaletteDeCouleurs getPalette();

    void revalidateInterface();

    void reaglesBack();

    void last_historique();

    boolean next_historique();

    void play_pause_historique();

    boolean previous_historique();

    void first_historique();

    void relancerPartie();

    void abandonner();

    boolean getSonState();

    void deisabel_enabel_son();

    boolean suggestion();

    Mouvement suggestionMouvement();

    void demanderSuggestion();

    void setNomJ1(String text);

    void setNomJ2(String text);

    void inverserJoueurs();

    EffetsSonores getEffetsSonores();

    Jeu getJeu();

    void classement();

    void setHistAffControl(HistoriqueTimeLine h);

    Classement getClassement();
}

