package Controleur;

import Modele.Jeu;
import Structures.Mouvement;

import java.awt.*;
import java.util.ArrayList;

public class IABasique extends IA{

    public IABasique(Jeu j, int joueur) {
        super(j, joueur);
    }

    /* Prends une indice de tableau de configuration comme le depart et rends les indices comme arrivées accessibles */
    private ArrayList<Point> voisinsAccessibles(int h, int l, int hauteurDepart){
        ArrayList<Point> resultat = new ArrayList<>();
        Point Arrivee;
        if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l)))&&(jeu.getCase(h-1, l).hauteur() > 0)){        //(h-1, l)
            if (jeu.getCase(h-1, l).hauteur()+hauteurDepart <=5)
                resultat.add(new Point(h-1, l));
        }
        if ((h+1 < jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l)))&&(jeu.getCase(h+1, l).hauteur() > 0)){         //(h+1, l)
            if (jeu.getCase(h+1, l).hauteur()+hauteurDepart <=5)
                resultat.add(new Point(h+1, l));
        }
        if (l-1 >= 0){
            if (jeu.estCaseValide(new Point(h, l-1))&&(jeu.getCase(h, l-1).hauteur() > 0)){
                if (jeu.getCase(h, l-1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h, l-1));
            }
            if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l-1)))&&(jeu.getCase(h-1, l-1).hauteur() > 0)){    //(h-1, l-1)
                if (jeu.getCase(h-1, l-1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h-1, l-1));
            }
            if ((h+1 <  jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l-1)))&&(jeu.getCase(h+1, l-1).hauteur() > 0)){  //(h+1, l-1)
                if (jeu.getCase(h+1, l-1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h+1, l-1));
            }
        }
        if (l+1 < jeu.getTaille().l){
            if (jeu.estCaseValide(new Point(h, l+1))&&(jeu.getCase(h, l+1).hauteur() > 0)){
                if (jeu.getCase(h, l+1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h, l+1));
            }
            if ((h-1 >= 0)&&(jeu.estCaseValide(new Point(h-1, l+1)))&&(jeu.getCase(h-1, l+1).hauteur() > 0)){    //(h-1, l+1)
                if (jeu.getCase(h-1, l+1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h-1, l+1));
            }
            if ((h+1 < jeu.getTaille().h)&&(jeu.estCaseValide(new Point(h+1, l+1)))&&(jeu.getCase(h+1, l+1).hauteur() > 0)){  //(h+1, l+1)
                if (jeu.getCase(h+1, l+1).hauteur()+hauteurDepart <=5)
                    resultat.add(new Point(h+1, l+1));
            }
        }
        return resultat;
    }



    @Override
    public Mouvement joue() {
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).hauteur() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).hauteur();
                        ArrayList<Point> voisins = voisinsAccessibles(i, j, hauteur);
                        if (voisins != null){
                            if ((voisins.size() == 1)&&(jeu.getCase(voisins.get(0).x, voisins.get(0).y).getTete().getCouleur() == joueur)){
                                return new Mouvement(voisins.get(0), new Point(i, j));
                            }
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).hauteur();
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
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).hauteur() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).hauteur();
                        ArrayList<Point> voisins = voisinsAccessibles(i, j, hauteur);
                        if (voisins != null){
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).hauteur();
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
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).hauteur() > 0)){
                    if (jeu.getCase(i, j).getTete().getCouleur() != joueur){
                        int hauteur = jeu.getCase(i, j).hauteur();
                        ArrayList<Point> voisins = voisinsAccessibles(i, j, hauteur);
                        if (voisins != null){
                            for (int s = 0; s < voisins.size(); s++){
                                Point depart = voisins.get(s);
                                int hauteurDepart = jeu.getCase(depart.x, depart.y).hauteur();
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
                if ((jeu.estCaseValide(new Point(i, j))) &&(jeu.getCase(i, j).hauteur() > 0)){
                    int hauteur = jeu.getCase(i, j).hauteur();
                    ArrayList<Point> voisins = voisinsAccessibles(i, j, hauteur);
                    if (voisins != null){
                        for (int s = 0; s < voisins.size(); s++){
                            Point depart = voisins.get(s);
                            int hauteurDepart = jeu.getCase(depart.x, depart.y).hauteur();
                            if ((hauteurDepart+hauteur < 5)){
                                return new Mouvement(depart, new Point(i, j));
                            }
                        }
                    }


                }
            }
        }
        Mouvement m = null;
        return m;
    }
}
