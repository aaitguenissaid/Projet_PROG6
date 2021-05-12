package Modele;

import Structures.SequenceListe;

public class Case {
    boolean valide;
    SequenceListe<Pion> pions;

    public Case() {
        this.valide = true;
        this.pions = new SequenceListe<>();
    }

    public Case(boolean valide) {
        this.valide = valide;
        this.pions = new SequenceListe<>();
    }

    public Case(boolean valide, Pion pion) {
        this.valide = valide;
        this.pions = new SequenceListe<>();
        this.pions.insereTete(pion);
    }

    public Case(boolean valide, SequenceListe<Pion> pions) {
        this.valide = valide;
        this.pions = pions;
    }
}
