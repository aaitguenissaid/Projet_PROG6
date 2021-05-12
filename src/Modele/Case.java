package Modele;

import Structures.*;

public class Case {
    boolean valide;
    SequenceListe<Pion> pions;
    Pion tete;

    public Case() {
        this.valide = true;
        this.pions = new SequenceListe<>();
        tete = null;
    }

    public Case(boolean valide) {
        this.valide = valide;
        this.pions = new SequenceListe<>();
        tete = null;
    }

    public Case(boolean valide, Pion pion) {
        this.valide = valide;
        this.pions = new SequenceListe<>();
        this.pions.insereTete(pion);
        tete = pion;
    }

    public Case(boolean valide, SequenceListe<Pion> pions) {
        this.valide = valide;
        this.pions = pions;
        tete = pions.extraitTete();
        pions.insereTete(tete);
    }

    void addPion(Pion pion) {
        this.pions.insereTete(pion);
        this.tete = pion;
    }

    Pion getTete() {
        return this.tete;
    }

    public boolean estValide() {
        return this.valide;
    }

    void setValide(boolean valide) {
        this.valide = valide;
    }

    public String toString() {
        String s = "[";
        if(!valide) s += " ";
        else {
            s += tete.getCouleur();
        }
        s += "]";
        return s;
    }
    public Iterateur<Pion> getIterateur(){
        return pions.iterateur();
    }
}
