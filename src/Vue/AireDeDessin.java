package Vue;

import Global.Configuration;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Structures.Mouvement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AireDeDessin extends JComponent {

    int largeurFenetrePixel, hauteurFenetrePixel;
    int largeurGrille,hauteurGrille;
    public int largeurCase;
    public int hauteurCase;
    public int paddingW;
    public int paddingH;
    Graphics2D drawable;
    Jeu jeu;
    boolean set;
    public PionComponent[][] tab;
    PaletteDeCouleurs palette;
    CollecteurEvenements cc;
    public AireDeDessin(Jeu j, CollecteurEvenements ctrl){
        jeu = j;
        cc=ctrl;
        palette = ctrl.getPalette();
        tab = new PionComponent[j.getTaille().h][j.getTaille().l];
        for(int i = 0 ; i< j.getTaille().h;i++){
            for(int k = 0; k<j.getTaille().l;k++){
              tab[i][k]  = new PionComponent(j,new Point(i,k),palette,i,k);
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
        if(jeu.estFini())
            hauteurFenetrePixel = getSize().height-20 -50;
        else
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
        if(jeu.estFini())
            paddingH =(getSize().height-hauteurCase*hauteurGrille)/2-25;
        else
            paddingH =(getSize().height-hauteurCase*hauteurGrille)/2;
        drawable.setColor(palette.Couleur1);
        drawable.fillRect(0, 0, largeurFenetrePixel, hauteurFenetrePixel);
        tracerGrille();
        if((jeu.lastDepJ>=0)&&(jeu.lastDepI>=0)&&(jeu.lastArrJ>=0)&&(jeu.lastArrI>=0))
            tracerLastMove(jeu.lastDepJ,jeu.lastDepI,jeu.lastArrJ,jeu.lastArrI);
        if(cc.suggestion() && Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION))) {
            suggestion(cc.suggestionMouvement());
        }
    }



    void tracerGrille() {
        if (jeu.estFini())
            tracerResultat();
        for (int l = 0; l < largeurGrille; l++) {
            for (int h = 0; h < hauteurGrille; h++) {
                if(tab[h][l].estValide()) {
                    if(!set){
                        tab[h][l].setPionComponent(paddingW+l*largeurCase+largeurCase/3,paddingH+h*hauteurCase,hauteurCase/8,largeurCase/3);
                    }
                    this.add(tab[h][l]);
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
    void tracerArrow(int mode,Dimension from,Dimension to){
        if(mode==1){
            drawable.setColor(Color.CYAN);
        }else{
            drawable.setColor(Color.DARK_GRAY);
        }
        drawable.setStroke(new BasicStroke(3));
        if((from.width<to.width)&&(from.height<to.height)){//Done
            drawable.drawLine(paddingW+(from.width+1)* largeurCase-largeurCase/5,paddingH+(from.height+1)*hauteurCase-largeurCase/5, paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5-5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/5-5, paddingH+to.height*hauteurCase+largeurCase/5);
        }else if((from.width>to.width)&&(from.height>to.height)){//Done
            drawable.drawLine(paddingW+from.width* largeurCase+largeurCase/5,paddingH+from.height*hauteurCase+largeurCase/5, paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+(to.height+1)*hauteurCase-largeurCase/5);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5-1, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1, paddingW+(to.width+1)* largeurCase-largeurCase/5-1, paddingH+(to.height+1)*hauteurCase-largeurCase/5+5-1);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5-1, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1, paddingW+(to.width+1)* largeurCase-largeurCase/5+5-1, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1);
        }else if((from.width<to.width)&&(from.height>to.height)){//Done
            drawable.drawLine(paddingW+(from.width+1)* largeurCase-largeurCase/5,paddingH+from.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/5, paddingH+(to.height+1)*hauteurCase-largeurCase/5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1, paddingW+to.width* largeurCase+largeurCase/5-5, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+(to.height+1)*hauteurCase-largeurCase/5-1, paddingW+to.width* largeurCase+largeurCase/5, paddingH+(to.height+1)*hauteurCase-largeurCase/5+5-1);
        }else if((from.width>to.width)&&(from.height<to.height)){//Done
            drawable.drawLine(paddingW+from.width* largeurCase+largeurCase/5,paddingH+(from.height+1)*hauteurCase-largeurCase/5, paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5-1, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+(to.width+1)* largeurCase-largeurCase/5-1, paddingH+to.height*hauteurCase+largeurCase/5-5);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+(to.width+1)* largeurCase-largeurCase/5+5, paddingH+to.height*hauteurCase+largeurCase/5);
        }else if((from.width>to.width)&&(from.height==to.height)){//Done
            drawable.drawLine(paddingW+from.width* largeurCase+largeurCase/5,paddingH+from.height*hauteurCase+largeurCase/2, paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2, paddingW+(to.width+1)* largeurCase-largeurCase/5+5, paddingH+to.height*hauteurCase+largeurCase/2+6);
            drawable.drawLine(paddingW+(to.width+1)* largeurCase-largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2, paddingW+(to.width+1)* largeurCase-largeurCase/5+5, paddingH+to.height*hauteurCase+largeurCase/2-5);
        }else if((from.width==to.width)&&(from.height>to.height)){//Done
            drawable.drawLine(paddingW+from.width* largeurCase+largeurCase/2,paddingH+from.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2, paddingH+(to.height+1)*hauteurCase-largeurCase/5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/2, paddingH+(to.height+1)*hauteurCase-largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2+5, paddingH+(to.height+1)*hauteurCase-largeurCase/5+5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/2, paddingH+(to.height+1)*hauteurCase-largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2-5, paddingH+(to.height+1)*hauteurCase-largeurCase/5+5);
        }else if((from.width<to.width)&&(from.height==to.height)){//Done
            drawable.drawLine(paddingW+(from.width+1)* largeurCase-largeurCase/5,paddingH+from.height*hauteurCase+largeurCase/2, paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2, paddingW+to.width* largeurCase+largeurCase/5-5, paddingH+to.height*hauteurCase+largeurCase/2-5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/5, paddingH+to.height*hauteurCase+largeurCase/2, paddingW+to.width* largeurCase+largeurCase/5-5, paddingH+to.height*hauteurCase+largeurCase/2+5);
        }else if((from.width==to.width)&&(from.height<to.height)){//
            drawable.drawLine(paddingW+from.width* largeurCase+largeurCase/2,paddingH+(from.height+1)*hauteurCase-largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2, paddingH+to.height*hauteurCase+largeurCase/5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/2+1, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2+5+1, paddingH+to.height*hauteurCase+largeurCase/5-5);
            drawable.drawLine(paddingW+to.width* largeurCase+largeurCase/2, paddingH+to.height*hauteurCase+largeurCase/5, paddingW+to.width* largeurCase+largeurCase/2-5, paddingH+to.height*hauteurCase+largeurCase/5-5);
        }
    }
    void tracerLastMove(int l,int h,int i,int j){
            /*drawable.setColor(Color.CYAN);
            drawable.setStroke(new BasicStroke(5));
            drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+l* largeurCase+5, paddingH+(h+1)*hauteurCase-5);
            drawable.drawLine(paddingW+l* largeurCase+5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+h*hauteurCase+5);
            drawable.drawLine(paddingW+(l+1)* largeurCase-5,paddingH+h*hauteurCase+5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
            drawable.drawLine(paddingW+l* largeurCase+5,paddingH+(h+1)*hauteurCase-5, paddingW+(l+1)* largeurCase-5, paddingH+(h+1)*hauteurCase-5);
            drawable.setColor(Color.DARK_GRAY);
            drawable.setStroke(new BasicStroke(5));
            drawable.drawLine(paddingW+i* largeurCase+5,paddingH+j*hauteurCase+5, paddingW+i* largeurCase+5, paddingH+(j+1)*hauteurCase-5);
            drawable.drawLine(paddingW+i* largeurCase+5,paddingH+j*hauteurCase+5, paddingW+(i+1)* largeurCase-5, paddingH+j*hauteurCase+5);
            drawable.drawLine(paddingW+(i+1)* largeurCase-5,paddingH+j*hauteurCase+5, paddingW+(i+1)* largeurCase-5, paddingH+(j+1)*hauteurCase-5);
            drawable.drawLine(paddingW+i* largeurCase+5,paddingH+(j+1)*hauteurCase-5, paddingW+(i+1)* largeurCase-5, paddingH+(j+1)*hauteurCase-5);
            */
            tracerArrow(1,new Dimension (l,h),new Dimension(i,j));

    }
    void tracerResultat(){
        Font font = new Font("Ubuntu", Font.BOLD, 24);
        drawable.setFont(font);
        drawable.setColor(palette.Couleur7);
        String nom, text;
        if(jeu.partieAbandonnee) {
            nom = (jeu.getTour()==Jeu.COULEUR1) ? jeu.getNomJ2() : jeu.getNomJ1();
            text="Le joueur "+ nom +" gagne";
        } else {
            int gagnant = jeu.quiAGagnee();
            if (gagnant == 0) {
                text = "Egalité";
            } else {
                if (gagnant == 1) nom = jeu.getNomJ1();
                else nom = jeu.getNomJ2();
                text = "Le joueur " + nom + " gagne !";
            }
        }
        FontMetrics metrics = drawable.getFontMetrics(font);
        int x = (largeurFenetrePixel - metrics.stringWidth(text)) / 2;
        int y = getHeight() - metrics.getHeight() ;
        drawable.setFont(font);
        drawable.drawString(text, x, y);
    }
    public void suggestion(Mouvement m){
  /*      drawable.setColor(Color.CYAN);
        drawable.setStroke(new BasicStroke(5));
        drawable.drawLine(paddingW+m.getDepart().y* largeurCase+5,paddingH+m.getDepart().x*hauteurCase+5, paddingW+m.getDepart().y* largeurCase+5, paddingH+(m.getDepart().x+1)*hauteurCase-5);
        drawable.drawLine(paddingW+m.getDepart().y* largeurCase+5,paddingH+m.getDepart().x*hauteurCase+5, paddingW+(m.getDepart().y+1)* largeurCase-5, paddingH+m.getDepart().x*hauteurCase+5);
        drawable.drawLine(paddingW+(m.getDepart().y+1)* largeurCase-5,paddingH+m.getDepart().x*hauteurCase+5, paddingW+(m.getDepart().y+1)* largeurCase-5, paddingH+(m.getDepart().x+1)*hauteurCase-5);
        drawable.drawLine(paddingW+m.getDepart().y* largeurCase+5,paddingH+(m.getDepart().x+1)*hauteurCase-5, paddingW+(m.getDepart().y+1)* largeurCase-5, paddingH+(m.getDepart().x+1)*hauteurCase-5);
        drawable.setColor(Color.DARK_GRAY);
        drawable.setStroke(new BasicStroke(5));
        drawable.drawLine(paddingW+m.getArrivee().y* largeurCase+5,paddingH+m.getArrivee().x*hauteurCase+5, paddingW+m.getArrivee().y* largeurCase+5, paddingH+(m.getArrivee().x+1)*hauteurCase-5);
        drawable.drawLine(paddingW+m.getArrivee().y* largeurCase+5,paddingH+m.getArrivee().x*hauteurCase+5, paddingW+(m.getArrivee().y+1)* largeurCase-5, paddingH+m.getArrivee().x*hauteurCase+5);
        drawable.drawLine(paddingW+(m.getArrivee().y+1)* largeurCase-5,paddingH+m.getArrivee().x*hauteurCase+5, paddingW+(m.getArrivee().y+1)* largeurCase-5, paddingH+(m.getArrivee().x+1)*hauteurCase-5);
        drawable.drawLine(paddingW+m.getArrivee().y* largeurCase+5,paddingH+(m.getArrivee().x+1)*hauteurCase-5, paddingW+(m.getArrivee().y+1)* largeurCase-5, paddingH+(m.getArrivee().x+1)*hauteurCase-5);
*/
        tracerArrow(2,new Dimension(m.getDepart().y,m.getDepart().x),new Dimension(m.getArrivee().y,m.getArrivee().x));
    }
    public void animatePion(int k,int l, int x, int y){
        tab[k][l].movePile(x,y);
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