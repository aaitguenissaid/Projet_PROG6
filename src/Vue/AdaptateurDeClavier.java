package Vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdaptateurDeClavier implements KeyListener {
    CollecteurEvenements cc;
    public AdaptateurDeClavier(CollecteurEvenements ctrl){
        cc= ctrl;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                cc.commande("ActiveA");
                break;
            case KeyEvent.VK_B:
                cc.commande("ActiveB");
                break;
            case KeyEvent.VK_ESCAPE:
                cc.commande("fullscreen");
                break;
            default:
                break; //On ignore

            //throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}