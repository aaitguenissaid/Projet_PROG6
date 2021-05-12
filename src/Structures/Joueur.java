package Structures;

public class Joueur {
    String nom;
    int id;
    int colore;
    public Joueur(int id,int colore) {
        this.nom = "Joueur "+id;
        this.id = id;
        this.colore=colore;
    }
    public Joueur(String nom, int id,int colore) {
        this.nom = nom;
        this.id = id;
        this.colore=colore;
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
        return colore;
    }
    public void changeColore(int colore){
        this.colore = colore;
    }
}
