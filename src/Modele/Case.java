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

    SequenceListe<Pion> getPions() {
        return this.pions;
    }

    public int hauteur() {
        return this.pions.taille();
    }

    public boolean estValide() {
        return this.valide;
    }

    void setValide(boolean valide) {
        this.valide = valide;
    }

    public boolean aTeteBlanche() {
        return this.tete.estBlanc();
    }

    public boolean aTeteNoire() {
        return this.tete.estNoir();
    }

    public String toString() {
        String s = "[";
        if(!valide) s += " ";
        else {
            if(tete==null) s += " ";
            else s += tete.getCouleur();
        }
        s += "]";
        return s;
    }

    public void supprimePions() {
        this.pions = new SequenceListe<>();
        this.tete = null;
    }

    public void ajoutePions(SequenceListe<Pion> nvx_pions) {
        IterateurListe<Pion> it = (IterateurListe<Pion>) nvx_pions.iterateur();

        //On construit une séquence qui contient les pions dans l'ordre inverse (file)
        SequenceListe<Pion> file = new SequenceListe<>();
        while(it.aProchain()) {
            file.insereQueue(it.prochain());
        }

        //On ajoute les pions à la case
        it = (IterateurListe<Pion>) file.iterateur();
        while(it.aProchain()) {
            Pion pion = it.prochain();
            pions.insereTete(pion);
            this.tete = pion;
        }
    }

    public int nbPions() {
        if(pions==null) return 0;
        return pions.taille();
    }

    public Iterateur<Pion> getIterateur(){
        return pions.iterateur();
    }
}
