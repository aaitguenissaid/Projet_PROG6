package Modele;

public class Pion {
    private int couleur;

    public Pion(int couleur) {
        this.couleur = couleur;
    }

    public int getCouleur() {
        return couleur;
    }

    public boolean estCouleur1() {
        return this.couleur == 0;
    }

    public boolean estCouleur2() {
        return this.couleur != 0;
    }

    public String toString() {
        if(estCouleur1()) return "Blanc";
        return "Noir";
    }
}
