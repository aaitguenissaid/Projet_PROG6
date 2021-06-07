package Modele;

import Global.Configuration;
import Structures.*;
import Vue.CollecteurEvenements;
import Vue.EffetsSonores;
import Structures.Point;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Jeu extends Etat implements Cloneable {
    public static final int COULEUR1 = 0;
    public static final int COULEUR2 = 1;
    Joueur j1,j2;
    Historique historique;
    boolean estPartieRecuperee;
    CollecteurEvenements cc;
    EffetsSonores son;





    // ########################
    // #### INITIALISATION ####
    // ########################
    public Jeu() {
        this(true);
        son = new EffetsSonores();
    }
    public void disable_enable_son(){
        son.deisabel_enabel_son();
    }
    public Jeu(boolean fromScratch) {
        super();
        estPartieRecuperee=false;
        if(fromScratch) {
            j1 = new Joueur(0,COULEUR1);
            j2 = new Joueur(1,COULEUR2);
            tour = COULEUR1;
            init_grille();
            //L'historique doit être construit en dernier (il récupère la grille initiale du jeu)
            historique = new Historique(this);
        }
        son = new EffetsSonores();
    }






    // ######################
    // #### VERIFICATION ####
    // ######################
    public boolean estFini() {
        //On recherche les piles que l'on peut déplacer
        for(int i=0; i<taille.h; i++) {
            for (int j = 0; j < taille.l; j++) {
                //S'il est envisageable de déplacer la tour
                if(grille[i][j].estValide() && grille[i][j].nbPions()>0 && grille[i][j].nbPions()<5) {
                    int nbPions = grille[i][j].nbPions();
                    ArrayList<Point> voisins = voisinsAccessibles(i, j);
                    if(voisins!=null && voisins.size()>0) return false;
                }
            }
        }
        return true;
    }






    // #########################
    // #### JOUER DES COUPS ####
    // #########################
    /**
     * Un déplacement est refusé si :
     *  - Le joueur est en train de naviguer dans l'historique
     *  - Le mouvement est impossible (en dehors de la grille, trop / pas assez de pions, case invalide)
     */
    public boolean bouge(Point depart, Point arrive) {
        return bouge(depart, arrive, true);
    }

    public boolean bouge(Point depart, Point arrive, boolean addToHistoric) {
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

        if(addToHistoric) {
            historique.ajouteEtat(new Etat(grille, tour,lastDepI,lastDepJ,lastArrI,lastArrJ,nbPionsDepl));
            son.moveEnd();
        }

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






    // #######################
    // #### GETTER/SETTER ####
    // #######################
    public Historique getHistorique() {
        return historique;
    }






    // ###################################
    // #### MANIPULATION DE LA PARTIE ####
    // ###################################
    public void relancerPartie() {
        init_grille();
        tour = COULEUR1;
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
        estPartieRecuperee=false;
        historique = new Historique(this);
    }






    // ##################################
    // #### MANIPULATION DES JOUEURS ####
    // ##################################
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
        /*if(j2.getNom().equals(nom) || Arrays.stream(IA_names).toList().contains(nom)) return false;*/
        j1.setNom(nom);
        return true;
    }
    public boolean setNomJ2(String nom) {
        String []IA_names = Configuration.instance().lis("IA_names").split(",");
        /*if(j1.getNom().equals(nom) || Arrays.stream(IA_names).toList().contains(nom)) return false;*/
        j2.setNom(nom);
        return true;
    }

    public void inverseJoueurs() {
        String tmp = j1.getNom();
        j1.setNom(j2.getNom());
        j2.setNom(tmp);
    }

    public int quiAGagnee() {
        int resultat = 2;

        if(nbPilesJoueur(Jeu.COULEUR1) == nbPilesJoueur(Jeu.COULEUR2)) {
            if (nbPiles5Joueur(Jeu.COULEUR1) > nbPiles5Joueur(Jeu.COULEUR2)) {
                resultat = 1;
            } else if (nbPiles5Joueur(Jeu.COULEUR1) == nbPiles5Joueur(Jeu.COULEUR2)) {
                resultat = 0;
            }
        } else if (nbPilesJoueur(Jeu.COULEUR1) > nbPilesJoueur(Jeu.COULEUR2)) {
            resultat = 1;
        }

        return resultat;
    }






    // #################
    // #### CLONAGE ####
    // #################
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
    public  void setCollecteurEvenements(CollecteurEvenements ccc){
        cc=ccc;
    }
}
