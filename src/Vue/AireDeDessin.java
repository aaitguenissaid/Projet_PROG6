package Vue;

import Modele.Jeu;

import javax.swing.*;
import java.awt.*;

public class AireDeDessin extends JComponent {

    int largeurFenetrePixel, hauteurFenetrePixel;
    int largeurGrille,hauteurGrille;
    int largeurCase,hauteurCase;
    Graphics2D drawable;
    Jeu jeu;

    public AireDeDessin(Jeu j){
        jeu = j;
    }

    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        largeurFenetrePixel = getSize().width;
        hauteurFenetrePixel = getSize().height;
        largeurCase = largeurFenetrePixel / largeurGrille;
        hauteurCase = hauteurFenetrePixel/hauteurGrille;
        //en soit mise en place Jeu

        drawable.clearRect(0, 0, largeurFenetrePixel, hauteurFenetrePixel);
        tracerTableaux();
    }

    public void tracerTableaux(){
    }

    void tracerGrille() {
        for (int l = 0; l <= largeurGrille; l++) {
            drawable.setColor(Color.BLACK);
            drawable.setStroke(new BasicStroke(3));
            drawable.drawLine(l* largeurCase,0, l* largeurCase, hauteurFenetrePixel);
        }
        for (int l = 0; l <= hauteurGrille; l++) {
            drawable.setColor(Color.BLACK);
            drawable.setStroke(new BasicStroke(3));
            drawable.drawLine(0,hauteurCase*l, largeurFenetrePixel,hauteurCase*l);
        }
    }

    void traceCaseMange(int l, int c) {
        int value = jeu.contenu(l, c);

        if(value==17 || value==16+8)
            drawable.setColor(Color.GREEN);
        else if (value==33 || value==32+8)
            drawable.setColor(Color.BLUE);

        drawable.fillRect(c* largeurCase,l*hauteurCase, largeurCase, hauteurCase);
    }

    void tracePoison(int l, int c) {
        drawable.setColor(Color.RED);
        drawable.fillOval(l* largeurCase + largeurCase /8, c*hauteurCase+hauteurCase/8, largeurCase - largeurCase /4, hauteurCase-hauteurCase/4);
    }


}