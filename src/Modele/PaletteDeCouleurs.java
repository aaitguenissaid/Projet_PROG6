package Modele;

import java.awt.*;

public class PaletteDeCouleurs {
    int curentColorSheme=1;
    public Color Couleur1;
    public Color Couleur2;
    public Color Couleur3;
    public Color Couleur4;
    public Color Couleur5;
    public PaletteDeCouleurs(){
        setSheme1();
    }

    public void setSheme1() {
        curentColorSheme=1;
        Couleur1=Color.decode("#4988BF");
        Couleur2=Color.decode("#84A651");
        Couleur3=Color.decode("#F1F2D8");
        Couleur4=Color.decode("#262422");
        Couleur5=Color.decode("#768C54");
    }

    public void setSheme2() {
        curentColorSheme=2;
        Couleur1=Color.decode("#53534C");
        Couleur2=Color.decode("#F9FBFB");
        Couleur3=Color.decode("#F1CF2F");
        Couleur4=Color.decode("#D92332");
        Couleur5=Color.decode("#232226");
    }
    public void setSheme3() {
        curentColorSheme=3;
        Couleur1=Color.decode("#63BBF2");
        Couleur2=Color.decode("#1B3659");
        Couleur3=Color.decode("#AAF2A7");
        Couleur4=Color.decode("#BF1162");
        Couleur5=Color.decode("#594253");
    }
    public void setSheme4() {
        curentColorSheme=4;
        Couleur1=Color.decode("#20639B");
        Couleur2=Color.decode("#3CAEA3");
        Couleur3=Color.decode("#F6D55C");
        Couleur4=Color.decode("#ED553B");
        Couleur5=Color.decode("#173F5F");
    }
    public void setSheme5() {
        curentColorSheme=5;
        Couleur1=Color.decode("#222226");
        Couleur2=Color.decode("#7B44F2");
        Couleur3=Color.decode("#F28705");
        Couleur4=Color.decode("#1763A6");
        Couleur5=Color.decode("#F20505");
    }
    public int colorSheme(){
        return curentColorSheme;
    }
}
