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
    Color one,tow,bordure,bg;
    boolean set;
    PionComponent[][] tab;
    public AireDeDessin(Jeu j){
        jeu = j;
        one = Color.decode("#F1F2D8");
        tow = Color.decode("#312E2B");
        bordure = Color.decode("#779556");
        bg = Color.decode("#7AABC7");
        tab = new PionComponent[j.getTaille().h][j.getTaille().l];
        for(int i = 0 ; i< j.getTaille().h;i++){
            for(int k = 0; k<j.getTaille().l;k++){
              tab[i][k]  = new PionComponent(jeu.getCase(i,k));
            }
        }
        set=false;
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
        for (int l = 0; l < largeurGrille; l++) {
            for (int h = 0; h < hauteurGrille; h++) {
                if(tab[h][l].estValide()) {
                    if(!set){
                        tab[h][l].setPionComponent(l*largeurCase+largeurCase/3,h*hauteurCase,hauteurCase/8,largeurCase/3);
                    }
                    this.add(tab[h][l]);
                    if(jeu.estCaseDepart(h,l)){
                        drawable.setColor(Color.CYAN);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(l* largeurCase+5,h*hauteurCase+5, l* largeurCase+5, (h+1)*hauteurCase-5);
                        drawable.drawLine(l* largeurCase+5,h*hauteurCase+5, (l+1)* largeurCase-5, h*hauteurCase+5);
                        drawable.drawLine((l+1)* largeurCase-5,h*hauteurCase+5, (l+1)* largeurCase-5, (h+1)*hauteurCase-5);
                        drawable.drawLine(l* largeurCase+5,(h+1)*hauteurCase-5, (l+1)* largeurCase-5, (h+1)*hauteurCase-5);
                    }else if(jeu.estCaseArrive(h,l)){
                        drawable.setColor(Color.DARK_GRAY);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(l* largeurCase+5,h*hauteurCase+5, l* largeurCase+5, (h+1)*hauteurCase-5);
                        drawable.drawLine(l* largeurCase+5,h*hauteurCase+5, (l+1)* largeurCase-5, h*hauteurCase+5);
                        drawable.drawLine((l+1)* largeurCase-5,h*hauteurCase+5, (l+1)* largeurCase-5, (h+1)*hauteurCase-5);
                        drawable.drawLine(l* largeurCase+5,(h+1)*hauteurCase-5, (l+1)* largeurCase-5, (h+1)*hauteurCase-5);
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
    public void startMove(){
        set = true;
    }
    public void stopMove(){
        set = false;
    }
    public int getLargeurCase(){
        return largeurCase;
    }
    public int getHauteurCase(){
        return hauteurCase;
    }
    public PionComponent getPionComponent(int i , int j){
        return tab[i][j];
    }
}