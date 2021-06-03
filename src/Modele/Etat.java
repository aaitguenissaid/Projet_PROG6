package Modele;

import Global.Configuration;
import Structures.Iterateur;
import Structures.SequenceListe;
import Structures.Size;
import Vue.EffetsSonores;

import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Etat {
    public static final int DEFAULT_SIZE = 9;
    Case[][] grille;
    Size taille;
    int tour;
    int lastDepI,lastDepJ,lastArrI,lastArrJ, nbPionsDepl; //ajouté pour afficher avoir coup






    // ##########################
    // ##### INITIALISATION #####
    // ##########################
    public Etat(Case [][]grille, int tour, int lastDepI, int lastDepJ, int lastArrI, int lastArrJ, int nbPionsDepl) {
        this.taille = new Size(grille.length,grille.length);
        this.grille = new Case[this.taille.h][this.taille.l];
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                this.grille[i][j] = (Case) grille[i][j].clone();
            }
        }
        this.tour = tour;
        this.lastDepI=lastDepI;
        this.lastDepJ=lastDepJ;
        this.lastArrI=lastArrI;
        this.lastArrJ=lastArrJ;
        this.nbPionsDepl=nbPionsDepl;
    }

    public Etat(Case [][]grille, int tour) {
        this(grille, tour, -1, -1, -1, -1,0);
    }

    public Etat() {
        taille = new Size(DEFAULT_SIZE, DEFAULT_SIZE);
        grille = new Case[this.taille.h][this.taille.l];
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
        nbPionsDepl=0;
    }

    protected void init_grille(){
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






    // ###########################
    // ##### GETTER / SETTER #####
    // ###########################
    public Size getTaille() {
        return this.taille;
    }
    public int getTour() {
        return this.tour;
    }
    public Case getCase(int i , int j){
        return grille[i][j];
    }
    public void setTour(int tour) {
        this.tour = tour;
    }






    // ########################
    // ##### STATISTIQUES #####
    // ########################
    public int nbPilesJoueur(int id) {
        int ret=0;
        for(int i=0; i<taille.h; i++)
            for(int j=0; j< taille.l; j++)
                if(estCaseValide(new Point(i, j)) && grille[i][j].tete!=null && grille[i][j].tete.estCouleur(id))
                    ret++;
        return ret;
    }

    //TODO rajoute methode comptage de tours de 5 d'une couleurs.
    public int nbPiles5Joueur(int id) {
        int resultat = 0;
        for(int i=0; i<taille.h; i++)
            for(int j=0; j< taille.l; j++)
                if(estCaseValide(new Point(i, j)) && grille[i][j].nbPions()==5 && grille[i][j].tete.estCouleur(id))
                    resultat++;
        return resultat;
    }




    // ################################
    // ##### ANALYSE DE LA GRILLE #####
    // ################################
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






    // #######################
    // #### VERIFICATIONS ####
    // #######################
    protected boolean coordonneesValides(Point pt) {
        return (pt.x>=0 && pt.x<taille.h && pt.y>=0 && pt.y<taille.l);
    }
    public boolean estCaseValide(Point pt) {
        return coordonneesValides(pt) && grille[pt.x][pt.y].estValide();
    }
    public boolean estMouvementPossible(Point depart, Point arrive) {
        if(estCaseValide(depart)        //On vérifie les coordonnées
                && estCaseValide(arrive)    //On vérifie les coordonnées
                && (arrive.x==depart.x+1 || arrive.x==depart.x-1 || arrive.x==depart.x) //On vérifie qu'on se déplace d'une case
                && (arrive.y==depart.y+1 || arrive.y==depart.y-1 || arrive.y==depart.y) //On vérifie qu'on se déplace d'une case
                && (depart.x!=arrive.x || depart.y!=arrive.y) ) { //On vérifie qu'on ne reste pas sur la même case
            return (grille[depart.x][depart.y].nbPions()>0) // On vérifie qu'on ne déplace pas une tour vide
                    && (grille[arrive.x][arrive.y].nbPions()>0)
                    && (grille[depart.x][depart.y].nbPions() + grille[arrive.x][arrive.y].nbPions()) <= 5; //On vérifie qu'il n'y aura pas trop de pions sur la tour
        }
        return false;
    }


    public boolean estCaseArrive(int x,int y){
        return lastArrI==x && lastArrJ==y;
    }
    public boolean estCaseDepart(int x,int y){
        return lastDepI==x && lastDepJ==y;
    }
    public int getNbPionsDepl() {return nbPionsDepl;}






    // ###########################
    // #### ENTREES / SORTIES ####
    // ###########################
    public static void printGrille(PrintWriter out, Case [][]grille, int h, int l) {
        for(int i=0; i<h; i++) {
            for(int j=0; j<l; j++) {
                out.print(grille[i][j].estValide());
                if(grille[i][j].estValide()) {
                    out.print(",");
                    Iterateur<Pion> it = grille[i][j].getPions().iterateur();
                    while (it.aProchain()) {
                        out.print(it.prochain().getCouleur());
                    }
                }
                if(j!=l-1) out.print("/");
            }
            if(i!=h-1) out.print(":");
        }
        out.println();
    }

    public static Case[][] readGrille(String strGrille, int h, int l) {
        Case [][] grille = new Case[h][l];
        String[] lignes = strGrille.split(":");
        for(int i=0; i<lignes.length && i<h; i++) {
            String[] cases = lignes[i].split("/");
            for(int j=0; j<cases.length && j<l; j++) {
                String[] attributs = cases[j].split(",");
                boolean valide = Boolean.parseBoolean(attributs[0]);
                grille[i][j] = new Case(valide);
                if(valide && attributs.length==1) {
                    grille[i][j].pions = new SequenceListe<>();
                    grille[i][j].tete = null;
                } else if(valide) {
                    SequenceListe<Pion> seq = new SequenceListe<>();
                    for(char c : attributs[1].toCharArray()) {
                        seq.insereTete(new Pion(Character.getNumericValue(c)));
                    }
                    grille[i][j].pions = seq;
                    grille[i][j].tete = (seq.taille()>0) ? seq.teteReturn() : null;
                }
            }
        }
        return grille;
    }

    public void print(PrintWriter out) {
        printGrille(out, grille, taille.h, taille.l);
        out.println(tour);
    }

    public String toString() {
        String s = "";
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                s += grille[i][j];
            }
            s += "\n";
        }
        return s;
    }






    // #################
    // #### CLONAGE ####
    // #################
    public Case[][] getCopieGrille() {
        Case[][] copy_grid = new Case[this.taille.h][this.taille.l];
        for(int i=0; i<taille.h; i++) {
            for(int j=0; j<taille.l; j++) {
                copy_grid[i][j] = (Case) grille[i][j].clone();
            }
        }
        return copy_grid;
    }

    public int getLastDepI() {
        return lastDepI;
    }

    public int getLastDepJ() {
        return lastDepJ;
    }

    public int getLastArrI() {
        return lastArrI;
    }

    public int getLastArrJ() {
        return lastArrJ;
    }
}
