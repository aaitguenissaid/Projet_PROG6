package Modele;

public class Joueur {
    String nom;
    int id;
    int couleur;
    public Joueur(int id,int color) {
        this.nom = "Joueur "+id;
        this.id = id;
        this.couleur=color;
    }
    public Joueur(String nom, int id,int couleur) {
        this.nom = nom;
        this.id = id;
        this.couleur=couleur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }
    public int getColore(){
        return couleur;
    }
    public void changeColore(int couleur){
        this.couleur = couleur;
    }
}
