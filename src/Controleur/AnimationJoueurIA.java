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

    public AnimationJoueurIA(int l, InterfaceUtilisateur gui, Mouvement to_clic, ControleurMediateur cm) {
        super(l);
        this.gui = gui;
        this.to_clic = to_clic;
        this.jeu = gui.jeu();
        this.cm = cm;
    }

    @Override
    public void miseAJour() {
        if(to_clic!=null) cm.bouge(to_clic.getDepart(), to_clic.getArrivee());
        gui.metAJour();
    }
}
