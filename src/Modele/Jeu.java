package Modele;

import Global.Configuration;
import Structures.*;
import Vue.CollecteurEvenements;

import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu extends Etat {
    public static final int COULEUR1 = 0;
    public static final int COULEUR2 = 1;
    CollecteurEvenements events;
    Joueur j1,j2;
    Historique historique;
    int lastDepI,lastDepJ,lastArrI,lastArrJ; //ajouté pour afficher le dernier coup

    public Jeu(CollecteurEvenements events){
        this(events, true);
    }

    public Jeu(CollecteurEvenements events, boolean fromScratch) {
        super();
        this.events = events;
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
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

    public void setCollecteurEvenements(CollecteurEvenements events) {
        this.events = events;
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
        if(!estMouvementPossible(depart, arrive) || historique.getNavigation()) return false;

        lastDepI=depart.x;lastDepJ=depart.y;lastArrI=arrive.x;lastArrJ=arrive.y;

        SequenceListe<Pion> pions = grille[depart.x][depart.y].getPions();
        grille[depart.x][depart.y].supprimePions();
        grille[arrive.x][arrive.y].ajoutePions(pions);
        setTour((tour==0) ? 1 : 0);

        historique.ajouteEtat(grille, tour);

        return true;
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

    public Historique getHistorique() {
        return historique;
    }

    public void enregistrerPartie() {
        PrintWriter out = Configuration.instance().ouvreFichierEcriture("FichierSauvegarde");
        if(out!=null) {
            out.println(taille.h + "," + taille.l);
            print(out); //Affiche la grille et le tour
            out.println(j1.getId() + "," + j1.getNom() + "," + j1.getColore());
            out.println(j2.getId() + "," + j2.getNom() + "," + j2.getColore());
            historique.print(out);
            out.close();
        }
    }

    public static Jeu recupererPartie(CollecteurEvenements events) {
        Scanner in = Configuration.instance().ouvrirFichierLecture("FichierSauvegarde");
        if(in==null) {
            return new Jeu(events);
        }
        Jeu nvx_jeu = new Jeu(events, false);

        //#### Récupération de la taille de la grille ####
        if(!in.hasNextLine()) return new Jeu(events);
        String line = in.nextLine();
        String[] taille = line.split(",");
        nvx_jeu.taille = new Size(Integer.parseInt(taille[0]),Integer.parseInt(taille[1]));

        //#### Récupération de la grille ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        nvx_jeu.grille = readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

        //#### Récupération du tour ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        nvx_jeu.tour = Integer.parseInt(line);

        //#### Récupération du premier joueur ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        String[] joueur = line.split(",");
        nvx_jeu.j1 = new Joueur(joueur[1],Integer.parseInt(joueur[0]),Integer.parseInt(joueur[2]));

        //#### Récupération du deuxième joueur ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        joueur = line.split(",");
        nvx_jeu.j2 = new Joueur(joueur[1],Integer.parseInt(joueur[0]),Integer.parseInt(joueur[2]));

        //#### Récupération du nombre de coups réels de l'historique ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        nvx_jeu.historique = new Historique(nvx_jeu, false);
        nvx_jeu.historique.nbCoupsReel = Integer.parseInt(line);

        //#### Récupération de la taille de l'historique ####
        if(!in.hasNextLine()) return new Jeu(events);
        line = in.nextLine();
        int taille_hist = Integer.parseInt(line);

        int tour_etat;
        //#### Récupération  de l'historique ####
        for(int i=0; i<taille_hist-1; i++) {
            Case[][] grille_etat;

            // Récupération de la grille de l'état
            if(!in.hasNextLine()) return new Jeu(events);
            line = in.nextLine();
            grille_etat = readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

            //Récupération du tour de l'état
            if(!in.hasNextLine()) return new Jeu(events);
            line = in.nextLine();
            tour_etat = Integer.parseInt(line);

            nvx_jeu.historique.ajouteEtat(grille_etat, tour_etat);
        }
        nvx_jeu.historique.ajouteEtat(nvx_jeu.grille, nvx_jeu.tour);

        return nvx_jeu;
    }
    public boolean estCaseArrive(int x,int y){
        return lastArrI==x && lastArrJ==y;
    }
    public boolean estCaseDepart(int x,int y){
        return lastDepI==x && lastDepJ==y;
    }
}
