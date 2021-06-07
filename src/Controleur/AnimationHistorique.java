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
        if(estTerminee) {
            cm.navigationHistoriqueRunning=false;
            cm.playBoutonHistorique();
        } else {
            cm.animations.insereTete(this);
        }
        cm.histAffControl.repaint();
    }

    @Override
    public boolean estTerminee() {
        return estTerminee;
    }
}
