package Controleur;

import Modele.Jeu;
import Structures.Mouvement;
import Vue.InterfaceUtilisateur;

import java.awt.*;

public class AnimationJoueurIA extends Animation {
    InterfaceUtilisateur gui;
    Mouvement to_clic;
    Jeu jeu;
    ControleurMediateur cm;
    int id;

    public AnimationJoueurIA(int l, InterfaceUtilisateur gui, Mouvement to_clic, ControleurMediateur cm) {
        this(l, gui, to_clic, cm, 0);
    }

    public AnimationJoueurIA(int l, InterfaceUtilisateur gui, Mouvement to_clic, ControleurMediateur cm, int id) {
        super(l);
        this.gui = gui;
        this.to_clic = to_clic;
        this.jeu = gui.jeu();
        this.cm = cm;
        this.id = id;
    }

    @Override
    public void miseAJour() {
        if(to_clic!=null) cm.bouge(to_clic.getDepart(), to_clic.getArrivee());
        gui.metAJour();
        if(cm.mode==cm.MODE_IAvsIA) {
            cm.lancerAnimationAdversaire(id);
        }
    }
}
