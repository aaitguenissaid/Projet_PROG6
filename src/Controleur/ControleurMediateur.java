package Controleur;


import Modele.Jeu;
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
}