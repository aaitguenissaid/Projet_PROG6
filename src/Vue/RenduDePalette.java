package Vue;

import Modele.PaletteDeCouleurs;
import Modele.Pion;
import Structures.Iterateur;

import javax.swing.*;
import java.awt.*;

public class RenduDePalette extends JComponent {
    PaletteDeCouleurs palette;
    Graphics2D drawable;
    RenduDePalette(PaletteDeCouleurs palette){
        this.palette=palette;
    }
    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        int paddingW = (getWidth() - 75) ;
        int paddingH = (getHeight() - 50) / 2;
        drawable.setColor(palette.Couleur5);
        drawable.fillRect(paddingW, paddingH, 75, 25);
        drawable.fillRect(paddingW, paddingH+25, 75, 25);
        drawable.setColor(palette.Couleur4);
        drawable.fillRect(paddingW+5, paddingH+30, 65, 15);
        drawable.setColor(palette.Couleur3);
        drawable.fillRect(paddingW+5, paddingH+5, 65, 15);
    }

}
