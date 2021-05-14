package Vue;

import Modele.Case;
import Modele.Jeu;
import Modele.Pion;
import Structures.Iterateur;

import javax.swing.*;
import java.awt.*;

public class AireDeDessin extends JComponent {

    int largeurFenetrePixel, hauteurFenetrePixel;
    int largeurGrille,hauteurGrille;
    int largeurCase,hauteurCase;
    Graphics2D drawable;
    Jeu jeu;
    Color one,tow,bordure,bg;
    public AireDeDessin(Jeu j){
        jeu = j;
        one = Color.decode("#F1F2D8");
        tow = Color.decode("#312E2B");
        bordure = Color.decode("#779556");
        bg = Color.decode("#7AABC7");
    }

    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        largeurFenetrePixel = getSize().width;
        hauteurFenetrePixel = getSize().height;
        largeurGrille=jeu.getTaille().l;
        hauteurGrille=jeu.getTaille().h;
        largeurCase = largeurFenetrePixel / largeurGrille;
        hauteurCase = hauteurFenetrePixel/hauteurGrille;
        drawable.setColor(bg);
        drawable.fillRect(0, 0, largeurFenetrePixel, hauteurFenetrePixel);
        tracerTableaux();
    }

    public void tracerTableaux(){
        tracerGrille();
    }


    void tracerGrille() {
        int k;
        for (int l = 0; l < largeurGrille; l++) {
            for (int h = 0; h < hauteurGrille; h++) {
                k=0;
                Case c =jeu.getCase(l,h);
                if(c.estValide()) {
                    Iterateur<Pion> it = c.getIterateur();
                    while (it.aProchain()) {
                        k++;
                        Pion impr = it.prochain();
                        if (impr.estBlanc()) {
                            drawable.setColor(one);
                        } else {
                            drawable.setColor(tow);
                        }
                        drawable.fillRect(l * largeurCase + largeurCase / 3, h * hauteurCase + hauteurCase / 7 * k, largeurCase / 3, hauteurCase / 7);
                        drawable.setColor(bordure);
                        drawable.setStroke(new BasicStroke(1));
                        drawable.drawLine(l * largeurCase + largeurCase / 3,h * hauteurCase + hauteurCase / 7 * k, l * largeurCase + largeurCase / 3, h * hauteurCase + hauteurCase / 7 *(k+1));
                        drawable.drawLine(l * largeurCase + largeurCase / 3*2,h * hauteurCase + hauteurCase / 7 * k, l * largeurCase + largeurCase / 3*2, h * hauteurCase + hauteurCase / 7 *(k+1));
                        drawable.drawLine(l * largeurCase + largeurCase / 3,h * hauteurCase + hauteurCase / 7 * k, l * largeurCase + largeurCase / 3*2, h * hauteurCase + hauteurCase / 7 *k);
                        drawable.drawLine(l * largeurCase + largeurCase / 3,h * hauteurCase + hauteurCase / 7 * (k+1), l * largeurCase + largeurCase / 3*2, h * hauteurCase + hauteurCase / 7 *(k+1));
                    }
                    drawable.setColor(bordure);
                    drawable.setStroke(new BasicStroke(3));
                    drawable.drawLine(l* largeurCase,h*hauteurCase, l* largeurCase, (h+1)*hauteurCase);
                    drawable.drawLine(l* largeurCase,h*hauteurCase, (l+1)* largeurCase, h*hauteurCase);
                    drawable.drawLine((l+1)* largeurCase,h*hauteurCase, (l+1)* largeurCase, (h+1)*hauteurCase);
                    drawable.drawLine(l* largeurCase,(h+1)*hauteurCase, (l+1)* largeurCase, (h+1)*hauteurCase);
                }
            }
    }
    }
    public int getLargeurCase(){
        return largeurCase;
    }
    public int getHauteurCase(){
        return hauteurCase;
    }
}