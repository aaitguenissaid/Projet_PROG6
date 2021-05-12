package Structures;

public class Mouvement {
    Coordonnee depart;
    Coordonnee arrivee;

    public Mouvement(Coordonnee depart, Coordonnee arrivee){
        this.depart = depart;
        this.arrivee = arrivee;
    }
    public Mouvement(Coordonnee depart){
        this.depart = depart;
    }
    public void setArrivee(Coordonnee arrivee){
        this.arrivee=arrivee;
    }
    public Coordonnee getDepart(){
        return depart;
    }

    public Coordonnee getArrivee(){
        return arrivee;
    }
}
