package Modele;

public class Joueur {
    String pseudo;
    int id;

    public Joueur(int id) {
        this.pseudo = "Joueur " + id;
        this.id = id;
    }

    public Joueur(String pseudo, int id) {
        this.pseudo = pseudo;
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

}
