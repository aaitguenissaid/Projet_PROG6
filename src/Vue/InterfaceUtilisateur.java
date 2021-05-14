package Vue;

import Controleur.ControleurMediateur;
import Modele.Jeu;

import javax.swing.*;
import java.awt.*;

public class InterfaceUtilisateur implements Runnable {
    static AireDeDessin comp;
    JFrame frame;
    boolean maximized;
    Jeu j;
    int l,h;
    CollecteurEvenements ctrl;


    public void run() {
        frame = new JFrame("Jeu gaufre");
        j = new Jeu();
        l=6;h=7;
        comp = new AireDeDessin(j);
        ctrl = new ControleurMediateur(this);
        comp.addMouseListener(new AdaptateurDeSouri(ctrl,comp));
        comp.addMouseMotionListener(new AdaptateurMouvementDeSouri(ctrl,comp));
        frame.add(comp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setVisible(true);
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
}
