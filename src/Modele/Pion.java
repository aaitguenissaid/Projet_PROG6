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

    //TODO BUG! en cas d'inversion de couleurs ça renvoies la mauvaise couleur.
    public boolean estCouleur(int id) {
        if(id==1) {
            return estCouleur1();
        }
        return estCouleur2();
    }

    public String toString() {
        if(estCouleur1()) return "Blanc";
        return "Noir";
    }
}
