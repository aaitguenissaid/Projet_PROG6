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
        int all = h.getNbCoups()-1;
        int pos =h.getCurrent();
        int width;
        if(all==0)
            width=getWidth();
        else
            width=getWidth()/all;
        int height=10;
        drawable.setColor(Color.RED);
        drawable.fillRect(0,0,getWidth(),height);
        if((all==0)||(pos==all)){
            drawable.setColor(Color.GREEN);
            drawable.fillRect(0,0,getWidth(),height);
            drawable.setColor(Color.DARK_GRAY);
            drawable.fillRect(getWidth()-3,0,5,height);
        }else{
            drawable.setColor(Color.GREEN);
            drawable.fillRect(0,0,pos*width,height);
            drawable.setColor(Color.DARK_GRAY);
            drawable.fillRect(pos*width-3,0,5,height);
        }
    }
    public void setHistorique(Historique h){
        this.h=h;
    }
}
