package Vue;

import Modele.Jeu;
import Modele.PaletteDeCouleurs;

import javax.swing.*;
import java.awt.*;

public class AireDeDessin extends JComponent {

    int largeurFenetrePixel, hauteurFenetrePixel;
    int largeurGrille,hauteurGrille;
    int largeurCase,hauteurCase;
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
              tab[i][k]  = new PionComponent(jeu.getCase(i,k),palette.Couleur3,palette.Couleur4,palette.Couleur5);
            }
        }
        set=false;
    }

    public void setJeu(Jeu j) {
        this.jeu = j;
        for(int i = 0 ; i< j.getTaille().h;i++){
            for(int k = 0; k<j.getTaille().l;k++){
                remove(tab[i][k]);
                tab[i][k]  = new PionComponent(jeu.getCase(i,k),palette.Couleur3,palette.Couleur4,palette.Couleur5);
            }
        }
        set=false;
    }

    @Override
    public void paintComponent(Graphics g) {
        requestFocusInWindow();
        drawable = (Graphics2D) g;
        largeurFenetrePixel = getSize().width-10;
        hauteurFenetrePixel = getSize().height-10;
        largeurGrille=jeu.getTaille().l;
        hauteurGrille=jeu.getTaille().h;
        largeurCase = largeurFenetrePixel / largeurGrille;
        hauteurCase = hauteurFenetrePixel/hauteurGrille;
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
                        tab[h][l].setPionComponent(5+l*largeurCase+largeurCase/3,5+h*hauteurCase,hauteurCase/8,largeurCase/3);
                    }
                    this.add(tab[h][l]);
                    if(jeu.estCaseDepart(h,l)){
                        drawable.setColor(Color.CYAN);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(5+l* largeurCase+5,5+h*hauteurCase+5, 5+l* largeurCase+5, 5+(h+1)*hauteurCase-5);
                        drawable.drawLine(5+l* largeurCase+5,5+h*hauteurCase+5, 5+(l+1)* largeurCase-5, 5+h*hauteurCase+5);
                        drawable.drawLine(5+(l+1)* largeurCase-5,5+h*hauteurCase+5, 5+(l+1)* largeurCase-5, 5+(h+1)*hauteurCase-5);
                        drawable.drawLine(5+l* largeurCase+5,5+(h+1)*hauteurCase-5, 5+(l+1)* largeurCase-5, 5+(h+1)*hauteurCase-5);
                    }else if(jeu.estCaseArrive(h,l)){
                        drawable.setColor(Color.DARK_GRAY);
                        drawable.setStroke(new BasicStroke(5));
                        drawable.drawLine(5+l* largeurCase+5,5+h*hauteurCase+5, 5+l* largeurCase+5, 5+(h+1)*hauteurCase-5);
                        drawable.drawLine(5+l* largeurCase+5,5+h*hauteurCase+5, 5+(l+1)* largeurCase-5, 5+h*hauteurCase+5);
                        drawable.drawLine(5+(l+1)* largeurCase-5,5+h*hauteurCase+5, 5+(l+1)* largeurCase-5, 5+(h+1)*hauteurCase-5);
                        drawable.drawLine(5+l* largeurCase+5,5+(h+1)*hauteurCase-5, 5+(l+1)* largeurCase-5, 5+(h+1)*hauteurCase-5);
                    }
                    drawable.setColor(palette.Couleur5);
                    drawable.setStroke(new BasicStroke(3));
                    drawable.drawLine(5+l* largeurCase,5+h*hauteurCase, 5+l* largeurCase, 5+(h+1)*hauteurCase);
                    drawable.drawLine(5+l* largeurCase,5+h*hauteurCase, 5+(l+1)* largeurCase, 5+h*hauteurCase);
                    drawable.drawLine(5+(l+1)* largeurCase,5+h*hauteurCase, 5+(l+1)* largeurCase, 5+(h+1)*hauteurCase);
                    drawable.drawLine(5+l* largeurCase,5+(h+1)*hauteurCase, 5+(l+1)* largeurCase, 5+(h+1)*hauteurCase);

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