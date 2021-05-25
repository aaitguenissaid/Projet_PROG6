package Modele;

import Global.Configuration;
import Structures.Iterateur;
import Structures.IterateurListe;
import Structures.FAPListe;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Classement {

    private final File fichierClassement;
    FAPListe<Score> listeScores;
    Scanner sc;

    public Classement() {
        //check if file exists
        //if yes load scores into the seq
        FAPListe<Score> listeScores = new FAPListe<Score>();
        //Fichiers et chemins
        String directory_path = System.getProperty("user.home") + File.separator + Configuration.home_directory;
        String nomFichierClassement = directory_path + File.separator + Configuration.instance().lis("user_classement");
        fichierClassement = new File(nomFichierClassement);

        //Création du fichier de Classement s'il n'existe pas
        if(!Files.exists(Paths.get(nomFichierClassement))) {
            try {
                //Création du répertoire Avalam (s'il n'existe pas) dans le répertoire de l'utilisateur
                Files.createDirectories(Paths.get(directory_path));
                fichierClassement.createNewFile();
                System.out.println("Empty File Created:- " + fichierClassement.length());
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        } else {
            String ligne = null;
            try {
                sc = new Scanner(fichierClassement);
                ligne = sc.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            while (ligne.length() > 0) {
                String[] mots = new String[4];
                mots = ligne.split(" ");
                String pseudo = mots[0];
                int nbVictoires = Integer.parseInt(mots[1]);
                int nbParties = Integer.parseInt(mots[2]);
                float ratio = Float.parseFloat(mots[3]);
                Score score = new Score(pseudo, nbVictoires, nbParties, ratio);
                listeScores.insere(score);
                ligne = sc.nextLine();
            }
        }
        this.listeScores = listeScores;
    }

    Iterateur getIterateur() {
        return listeScores.iterateur();
    }

    void enregsitererScore(String pseudo, boolean aGagner) {
        Iterateur<Score> it = listeScores.iterateur();
        while(it.aProchain()){
            Score p = it.prochain();
            if (p.pseudo.equals(pseudo)) {
                p.nbParties++;
                p.nbVictoires = aGagner ? p.nbVictoires+1 : p.nbVictoires;
                //enregistrer le fichier. suprimer et reecrire.
                return;
            }
        }
        listeScores.insere(new Score(pseudo, aGagner ? 1 : 0, 1));
        //enregistrer le fichier. suprimer et reecrire.

    }

    void enregsitererScore(String pseudo1, String pseudo2, boolean premierAGagner) {
        enregsitererScore(pseudo1, premierAGagner);
        enregsitererScore(pseudo2, !premierAGagner);
    }

    void setScore(Score score) {
        /*if(!liste)
            ajoute
            String pseudo;
            int nbVictoires;
            int nbParties;
            float ratio;

        else
*/

    }
}
