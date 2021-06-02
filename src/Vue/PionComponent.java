package Vue;

import Modele.Case;
import Modele.Jeu;
import Modele.PaletteDeCouleurs;
import Modele.Pion;
import Structures.Iterateur;

import javax.swing.*;
import java.awt.*;

public class PionComponent extends JComponent {
    Color one,tow,bordure;
    int x,y;
    int width,height;
    int padding;
    Case c;
    Graphics2D drawable;
    Jeu j;
    Point p;
    PaletteDeCouleurs palette;
    PionComponent(Jeu j, Point c, PaletteDeCouleurs palette){
        this.setLocation(new Point(-100,-100));
        this.palette =palette;
        this.j = j;
        p=c;
        this.c =j.getCase(p.x,p.y);
    }
    public void setPionComponent(int x, int y,int height,int width){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.setLocation(new Point(x,y));
        this.setSize(new Dimension(width, 7*height));
        padding=(7*height);
        this.c =j.getCase(p.x,p.y);
    }



    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        int k=c.nbPions()+1;
        Iterateur<Pion> it = c.getIterateur();
        while (it.aProchain()) {
            k--;
            Pion impr = it.prochain();
            if (impr.estCouleur1()) {
                drawable.setColor(palette.Couleur3);
            } else {
                drawable.setColor(palette.Couleur4);
            }
            drawable.fillRect(0, padding-height*k, width, height);
            drawable.setColor(palette.Couleur5);
            drawable.setStroke(new BasicStroke(1));
            drawable.drawLine(0,padding-height*k, 0, padding-height*(k-1));
            drawable.drawLine(width-1,padding-height*k, width-1, padding-height*(k-1));
            drawable.drawLine(0,padding-height*k, width-1, padding-height*k);
            drawable.drawLine(0,padding-height*(k-1), width-1, padding-height*(k-1));
        }
    }
    public boolean estValide(){
        return c.estValide();
    }
    public void movePile(int x,int y){
        this.x = x-this.getWidth()/2;
        this.y = y-this.getHeight()+height/2;
        this.setLocation(new Point(this.x,this.y));
        this.repaint();
    }

    public void setJeu(Jeu j) {
        this.j=j;
    }
}
