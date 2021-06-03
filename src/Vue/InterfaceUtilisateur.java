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
    Regles reg;
    JeuVue jeu;
    boolean regBack =false;

    public void run() {
        frame = new JFrame("Jeu Avalam");
        j = new Jeu();
        l=6;h=7;
        ctrl = new ControleurMediateur(this);
        comp = new AireDeDessin(j,ctrl);
        main = new MainMenu(ctrl);
        param = new Parametres(ctrl);
        reg = new Regles(ctrl);
        jeu = new JeuVue(ctrl,comp);
        j.setCollecteurEvenements(ctrl);
        comp.addMouseListener(new AdaptateurDeSouri(ctrl,comp));
        comp.addMouseMotionListener(new AdaptateurMouvementDeSouri(ctrl,comp));
        setStatistiques();
        screens = new JPanel(new CardLayout());
        screens.add(main,"MAINMENU");
        screens.add(jeu,"GAMESCREEN");
        screens.add(param,"PARAMETRES");
        screens.add(reg,"REAGLES");
        frame.add(screens);
        comp.setFocusable(true);
        comp.addKeyListener(new AdaptateurDeClavier(ctrl));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setVisible(true);
        cl = (CardLayout)(screens.getLayout());
    }

    public void setStatistiques() {
        jeu.setNomDeJ1(j.getNomJ1());
        jeu.setNomDeJ2(j.getNomJ2());
        // TODO update score avec les piles a 5 en cas d'egalité.
        jeu.setScoreJ1(String.valueOf(j.nbPilesJoueur(Jeu.COULEUR1)));
        jeu.setScoreJ2(String.valueOf(j.nbPilesJoueur(Jeu.COULEUR2)));
    }

    public Jeu jeu(){
        return j;
    }
    public void setJeu(Jeu j) {
        this.j=j;
        this.comp.setJeu(j);
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
        if(j.getTour()==0){
            jeu.setTour(j.getNomJ1());
        }else{
            jeu.setTour(j.getNomJ2());
        }
        comp.repaint();
    }
    public AireDeDessin getAireDeDessin(){
        return comp;
    }

    public void setGameScreen(){
        cl.show(screens, "GAMESCREEN");
        metAJour();
        regBack=true;
    }
    public void setMainMenu(){
        cl.show(screens, "MAINMENU");
        regBack=false;
    }
    public void setParametres(){
        cl.show(screens, "PARAMETRES");
    }
    public void setReagles(){ cl.show(screens, "REAGLES"); }
    public void revalidateInterface(){
        main.initComponents();
        param.setToutCouleurs();
        reg.initComponents();
        jeu.initComponents();
        setStatistiques();
    }
    public void reaglesBack(){
        if(regBack){
            cl.show(screens, "GAMESCREEN");
        }else{
            cl.show(screens, "MAINMENU");
        }
    }

    public boolean valideAction(String titre, String description, String choix_valider, String choix_annuler) {
        Object[] options = {choix_annuler, choix_valider};
        int n = JOptionPane.showOptionDialog(
                frame,
                description,
                titre,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        return n==1;
    }

    public Object choisirItem(String titre, String description, Object[] items, int message) {
        return JOptionPane.showInputDialog(
                frame,
                description,
                titre,
                message,
                null,
                items,
                (items!=null && items.length>0) ? items[0] : null
        );
    }

    public void informer(String description) {
        JOptionPane.showMessageDialog(frame, description);
    }
}
