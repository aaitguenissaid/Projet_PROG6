package Modele;

public class Pion {
    private int couleur;

    public Pion(int couleur) {
        this.couleur = couleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public boolean estBlanc() {
        return this.couleur == 0;
    }

    public boolean estNoir() {
        return this.couleur != 0;
    }

    public String toString() {
        if(estBlanc()) return "Blanc";
        return "Noir";
    }
}
