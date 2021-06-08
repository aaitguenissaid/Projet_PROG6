package Modele;

import Global.Configuration;

import java.awt.*;

public class PaletteDeCouleurs {
    public Color CouleurSh1;
    public Color CouleurSh2;
    public Color CouleurSh3;
    public Color CouleurSh4;
    public Color CouleurSh5;
    int curentColorSheme=1;
    public Color Couleur1;
    public Color Couleur2;
    public Color Couleur3;
    public Color Couleur4;
    public Color Couleur5;
    public Color Couleur6;
    public Color Couleur7;
    public PaletteDeCouleurs(){
        CouleurSh1=Color.decode("#4988BF");
        CouleurSh2=Color.decode("#53534C");
        CouleurSh3=Color.decode("#63BBF2");
        CouleurSh4=Color.decode("#20639B");
        CouleurSh5=Color.decode("#222226");
        int scheme = Integer.parseInt(Configuration.instance().get(Configuration.COULEUR_THEME));
        switch (scheme) {
            case 2:
                setSheme2(); break;
            case 3:
                setSheme3(); break;
            case 4:
                setSheme4(); break;
            case 5:
                setSheme5(); break;
            default:
                setSheme1(); break;
        }
    }

    public void setSheme1() {
        curentColorSheme=1;
        Couleur1=Color.decode("#4988BF");
        Couleur2=Color.decode("#84A651");
        Couleur3=Color.decode("#F1F2D8");
        Couleur4=Color.decode("#262422");
        Couleur5=Color.decode("#768C54");
        Couleur6=Color.decode("#4988BF");
        Couleur7= Color.decode("#FFFFFF");
    }

    public void setSheme2() {
        curentColorSheme=2;
        Couleur1=Color.decode("#53534C");
        Couleur2=Color.decode("#F9FBFB");
        Couleur3=Color.decode("#F1CF2F");
        Couleur4=Color.decode("#D92332");
        Couleur5=Color.decode("#232226");
        Couleur6=Color.decode("#FAFAFA");
        Couleur7= Color.decode("#FFFFFF");
    }
    public void setSheme3() {
        curentColorSheme=3;
        Couleur1=Color.decode("#63BBF2");
        Couleur2=Color.decode("#1B3659");
        Couleur3=Color.decode("#AAF2A7");
        Couleur4=Color.decode("#BF1162");
        Couleur5=Color.decode("#594253");
        Couleur6=Color.decode("#4988BF");
        Couleur7= Color.decode("#FFFFFF");
    }
    public void setSheme4() {
        curentColorSheme=4;
        Couleur1=Color.decode("#20639B");
        Couleur2=Color.decode("#3CAEA3");
        Couleur3=Color.decode("#F6D55C");
        Couleur4=Color.decode("#ED553B");
        Couleur5=Color.decode("#173F5F");
        Couleur6=Color.decode("#4988BF");
        Couleur7= Color.decode("#FFFFFF");
    }
    public void setSheme5() {
        curentColorSheme=5;
        Couleur1=Color.decode("#222226");
        Couleur2=Color.decode("#7B44F2");
        Couleur3=Color.decode("#F28705");
        Couleur4=Color.decode("#1763A6");
        Couleur5=Color.decode("#F20505");
        Couleur6=Color.decode("#4988BF");
        Couleur7= Color.decode("#FFFFFF");
    }
    public int colorSheme(){
        return curentColorSheme;
    }
}
