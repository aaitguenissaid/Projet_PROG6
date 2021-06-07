package Structures;
import Structures.Point;
import java.awt.*;

public class Mouvement {
    Point depart;
    Point arrivee;

    public Mouvement(Point depart, Point arrivee){
        this.depart = depart;
        this.arrivee = arrivee;
    }
    public Mouvement(Point depart){
        this.depart = depart;
    }
    public void setArrivee(Point arrivee){
        this.arrivee=arrivee;
    }

    public Point getDepart(){
        return depart;
    }

    public Point getArrivee(){
        return arrivee;
    }
}
