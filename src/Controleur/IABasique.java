package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

public class IABasique extends IA{

    public IABasique(Jeu j, int joueur) {
        super(j, joueur);
    }

    @Override
    public Mouvement joue() {
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).nbPions();
                        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                        if (voisins != null){
                            if ((voisins.size() == 1)&&(jeu.getCase(voisins.get(0).x, voisins.get(0).y).getTete().getCouleur() == joueur)){
                                return new Mouvement(voisins.get(0), new Point(i, j));
                            }
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).nbPions();
                                if ((hauteurDepart+hauteur == 5)&&(jeu.getCase(depart.x, depart.y).getTete().getCouleur() == joueur)){
                                    return new Mouvement(depart, new Point(i, j));
                                }
                            }
                        }

                    }
                }
            }
        }
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).nbPions();
                        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                        if (voisins.size() > 0){
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).nbPions();
                                if ((hauteurDepart+hauteur < 5)&&(jeu.getCase(depart.x, depart.y).getTete().getCouleur() != joueur)){
                                    return new Mouvement(depart, new Point(i, j));
                                }
                            }
                        }

                    }
                }
            }
        }
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).nbPions();
                        ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                        if (voisins != null){
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).nbPions();
                                if ((hauteurDepart+hauteur < 5)&&(jeu.getCase(depart.x, depart.y).getTete().getCouleur() == joueur)){
                                    return new Mouvement(depart, new Point(i, j));
                                }
                            }
                        }

                    }
                }
            }
        }
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).nbPions() > 0)){
                    int hauteur = jeu.getCase(i, j).nbPions();
                    ArrayList<Point> voisins = jeu.voisinsAccessibles(i, j);
                    if (voisins != null){
                        for (int s = 0; s < voisins.size(); s++){
                            Point depart = voisins.get(s);
                            int hauteurDepart = jeu.getCase(depart.x, depart.y).nbPions();
                            if ((hauteurDepart+hauteur < 5)){
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
