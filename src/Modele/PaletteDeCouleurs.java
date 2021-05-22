package Modele;

import java.awt.*;

public class PaletteDeCouleurs {
    public Color Couleur1;
    public Color Couleur2;
    public Color Couleur3;
    public Color Couleur4;
    public Color Couleur5;
    public PaletteDeCouleurs(){
        init_defult_coleur();
    }

    public void init_defult_coleur() {
        Couleur1=Color.decode("#4988BF");
        Couleur2=Color.decode("#84A651");
        Couleur3=Color.decode("#F1F2D8");
        Couleur4=Color.decode("#262422");
        Couleur5=Color.decode("#768C54");
    }
}
