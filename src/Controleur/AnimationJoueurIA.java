package Controleur;

import Modele.Jeu;
import Structures.Mouvement;
import Vue.InterfaceUtilisateur;

import java.awt.*;

public class AnimationJoueurIA extends Animation {
    InterfaceUtilisateur gui;
    Mouvement to_clic;
    Jeu jeu;

    public AnimationJoueurIA(int l, InterfaceUtilisateur gui, Mouvement to_clic) {
        super(l);
        this.gui = gui;
        this.to_clic = to_clic;
        this.jeu = gui.jeu();
    }

    @Override
    public void miseAJour() {
        if(to_clic!=null) jeu.bouge(to_clic.getDepart(), to_clic.getArrivee());
        gui.metAJour();
    }
}
