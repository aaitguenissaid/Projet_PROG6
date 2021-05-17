package Vue;

import Modele.Case;
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
    PionComponent(Case c){
        one = Color.decode("#F1F2D8");
        tow = Color.decode("#312E2B");
        bordure = Color.decode("#779556");
        this.c =c;
    }
    public void setPionComponent(int x, int y,int height,int width){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.setLocation(new Point(x,y));
        this.setSize(new Dimension(width, 7*height));
        padding=(7*height);
    }
    @Override
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        int k=c.nbPions()+1;
        Iterateur<Pion> it = c.getIterateur();
        while (it.aProchain()) {
            k--;
            Pion impr = it.prochain();
            if (impr.estBlanc()) {
                drawable.setColor(one);
            } else {
                drawable.setColor(tow);
            }
            drawable.fillRect(0, padding-height*k, width, height);
            drawable.setColor(bordure);
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
        this.y = y-this.getHeight()/2;
        //padding = (int)(1+5-c.nbPions())*height;
        this.setLocation(new Point(this.x,this.y));
        this.repaint();
    }
}
