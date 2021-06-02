package Vue;

import Modele.Jeu;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;

public class AireDeDessin extends JComponent {

    int largeurFenetrePixel, hauteurFenetrePixel;
    int largeurGrille,hauteurGrille;
    int largeurCase,hauteurCase;
    int paddingW;
    int paddingH;
    Graphics2D drawable;
    Jeu jeu;
    boolean set;
    PionComponent[][] tab;
    PaletteDeCouleurs palette;
    public AireDeDessin(Jeu j, CollecteurEvenements ctrl){
        jeu = j;
        palette = ctrl.getPalette();
        tab = new PionComponent[j.getTaille().h][j.getTaille().l];
        for(int i = 0 ; i< j.getTaille().h;i++){
            for(int k = 0; k<j.getTaille().l;k++){
              tab[i][k]  = new PionComponent(j,new Point(i,k),palette);
            }
        }
        set=false;
    }

    public void setJeu(Jeu j) {
        this.jeu = j;
        for(int i = 0 ; i< j.getTaille().h;i++){
            for(int k = 0; k<j.getTaille().l;k++){
                tab[i][k].setJeu(j);
            }
        }
        set=false;
    }

    @Override
    public void paintComponent(Graphics g) {
        requestFocusInWindow();
        drawable = (Graphics2D) g;
        largeurFenetrePixel = getSize().width-20;
        hauteurFenetrePixel = getSize().height-20;

        largeurGrille=jeu.getTaille().l;
        hauteurGrille=jeu.getTaille().h;
        largeurCase = largeurFenetrePixel / largeurGrille;
        hauteurCase = hauteurFenetrePixel/hauteurGrille;
        if(largeurCase>hauteurCase){
            largeurCase=hauteurCase;
        }else {
            hauteurCase=largeurCase;
        }
        paddingW =(getSize().width-largeurCase*largeurGrille)/2;
        paddingH =(getSize().height-hauteurCase*hauteurGrille)/2;
        drawable.setColor(palette.Couleur1);
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
                        tab[h][l].setPionComponent(paddingW+l*largeurCase+largeurCase/3,paddingH+h*hauteurCase,hauteurCase/8,largeurCase/3);
                    }
                    this.add(tab[h][l]);
                    if(jeu.estCaseDepart(h,l)){
                        drawable.setColor(Color.CYAN);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+l* largeurCase+5, paddingH+(h+1)*hauteurCase-5);
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+h*hauteurCase+5);
                        drawable.drawLine(paddingW+(l+1)* largeurCase-5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+(h+1)*hauteurCase-5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
                    }else if(jeu.estCaseArrive(h,l)){
                        drawable.setColor(Color.DARK_GRAY);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+l* largeurCase+5, paddingH+(h+1)*hauteurCase-5);
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+h*hauteurCase+5);
                        drawable.drawLine(paddingW+(l+1)* largeurCase-5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
                        drawable.drawLine(paddingW+l* largeurCase+5,paddingH+(h+1)*hauteurCase-5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
                    }
                    drawable.setColor(palette.Couleur5);
                    drawable.setStroke(new BasicStroke(3));
                    drawable.drawLine(paddingW+l* largeurCase,paddingH+h*hauteurCase, paddingW+l* largeurCase, paddingH+(h+1)*hauteurCase);
                    drawable.drawLine(paddingW+l* largeurCase,paddingH+h*hauteurCase, paddingW+(l+1)* largeurCase, paddingH+h*hauteurCase);
                    drawable.drawLine(paddingW+(l+1)* largeurCase,paddingH+h*hauteurCase, paddingW+(l+1)* largeurCase, paddingH+(h+1)*hauteurCase);
                    drawable.drawLine(paddingW+l* largeurCase,paddingH+(h+1)*hauteurCase, paddingW+(l+1)* largeurCase, paddingH+(h+1)*hauteurCase);
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
    public int getPaddingW(){return paddingW;}
    public int getPaddingH(){return paddingH;}
    public int getHauteurCase(){
        return hauteurCase;
    }
    public PionComponent getPionComponent(int i , int j){
        return tab[i][j];
    }
}