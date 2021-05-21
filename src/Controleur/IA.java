package Controleur;

import Modele.Jeu;
import Structures.Mouvement;


import java.awt.*;
import java.util.ArrayList;

abstract class IA {
    Jeu jeu;
    int joueur;

    public IA (Jeu j, int joueur) {
        jeu = j;
        this.joueur = joueur;

    }

    /* Prends une indice de tableau de configuration comme le depart et rends les indices comme arrivées accessibles */
    public ArrayList<Point> voisinsAccessibles(int h, int l){
        int hauteurDepart = jeu.getCase(h, l).hauteur();
        ArrayList<Point> resultat = new ArrayList<>();
        if (hauteurDepart > 0){
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
        }
        return resultat;
    }


    public ArrayList<Point> trouveCasePeutBouger(){
        ArrayList<Point> casePeutBouger = new ArrayList<>();
        for (int i = 0; i < jeu.getTaille().h; i++){
            for (int j = 0; j < jeu.getTaille().l; j++){
                if ((jeu.estCaseValide(new Point(i, j))) && (jeu.getCase(i, j).hauteur()>0)){
                    ArrayList<Point> vosinsAccessible = voisinsAccessibles(i, j);
                    if (vosinsAccessible != null){
                        if (vosinsAccessible.size() != 0)
                            casePeutBouger.add(new Point(i, j));
                    }
                }
            }
        }
        return casePeutBouger;
    }

    public abstract Mouvement joue();
}
