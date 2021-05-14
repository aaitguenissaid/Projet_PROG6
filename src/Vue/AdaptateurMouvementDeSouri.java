package Vue;

import Structures.Mouvement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AdaptateurMouvementDeSouri implements MouseMotionListener {
    CollecteurEvenements cc;
    Mouvement m;
    AireDeDessin a;
    public AdaptateurMouvementDeSouri(CollecteurEvenements ctrl,AireDeDessin a) {
        cc = ctrl;
        this.a=a;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        cc.movePionTo(new Point(e.getX(),e.getY()));
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}