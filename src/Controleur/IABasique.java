package Controleur;

import Modele.Jeu;
import Structures.Mouvement;
import Structures.Point;
import java.util.ArrayList;

public class IABasique extends IA{
    public IABasique(Jeu j, int joueur) {
        super(j, joueur);
    }

    private boolean estJoueur(int i, int j){
        return jeu.getCase(i, j).getTete().getCouleur() == joueur;
    }

    private boolean aPion(int i, int j){
        return ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0));

    }

    @Override
    public Mouvement joue() {
          /* Règle 1: S'il y a une case avec un seul voisin et que la couleur de cette tour voisine est  celle de l’IABasique,
                       alors on bouge la tour de pions de l’IABasique sur la tour pour obtenir un point.
             Règle 2 : S'il y a une tour de pions avec une hauteur x, un voisin de la couleur de l'IABasique avec une hauteur y,
                       et que x+y vaut 5, alors on bouge la tour de l’IABasique sur cette case pour obtenir un point.    */
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if (aPion(i, j)){
                    int hauteur = jeu.getCase(i, j).nbPions();
                    ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                    if (voisins != null){
                        if ((voisins.size() == 1)&&(estJoueur(voisins.get(0).x, voisins.get(0).y))){
                            return new Mouvement(voisins.get(0), new Point(i, j));
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
        IAAleatoire iaA = new IAAleatoire(jeu, joueur);
        return iaA.joue();
    }

}
