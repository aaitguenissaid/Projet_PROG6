package Modele;

import java.util.ArrayList;

public class Historique {
    Jeu jeu;
    ArrayList<Etat> historique;
    int current;
    int nbCoupsReel;
    boolean navigationOn;

    public Historique(Jeu j) {
        jeu = j;
        historique = new ArrayList<>();
        current = 0;
        nbCoupsReel=0;
        navigationOn = false;
        jeu.navigationHistoriqueActivee = false;
        ajouteEtat(jeu.grille, jeu.tour);
    }

    /*
    * Cette fonction ajoute un état à la position actuelle du jeu
    */
    public void ajouteEtat(Case [][]grille, int tour) {
        if(navigationOn) {
            historique.add(new Etat(grille, tour));
            current = historique.size()-1;
            nbCoupsReel++;
        }
    }

    public boolean aPrecedent() {
        return (historique!=null) && (current-1>=0);
    }

    public Etat precedent() {
        setNavigationOn();
        if(historique==null || current-1<0 || current-1>=historique.size()) return null;
        return historique.get(--current);
    }

    public boolean aSuivant() {
        return !(historique==null) && (current+1 <= historique.size());
    }

    public Etat suivant() {
        if(current==historique.size()-1) setNavigationOff();
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
        jeu.navigationHistoriqueActivee = true;
        navigationOn = true;
    }

    private void setNavigationOff() {
        jeu.navigationHistoriqueActivee = false;
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

    public void annulerNavigation() {
        current = historique.size()-1;
        setNavigationOff();
    }
}
