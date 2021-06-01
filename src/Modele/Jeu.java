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
    boolean estPartieRecuperee;






    // ########################
    // #### INITIALISATION ####
    // ########################
    public Jeu() {
        this(true);
    }

    public Jeu(boolean fromScratch) {
        super();
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

        historique.ajouteEtat(new Etat(grille, tour));

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
    //Fait perdre le joueur dont c'était le tour
    public void abandonner() {
        relancerPartie(true);
    }

    public void relancerPartie() {
        relancerPartie(false);
    }

    public void relancerPartie(boolean isAbandon) {
        Classement c = new Classement(this);
        if(isAbandon) {
            // TODO 24 codé en dur!
            if(nbPilesJoueur(j1.id)==nbPilesJoueur(j2.id) && nbPilesJoueur(j1.id)==24){
                System.out.println("Aucun pion n'a été Bougé");
                // aucun pion n'a été bougé. pas d'enregistrement.
            } else {
                // celui qui a abondonné est perdant!
                c.enregistrerScore(getNomJ1(), getNomJ2(), tour!=COULEUR1);
            }
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

    public boolean j1AGagne() {
        boolean resultat = false;
        if(nbPilesJoueur(j1.id) > nbPilesJoueur(j2.id))
            resultat = true;
        else {
            //TODO if comparaison des piles à 5.
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
}
