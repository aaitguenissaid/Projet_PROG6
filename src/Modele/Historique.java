package Modele;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Historique implements Cloneable {
    Jeu jeu;
    ArrayList<Etat> historique;
    int current;
    int nbCoupsReel;
    boolean navigationOn;

    public Historique(Jeu j) {
        this(j, true);
    }

    public Historique(Jeu j, boolean createInitialState) {
        jeu = j;
        historique = new ArrayList<>();
        current = 0;
        nbCoupsReel=0;
        navigationOn = false;
        if(createInitialState) {
            ajouteEtat(jeu.grille, jeu.tour);
        }
    }

    /*
    * Cette fonction ajoute un état à la position actuelle du jeu
    */
    public void ajouteEtat(Case [][]grille, int tour) {
        if(!navigationOn) {
            historique.add(new Etat(grille, tour));
            current = historique.size()-1;
            nbCoupsReel++;
        }
    }

    public Etat getEtatNavigation() {
        if(historique==null || current<0 || current>historique.size()) return null;
        return historique.get(current);
    }

    public boolean aPrecedent() {
        return (historique!=null) && (current-1>=0);
    }

    public Etat precedent() {
        if(historique==null || current-1<0 || current-1>=historique.size()) return null;
        setNavigationOn();
        return historique.get(--current);
    }

    public boolean aSuivant() {
        return (historique!=null) && (current+1 < historique.size());
    }

    public Etat suivant() {
        if(current==historique.size()-2) setNavigationOff();
        if(historique==null || current+1<0 || current+1>=historique.size()) return null;
        return historique.get(++current);
    }

    public int getNbCoups() {
        return historique.size();
    }

    public int getNbCoupsReel() {
        return nbCoupsReel;
    }

    private void setNavigationOn() {
        navigationOn = true;
    }

    private void setNavigationOff() {
        navigationOn = false;
    }

    public void validerNavigation() {
        if(current<historique.size()-1 && current>=0) {
            for (int i = current+1; i<historique.size(); i++) {
                //On supprime le dernier élément de la liste à chaque itération
                historique.remove(historique.size()-1);
            }
        }
        jeu.grille = historique.get(current).getCopieGrille();
        jeu.setTour(historique.get(current).getTour());
        setNavigationOff();
    }

    //Rejoins l'etat actuel du Jeu
    public Etat atteindreFinHistorique() {
        if(historique.size()==0) return null;
        current = historique.size()-1;
        setNavigationOff();
        return historique.get(current);
    }

    //Rejoins le premier etat de la partie
    public Etat atteindreDebutHistorique() {
        if(historique.size()==0) return null;
        setNavigationOn();
        current = 0;
        return historique.get(current);
    }

    public void supprimeTete() {
        if(historique!=null && historique.size()>0) {
            historique.remove(historique.size()-1);
        }
    }

    public boolean isNavigationOn() {
        return this.navigationOn;
    }

    public void print(PrintWriter out) {
        out.println(nbCoupsReel);
        out.println(historique.size());
        for(int i=0; i<historique.size()-1; i++) {
            historique.get(i).print(out);
        }
    }

    @Override
    public Object clone() {
        Historique ret = new Historique(jeu, false);
        for(int i=0; i<historique.size(); i++) {
            ret.ajouteEtat(historique.get(i).grille, historique.get(i).getTour());
        }
        ret.nbCoupsReel = nbCoupsReel;
        ret.current = current;
        ret.navigationOn = navigationOn;
        return ret;
    }
}
