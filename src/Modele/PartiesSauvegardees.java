package Modele;

import Global.Configuration;
import Structures.Size;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PartiesSauvegardees {
    public static Object[] getNomsParties() {
        int nb_parties = 0;
        File f = new File(System.getProperty("user.home") + File.separator + Configuration.home_directory);
        File[] fichiers = f.listFiles();
        if (fichiers != null) {
            Object[] parties = new Object[fichiers.length];
            for (int i = 0; i < fichiers.length; i++) {
                if(fichiers[i].isFile()) {
                    String filename = fichiers[i].getName();
                    //Vérification de l'extension
                    int dotIndex = filename.lastIndexOf('.');
                    String ext = (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
                    if(ext.equals("save")) {
                        parties[nb_parties] = filename.substring(0, dotIndex);
                        nb_parties++;
                    }
                }
            }
            return parties;
        }
        return null;
    }

    public static boolean enregistrerPartie(String nom, Jeu j) {
        PrintWriter out = Configuration.instance().ouvreFichierEcriture(nom
                +"."
                +Configuration.instance().lis("ExtensionSauvegarde"));
        if(out!=null) {
            j.estPartieNonSauvegardee=false;
            out.println(j.taille.h + "," + j.taille.l);
            j.print(out); //Affiche la grille et le tour
            out.println(j.j1.getId() + "," + j.j1.getNom() + "," + j.j1.getCouleur());
            out.println(j.j2.getId() + "," + j.j2.getNom() + "," + j.j2.getCouleur());
            j.historique.print(out);
            out.close();
            return true;
        }
        return false;
    }

    public static Jeu recupererPartie(String nom) {
        Scanner in = Configuration.instance().ouvrirFichierLecture(nom
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
        if(taille.length!=2) return null;
        nvx_jeu.taille = new Size(Integer.parseInt(taille[0]),Integer.parseInt(taille[1]));

        //#### Récupération de la grille ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        nvx_jeu.grille = Etat.readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

        //#### Récupération du tour ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        nvx_jeu.tour = Integer.parseInt(line);

        //#### Récupération du premier joueur ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        String[] joueur = line.split(",");
        if(joueur.length!=3) return null;
        nvx_jeu.j1 = new Joueur(joueur[1],Integer.parseInt(joueur[0]),Integer.parseInt(joueur[2]));

        //#### Récupération du deuxième joueur ####
        if(!in.hasNextLine()) return null;
        line = in.nextLine();
        joueur = line.split(",");
        if(joueur.length!=3) return null;
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
            grille_etat = Etat.readGrille(line, nvx_jeu.taille.h, nvx_jeu.taille.l);

            //Récupération du tour de l'état
            if(!in.hasNextLine()) return null;
            line = in.nextLine();
            tour_etat = Integer.parseInt(line);

            nvx_jeu.historique.ajouteEtat(new Etat(grille_etat, tour_etat));
        }
        nvx_jeu.historique.ajouteEtat(new Etat(nvx_jeu.grille, nvx_jeu.tour));

        return nvx_jeu;
    }
}
