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
        Couleur1=Color.decode("#A1A0A2");
        Couleur2=Color.decode("#F2F2F2");
        Couleur3=Color.decode("#F2B90C");
        Couleur4=Color.decode("#BF1B28");
        Couleur5=Color.decode("#232226");
        Couleur6=Color.decode("#A1A0A2");
        Couleur7= Color.decode("#232226");
    }
    public void setSheme3() {
        curentColorSheme=3;
        Couleur1=Color.decode("#63BBF2");
        Couleur2=Color.decode("#0796D9");
        Couleur3=Color.decode("#24F24A");
        Couleur4=Color.decode("#F2002A");
        Couleur5=Color.decode("#594253");
        Couleur6=Color.decode("#63BBF2");
        Couleur7= Color.decode("#232226");
    }
    public void setSheme4() {
        curentColorSheme=4;
        Couleur1=Color.decode("#20639B");
        Couleur2=Color.decode("#035948");
        Couleur3=Color.decode("#F6D55C");
        Couleur4=Color.decode("#ED553B");
        Couleur5=Color.decode("#173F5F");
        Couleur6=Color.decode("#20639B");
        Couleur7= Color.decode("#FFFFFF");
    }
    public void setSheme5() {
        curentColorSheme=5;
        Couleur1=Color.decode("#222226");
        Couleur2=Color.decode("#6182C2");
        Couleur3=Color.decode("#F28705");
        Couleur4=Color.decode("#1763A6");
        Couleur5=Color.decode("#035948");
        Couleur6=Color.decode("#222226");
        Couleur7= Color.decode("#FFFFFF");
    }
    public int colorSheme(){
        return curentColorSheme;
    }
}
