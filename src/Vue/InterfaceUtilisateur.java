package Vue;

import Controleur.ControleurMediateur;
import Modele.Jeu;
import javax.swing.*;
import java.awt.*;

public class InterfaceUtilisateur implements Runnable {
    JPanel screens;
    AireDeDessin comp;
    JFrame frame;
    boolean maximized;
    Jeu j;
    int l,h;
    MainMenu main;
    Parametres param;
    CollecteurEvenements ctrl;
    CardLayout cl;


    public void run() {
        frame = new JFrame("Jeu gaufre");
        j = new Jeu();
        l=6;h=7;
        ctrl = new ControleurMediateur(this);
        j.setCollecteurEvenements(ctrl);
        comp = new AireDeDessin(j,ctrl);
        main = new MainMenu(ctrl);
        param = new Parametres(ctrl);
        comp.addMouseListener(new AdaptateurDeSouri(ctrl,comp));
        comp.addMouseMotionListener(new AdaptateurMouvementDeSouri(ctrl,comp));
        screens = new JPanel(new CardLayout());
        screens.add(main,"MAINMENU");
        screens.add(comp,"GAMESCREEN");
        screens.add(param,"PARAMETRES");
        frame.add(screens);
        comp.setFocusable(true);
        comp.addKeyListener(new AdaptateurDeClavier(ctrl));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setVisible(true);
        cl = (CardLayout)(screens.getLayout());
    }
    public Jeu jeu(){
        return j;
    }

    public void basculePleinEcran() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (maximized) {
            device.setFullScreenWindow(null);
            maximized = false;
        } else {
            device.setFullScreenWindow(frame);
            maximized = true;
        }
    }
    public JFrame getFrame() {
        return frame;
    }
    public void metAJour() {
        comp.repaint();
    }
    public AireDeDessin getAireDeDessin(){
        return comp;
    }

    public void setGameScreen(){
        cl.show(screens, "GAMESCREEN");
        metAJour();
    }
    public void setMainMenu(){
        cl.show(screens, "MAINMENU");
    }
    public void setParametres(){
        cl.show(screens, "PARAMETRES");
    }
}
