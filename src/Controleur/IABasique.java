package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class IABasique extends IA{
    public IABasique(Jeu j, int joueur) {
        super(j, joueur);
    }

    private boolean estJoueur(int i, int j){
        return jeu.getCase(i, j).getTete().getCouleur() == joueur;
    }

    private boolean aPion(int i, int j){
        if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0))
            return true;
        else
            return false;
    }

    private boolean aVoisinJoueur(int i, int j){
        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
        for (int s = 0; s < voisins.size(); s++){
            if (estJoueur(voisins.get(s).x, voisins.get(s).y))
                return true;
        }
        return false;
    }


    private boolean existeAdHateurSup(int i, int j, int hauteur){
        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
        for (int s = 0; s < voisins.size(); s++){
            int l = voisins.get(s).x;
            int h = voisins.get(s).y;
            int ha = jeu.getCase(l, h).nbPions();
            if ((!estJoueur(voisins.get(s).x, voisins.get(s).y))&&(ha <= hauteur))
                return true;
        }
        return false;
    }

    @Override
    public Mouvement joue() {
          /* Règle 1: s'il y a pour une case de couleur d'adversaire et le nombre de ses voisins est 1 et le couleur est
            IABasique, on bouge le tour de pion IABasique sur le tour d'adversaire pour obtenir un point.
            Règle 2 : s'il y un tour de pions d'adversaire avec hauteur x, et un voisin d'IABasique avec hauteur y, et
            x+y == 5, on bouge le tour de IABasique pour obtenir un point.   */
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if (aPion(i, j)){   // if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0))
                    if (!estJoueur(i, j)){  // if (jeu.getCase(i, j).getTete().getCouleur() != joueur)
                        int hauteur = jeu.getCase(i, j).nbPions();
                        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                        if (voisins != null){
                            if ((voisins.size() == 1)&&(estJoueur(voisins.get(0).x, voisins.get(0).y))){
                                return new Mouvement(voisins.get(0), new Point(i, j));
                                // (jeu.getCase(voisins.get(0).x, voisins.get(0).y).getTete().getCouleur() == joueur))
                            }
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).nbPions();
                                if ((hauteurDepart+hauteur == 5)&&(estJoueur(voisins.get(s).x, voisins.get(s).y))){
                                    return new Mouvement(depart, new Point(i, j));
                                }
                            }
                        }

                    }
                }
            }
        }
        IAAleatoire iaA = new IAAleatoire(jeu, joueur);
        return iaA.joue();
    }

}
