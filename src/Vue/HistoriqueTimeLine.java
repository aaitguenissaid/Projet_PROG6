package Vue;

import Modele.Historique;

import javax.swing.*;
import java.awt.*;

public class HistoriqueTimeLine extends JComponent {
    Historique h;
    Graphics2D drawable;
    HistoriqueTimeLine(Historique h){
        this.h=h;
    }
    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;
        int all = h.getNbCoupsReel();
        int pos =h.getCurrent();
        int width=getWidth()/all;
        int height=10;
        drawable.setColor(Color.CYAN);
        drawable.fillRect(0,0,pos*width,height);
        drawable.setColor(Color.DARK_GRAY);
        drawable.fillRect(pos*width,0,width,height);
        drawable.setColor(Color.GREEN);
        drawable.fillRect((pos+1)*width,0,width*(all-pos),height);
    }
}
