package Controleur;

import Structures.Mouvement;
import Vue.InterfaceUtilisateur;

public class AnimationHistorique extends Animation {
    ControleurMediateur cm;
    boolean estTerminee;
    boolean versEtatInitial;

    public AnimationHistorique(int l, ControleurMediateur cm) {
        super(l);
        this.cm = cm;
        estTerminee=false;
        versEtatInitial = cm.jeu.getHistorique().aPrecedent();
    }

    @Override
    public void miseAJour() {
        if(versEtatInitial) {
            estTerminee = !cm.next_historique();
        } else {
            estTerminee = !cm.previous_historique();
        }
        if(!estTerminee) {
            cm.animations.insereTete(this);
        } else {
            cm.navigationHistoriqueRunning=false;
        }
    }

    @Override
    public boolean estTerminee() {
        return estTerminee;
    }
}
