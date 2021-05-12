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

    public AireDeDessin(Jeu j){
        jeu = j;
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
        drawable.setColor(Color.GRAY);
        drawable.fillRect(0, 0, largeurFenetrePixel, hauteurFenetrePixel);
        tracerTableaux();
    }

    public void tracerTableaux(){
        tracerGrille();
        tracerPion();
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

    void tracerPion() {
        for(int i = 0;i<jeu.getTaille().l;i++)
            for(int j = 0;j<jeu.getTaille().h;j++){
                int k=0;
                Case c =jeu.getCase(j,i);
                if(c.estValide()){
                    Iterateur<Pion> it= c.getIterateur();
                    while (it.aProchain()){
                        k++;
                        Pion impr=it.prochain();
                        if(impr.getCouleur()==1){
                            drawable.setColor(Color.WHITE);
                        }else{
                            drawable.setColor(Color.BLACK);
                        }
                        drawable.fillRect(i* largeurCase+largeurCase/3,j*hauteurCase+hauteurCase/7*k, largeurCase/3, hauteurCase/7);
                    }
                }
            }
    }
}