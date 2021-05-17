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
        /* A - activer Joueur A
         B - activer Joueur B
         C- activer les deux comme IA
         F - full screen        */
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R -> cc.taper(1,1);
            default ->{
            }

            //throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}