package Modele;

import Global.Configuration;
import Structures.Iterateur;
import Structures.FAPListe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Classement {

    private File fichierClassement;
    FAPListe<Score> listeScores;
    Scanner sc;
    //Fichiers et chemins
    String directory_path = System.getProperty("user.home") + File.separator + Configuration.home_directory;
    String nomFichierClassement = directory_path + File.separator + Configuration.instance().lis("user_classement");

    public Classement() {
        //check if file exists
        //if yes load scores into the seq
        FAPListe<Score> listeScores = new FAPListe<Score>();
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
                if(sc.hasNextLine())
                    ligne = sc.nextLine();
                else
                    ligne = "";
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            while (ligne.length() > 0) {
                String[] mots = new String[4];
                mots = ligne.split("\t");
                String pseudo = mots[0];
                int nbVictoires = Integer.parseInt(mots[1]);
                int nbParties = Integer.parseInt(mots[2]);
                float ratio = Float.parseFloat(mots[3]);
                Score score = new Score(pseudo, nbVictoires, nbParties, ratio);
                listeScores.insere(score);
                if(sc.hasNextLine())
                    ligne = sc.nextLine();
                else
                    ligne = "";
            }
        }
        this.listeScores = listeScores;
    }

    Iterateur getIterateur() {
        return listeScores.iterateur();
    }

    void enregsitererScore(String pseudo, boolean aGagner) {
        Iterateur<Score> it = listeScores.iterateur();
        boolean b = false;
        while(it.aProchain()){
            Score p = it.prochain();
            if (p.pseudo.equals(pseudo)) {
                System.out.println("debug");
                p.nbParties++;
                p.nbVictoires = aGagner ? p.nbVictoires+1 : p.nbVictoires;
                if(p.nbParties>0)
                    p.ratio = (float)p.nbVictoires / p.nbParties;
                //enregistrer le fichier. suprimer et reecrire.
                supprimerEnregistrerFichier();
                b = true;
            }
        }
        if(!b){
            listeScores.insere(new Score(pseudo, aGagner ? 1 : 0, 1));
            supprimerEnregistrerFichier();
        }
    }

    void enregsitererScore(String pseudo1, String pseudo2, boolean premierAGagner) {
        enregsitererScore(pseudo1, premierAGagner);
        enregsitererScore(pseudo2, !premierAGagner);
    }

    void supprimerEnregistrerFichier() {
        //fichierClassement.delete();
        fichierClassement = new File(nomFichierClassement);
        try {
            FileWriter fileWriter = new FileWriter(fichierClassement,false);
            Iterateur<Score> iterateur = listeScores.iterateur();
            while(iterateur.aProchain()){
                Score sc = iterateur.prochain();
                String s = sc.pseudo + "\t" + sc.nbVictoires + "\t" + sc.nbParties + "\t" + sc.ratio + "\n";
                fileWriter.write(s);
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
