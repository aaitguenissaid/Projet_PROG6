package Modele;

import Global.Configuration;
import Structures.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Jeu extends Etat implements Cloneable {
    public static final int COULEUR1 = 0;
    public static final int COULEUR2 = 1;
    Joueur j1,j2;
    Historique historique;
    int lastDepI,lastDepJ,lastArrI,lastArrJ, nbPionsDepl; //ajouté pour afficher le dernier coup
    boolean estPartieRecuperee;

    public Jeu() {
        this(true);
    }

    public Jeu(boolean fromScratch) {
        super();
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
        nbPionsDepl=0;
        estPartieRecuperee=false;
        if(fromScratch) {
            j1 = new Joueur(1,0);
            j2 = new Joueur(2,1);
            tour = COULEUR1;
            init_grille();
            //L'historique doit être construit en dernier (il récupère la grille initiale du jeu)
            historique = new Historique(this);
        }
    }

    private void init_grille(){
        int centerL = taille.l/2;
        int centerH = taille.h/2;
        ArrayList<Point> emptyPoints = Configuration.instance().getEmptyPoints();
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j< taille.l; j++) {
                if(emptyPoints.contains(new Point(i,j))) {
                    grille[i][j] = new Case(false);
                } else if((i==centerH && j==centerL)) {
                    grille[i][j] = new Case(true);
                } else {
                    if(i%2==0)
                        grille[i][j] = new Case(true, new Pion(j%2));
                    else
                        grille[i][j] = new Case(true, new Pion((j+1)%2));
                }
            }
        }
    }

    public boolean estFini() {
        //On recherche les piles que l'on peut déplacer
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                //S'il est envisageable de déplacer la tour
                if(grille[i][j].estValide() && grille[i][j].nbPions()>0 && grille[i][j].nbPions()<5) {
                    int nbPions = grille[i][j].nbPions();
                    ArrayList<Point> voisins = new ArrayList<>();
                    voisins.add(new Point(i,j-1));
                    voisins.add(new Point(i-1,j-1));
                    voisins.add(new Point(i-1,j));
                    voisins.add(new Point(i-1,j+1));
                    voisins.add(new Point(i,j+1));
                    voisins.add(new Point(i+1,j+1));
                    voisins.add(new Point(i+1,j));
                    voisins.add(new Point(i+1,j-1));
                    //On parcourt tous les voisins
                    for(int k=0; k<voisins.size(); k++) {
                        Point voisin = voisins.get(k);
                        if(estCaseValide(voisin)) {
                            int nbPionsVoisin = grille[voisin.x][voisin.y].nbPions();
                            if (nbPionsVoisin > 0 && (nbPionsVoisin + nbPions) <= 5) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Un déplacement est refusé si :
     *  - Le joueur est en train de naviguer dans l'historique
     *  - Le mouvement est impossible (en dehors de la grille, trop / pas assez de pions, case invalide)
     */
    public boolean bouge(Point depart, Point arrive) {
        if(historique.isNavigationOn()) {
            //Si l'utilisateur était en train de naviguer dans l'historique, on demande confirmation pour retourner
            //dans l'état qu'il visitait
            if(!historique.getEtatNavigation().estMouvementPossible(depart, arrive)) return false;
            historique.validerNavigation();
        } else {
            if (!estMouvementPossible(depart, arrive)) return false;
        }

        lastDepI=depart.x;lastDepJ=depart.y;lastArrI=arrive.x;lastArrJ=arrive.y;
        nbPionsDepl=grille[depart.x][depart.y].nbPions();

        SequenceListe<Pion> pions = grille[depart.x][depart.y].getPions();
        grille[depart.x][depart.y].supprimePions();
        grille[arrive.x][arrive.y].ajoutePions(pions);
        setTour((tour==0) ? 1 : 0);

        historique.ajouteEtat(grille, tour);

        return true;
    }

    //Dédiée à l'IA
    public boolean annule(Point depart, Point arrive, int nbPions) {
        if(!estCaseValide(depart) || !estCaseValide(arrive)) return false;

        Iterateur<Pion> it = grille[arrive.x][arrive.y].getIterateur();
        SequenceListe<Pion> seq = new SequenceListe<>();
        int i=0;
        while(it.aProchain() && i<nbPions) {
            Pion p = it.prochain();
            it.supprime();
            seq.insereQueue(p);
            i++;
        }
        grille[arrive.x][arrive.y].updateTete();
        grille[depart.x][depart.y].ajoutePions(seq);
        setTour((tour==0) ? 1 : 0);

        historique.supprimeTete();

        return true;
    }

    public Historique getHistorique() {
        return historique;
    }

    // Prends une indice de tableau de configuration comme le depart et rends les indices comme arrivées accessibles
    public ArrayList<Point> voisinsAccessibles(int h, int l){
        int nbPionsDep = grille[h][l].nbPions();
        ArrayList<Point> resultat = new ArrayList<>();
        if (nbPionsDep > 0) {
            ArrayList<Point> pointsVoisins = getPointsVoisins(h, l);
            for (Point v : pointsVoisins) {
                if (estCaseValide(v) && grille[v.x][v.y].nbPions() > 0 && grille[v.x][v.y].nbPions() + nbPionsDep <= 5) {
                    resultat.add(v);
                }
            }
        }
        return resultat;
    }

    public ArrayList<Point> trouveCasePeutBouger() {
        ArrayList<Point> casePeutBouger = new ArrayList<>();
        for (int i = 0; i < taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                if ((estCaseValide(new Point(i, j))) && (grille[i][j].nbPions()>0)) {
                    ArrayList<Point> vosinsAccessible = voisinsAccessibles(i, j);
                    if (vosinsAccessible != null && vosinsAccessible.size() != 0) {
                            casePeutBouger.add(new Point(i, j));
                    }
                }
            }
        }
        return casePeutBouger;
    }

    private ArrayList<Point> getPointsVoisins(int h, int l) {
        ArrayList<Point> ret = new ArrayList<>();
        ret.add(new Point(h, l-1));
        ret.add(new Point(h-1, l-1));
        ret.add(new Point(h-1, l));
        ret.add(new Point(h-1, l+1));
        ret.add(new Point(h, l+1));
        ret.add(new Point(h+1, l+1));
        ret.add(new Point(h+1, l));
        ret.add(new Point(h+1, l-1));
        return ret;
    }

    //Fait perdre le joueur dont c'était le tour
    public void abandonner() {
        relancerPartie(true);
    }

    public void relancerPartie() {
        relancerPartie(false);
    }

    public void relancerPartie(boolean isAbandon) {
        Classement c = new Classement();
        if(isAbandon) {
            c.enregistrerScore(getNomJ1(), getNomJ2(), tour!=COULEUR1);
        } else {
            int nb1=0, nb2=0;
            for(int i=0; i<taille.h; i++) {
                for(int j=0; j<taille.l; j++) {
                    if(grille[i][j].estValide() && grille[i][j].tete!=null) {
                        if(grille[i][j].tete.estCouleur1()) nb1++;
                        else nb2++;
                    }
                }
            }
            if(nb1!=nb2) {
                c.enregistrerScore(getNomJ1(), getNomJ2(), nb1>nb2);
            }
        }
        init_grille();
        tour = COULEUR1;
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
        estPartieRecuperee=false;
        historique = new Historique(this);
    }

    public int nbPilesJoueur(int id) {
        int ret=0;
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j< taille.l; j++) {
                if(estCaseValide(new Point(i, j)) && grille[i][j].tete!=null && grille[i][j].tete.estCouleur(id)) ret++;
            }
        }
        return ret;
    }

    public boolean estCaseArrive(int x,int y){
        return lastArrI==x && lastArrJ==y;
    }
    public boolean estCaseDepart(int x,int y){
        return lastDepI==x && lastDepJ==y;
    }
    public int getNbPionsDepl() {return nbPionsDepl;}

    public Joueur getJ1() {return j1;}
    public Joueur getJ2() {return j2;}
    public String getNomJ1(){
        return j1.getNom();
    }
    public String getNomJ2(){
        return j2.getNom();
    }
    public boolean setNomJ1(String nom) {
        String []IA_names = Configuration.instance().lis("IA_names").split(",");
        if(j2.getNom().equals(nom) || Arrays.stream(IA_names).toList().contains(nom)) return false;
        j1.setNom(nom);
        return true;
    }
    public boolean setNomJ2(String nom) {
        String []IA_names = Configuration.instance().lis("IA_names").split(",");
        if(j1.getNom().equals(nom) || Arrays.stream(IA_names).toList().contains(nom)) return false;
        j2.setNom(nom);
        return true;
    }

    public void inverseJoueurs() {
        String tmp = j1.getNom();
        j1.setNom(j2.getNom());
        j2.setNom(tmp);
    }

    @Override
    public Object clone() {
        Jeu ret = new Jeu();
        ret.j1 = j1;
        ret.j2 = j2;
        ret.historique = (Historique) historique.clone();
        ret.historique.jeu = ret;
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                ret.grille[i][j] = (Case) grille[i][j].clone();
            }
        }
        ret.lastArrI = lastArrI;
        ret.lastArrJ = lastArrJ;
        ret.lastDepI = lastDepI;
        ret.lastDepJ = lastDepJ;
        ret.estPartieRecuperee = estPartieRecuperee;
        return ret;
    }
}
