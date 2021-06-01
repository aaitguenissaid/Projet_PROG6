package Controleur;

import Structures.Mouvement;
import Vue.InterfaceUtilisateur;

public class AnimationHistorique extends Animation {
    ControleurMediateur cm;
    boolean estTerminee;

    public AnimationHistorique(int l, ControleurMediateur cm) {
        super(l);
        this.cm = cm;
        estTerminee=false;
    }

    @Override
    public void miseAJour() {
        estTerminee=!cm.next_historique();
        if(!estTerminee) {
            cm.animations.insereTete(this);
        }
    }

    @Override
    public boolean estTerminee() {
        return estTerminee;
    }
}
