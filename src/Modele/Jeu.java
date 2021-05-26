package Modele;

import Global.Configuration;
import Structures.*;
import Vue.CollecteurEvenements;

import java.awt.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Jeu extends Etat implements Cloneable {
    public static final int COULEUR1 = 0;
    public static final int COULEUR2 = 1;
    Joueur j1,j2;
    Historique historique;
    int lastDepI,lastDepJ,lastArrI,lastArrJ; //ajouté pour afficher le dernier coup
    boolean estPartieRecuperee;

    public Jeu() {
        this(true);
    }

    public Jeu(boolean fromScratch) {
        super();
        lastDepI=lastDepJ=lastArrI=lastArrJ=-1;
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
        if(historique.getNavigation()) {
            //Si l'utilisateur était en train de naviguer dans l'historique, on demande confirmation pour retourner
            //dans l'état qu'il visitait
            if(!historique.getEtatNavigation().estMouvementPossible(depart, arrive)) return false;
            /*String titre = "Validation navigation historique";
            String description = "Attention, vous êtes sur le point de retourner à un état antérieur de la partie.\n"
                    +"Si vous n'avez pas enregistré votre partie, certains coups risquent d'être perdus.";
            String choix_valide = "Continuer";
            String choix_annule = "Annuler";*/
            historique.validerNavigation();
        } else {
            if (!estMouvementPossible(depart, arrive)) return false;
        }

        lastDepI=depart.x;lastDepJ=depart.y;lastArrI=arrive.x;lastArrJ=arrive.y;

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

    public void enregistrerPartie(String nom_partie) {
        PrintWriter out = Configuration.instance().ouvreFichierEcriture(nom_partie
                +"."
                +Configuration.instance().lis("ExtensionSauvegarde"));
        if(out!=null) {
            out.println(taille.h + "," + taille.l);
            print(out); //Affiche la grille et le tour
            out.println(j1.getId() + "," + j1.getNom() + "," + j1.getColore());
            out.println(j2.getId() + "," + j2.getNom() + "," + j2.getColore());
            historique.print(out);
            out.close();
        }
    }

    public static Jeu recupererPartie(String nom_partie) {
        return recupererPartie(null, nom_partie);
    }

    public static Jeu recupererPartie(CollecteurEvenements events, String nom_partie) {
        Scanner in = Configuration.instance().ouvrirFichierLecture(nom_partie
                +"."
                +Configuration.instance().lis("ExtensionSauvegarde"));
        if(in==null) {
            return null;
        }
        Jeu nvx_jeu = new Jeu(false);
        nvx_jeu.estPartieRecuperee=true;

        //#### Récupération de la taille de la grille ####
        if(!in.hasNextLine()) return null;
        String line = in.nextLine();
        String[] taille = line.split(",");
        nvx_jeu.taille = new Size(Integer.parseInt(taille[0]),Integer.parseInt(taille[1]));

        //#### Récupération de la grille ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        nvx_jeu.grille = readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

        //#### Récupération du tour ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        nvx_jeu.tour = Integer.parseInt(line);

        //#### Récupération du premier joueur ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        String[] joueur = line.split(",");
        nvx_jeu.j1 = new Joueur(joueur[1],Integer.parseInt(joueur[0]),Integer.parseInt(joueur[2]));

        //#### Récupération du deuxième joueur ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        joueur = line.split(",");
        nvx_jeu.j2 = new Joueur(joueur[1],Integer.parseInt(joueur[0]),Integer.parseInt(joueur[2]));

        //#### Récupération du nombre de coups réels de l'historique ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        nvx_jeu.historique = new Historique(nvx_jeu, false);
        nvx_jeu.historique.nbCoupsReel = Integer.parseInt(line);

        //#### Récupération de la taille de l'historique ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        int taille_hist = Integer.parseInt(line);

        int tour_etat;
        //#### Récupération  de l'historique ####
        for(int i=0; i<taille_hist-1; i++) {
            Case[][] grille_etat;

            // Récupération de la grille de l'état
            if(!in.hasNextLine()) return null;
            line = in.nextLine();
            grille_etat = readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

            //Récupération du tour de l'état
            if(!in.hasNextLine()) return null;
            line = in.nextLine();
            tour_etat = Integer.parseInt(line);

            nvx_jeu.historique.ajouteEtat(grille_etat, tour_etat);
        }
        nvx_jeu.historique.ajouteEtat(nvx_jeu.grille, nvx_jeu.tour);

        return nvx_jeu;
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

    public boolean estCaseArrive(int x,int y){
        return lastArrI==x && lastArrJ==y;
    }
    public boolean estCaseDepart(int x,int y){
        return lastDepI==x && lastDepJ==y;
    }

    public String getNomJ1(){
        return j1.getNom();
    }
    public String getNomJ2(){
        return j2.getNom();
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
