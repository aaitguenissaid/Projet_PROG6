package Vue;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdaptateurDeSouri implements MouseListener {
    CollecteurEvenements cc;
    public AdaptateurDeSouri(CollecteurEvenements ctrl) {
        cc = ctrl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        cc.clic(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}