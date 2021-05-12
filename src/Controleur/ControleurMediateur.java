package Controleur;


import Modele.Jeu;
import Structures.Mouvement;
import Vue.CollecteurEvenements;
import Vue.InterfaceUtilisateur;

public class ControleurMediateur implements CollecteurEvenements {
    Jeu jeu;
    InterfaceUtilisateur jeuint;
    public ControleurMediateur(InterfaceUtilisateur i){
        jeuint=i;
        jeu = i.jeu();
    }
    @Override
    public void clic(int x, int y) {

    }

    @Override
    public void mouvementFini(Mouvement m) {
        System.out.print("Start :");
        System.out.println("x-"+m.getDepart().getL()+" y-"+m.getDepart().getH());
        System.out.print("End :");
        System.out.println("x-"+m.getArrivee().getL()+" y-"+m.getArrivee().getH());
    }
}