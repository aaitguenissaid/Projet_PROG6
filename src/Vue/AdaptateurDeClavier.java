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
            case KeyEvent.VK_R -> cc.clic(1,1);
            default ->{
            }

            //throw new IllegalStateException("Unexpected value: " + e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}