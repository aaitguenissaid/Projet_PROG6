package Vue;

import Controleur.ControleurMediateur;
import Global.Configuration;
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
    ClassementScreen classements;
    boolean regBack =false;
    String pageActuelle;

    public void run() {
        pageActuelle = "MAINMENU";
        frame = new JFrame("Jeu Avalam");
        j = new Jeu();
        l=6;h=7;
        ctrl = new ControleurMediateur(this);
        comp = new AireDeDessin(j,ctrl);
        main = new MainMenu(ctrl);
        param = new Parametres(ctrl);
        reg = new Regles(ctrl);
        jeu = new JeuVue(ctrl,comp);
        classements = new ClassementScreen(ctrl);
        comp.addMouseListener(new AdaptateurDeSouri(ctrl,comp));
        comp.addMouseMotionListener(new AdaptateurMouvementDeSouri(ctrl,comp));
        setStatistiques();
        screens = new JPanel(new CardLayout());
        screens.add(main,"MAINMENU");
        screens.add(jeu,"GAMESCREEN");
        screens.add(param,"PARAMETRES");
        screens.add(reg,"REAGLES");
        screens.add(classements,"CLASSEMENT");
        frame.add(screens);
        comp.setFocusable(true);
        comp.addKeyListener(new AdaptateurDeClavier(ctrl));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 700);
        frame.setVisible(true);
        cl = (CardLayout)(screens.getLayout());
    }

    public void setStatistiques() {
        jeu.setNomDeJ1(j.getNomJ1());
        jeu.setNomDeJ2(j.getNomJ2());
        // TODO update score avec les piles a 5 en cas d'egalité.
        jeu.setScoreJ1(String.valueOf(j.getHistorique().getEtatNavigation().nbPilesJoueur(Jeu.COULEUR1)));
        jeu.setScoreJ2(String.valueOf(j.getHistorique().getEtatNavigation().nbPilesJoueur(Jeu.COULEUR2)));
    }

    public Jeu jeu(){
        return j;
    }
    public void setJeu(Jeu j) {
        this.j=j;
        this.comp.setJeu(j);
        setStatistiques();
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
        if(j.estFini()) {
            jeu.allumerRelancerPartie();
            jeu.eteindreAbandonner();
            jeu.eteindreSuggestion();
        } else if(!j.getHistorique().isNavigationOn()) {
            if(Boolean.parseBoolean(Configuration.instance().get(Configuration.EST_AUTORISE_SUGGESTION))) {
                jeu.allumerSuggestion();
            }
            jeu.allumerAbandonner();
            jeu.eteindreRelancerPartie();
        }
        if(j.getTour()==0){
            jeu.setTour(j.getNomJ1(),j.getJ1().couleur==0 ? ctrl.getPalette().Couleur3 :ctrl.getPalette().Couleur4);
        }else{
            jeu.setTour(j.getNomJ2(),j.getJ2().couleur==0 ? ctrl.getPalette().Couleur3 :ctrl.getPalette().Couleur4);
        }
        comp.repaint();
    }
    public AireDeDessin getAireDeDessin(){
        return comp;
    }

    public void setGameScreen(){
        show("GAMESCREEN");
        metAJour();
        regBack=true;
    }
    public void setMainMenu(){
        show("MAINMENU");
        regBack=false;
    }

    public void setClassement() { show("CLASSEMENT"); }
    public void setParametres(){
        show("PARAMETRES");
    }
    public void setReagles(){ show("REAGLES"); }
    public void revalidateInterface(){
        main.initComponents();
        param.setToutCouleurs();
        reg.initComponents();
        jeu.initComponents();
        classements.initComponents();
        setStatistiques();
    }
    public void reaglesBack(){
        if(regBack){
            show("GAMESCREEN");
        }else{
            show("MAINMENU");
        }
    }

    private void show(String nomPage) {
        cl.show(screens, nomPage);
        pageActuelle = nomPage;
    }

    public String getPageActuelle() { return pageActuelle; }

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

    public void pauseBoutonHistorique() {jeu.pauseBoutonHistorique();}
    public void playBoutonHistorique() {jeu.playBoutonHistorique();}
}
