package Vue;

import Structures.Mouvement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdaptateurDeSouri implements MouseListener {
    CollecteurEvenements cc;
    Mouvement m;
    AireDeDessin a;
    public AdaptateurDeSouri(CollecteurEvenements ctrl,AireDeDessin a) {
        cc = ctrl;
        this.a=a;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        m=new Mouvement(new Point((e.getY()-5)/a.getHauteurCase(),(e.getX()-5)/a.getLargeurCase()));
        cc.startMove((e.getY()-5)/a.getHauteurCase(),(e.getX()-5)/a.getLargeurCase());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(m!=null){
            m.setArrivee(new Point((e.getY()-5)/a.getHauteurCase(),(e.getX()-5)/a.getLargeurCase()));
            cc.mouvementFini(m);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}