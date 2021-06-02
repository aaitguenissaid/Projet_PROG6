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
    String directory_path = Configuration.directoryPath();
    String nomFichierClassement = directory_path + File.separator + Configuration.instance().lis("user_classement");
    Jeu jeu;

    public Classement(Jeu j) {
        jeu =j;
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
                int lesPoints = Integer.parseInt(mots[3]);
                Score score = new Score(pseudo, nbVictoires, nbParties, lesPoints);
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

    void enregistrerScore(String pseudo, int aGagner, int lesNouveauxPoints) {
        Iterateur<Score> it = listeScores.iterateur();
        boolean b = false;

        while(it.aProchain()){
            Score p = it.prochain();
            if (p.pseudo.equals(pseudo)) {
                p.nbParties++;
                if(aGagner==0) {

                } else {
                    p.nbVictoires += aGagner==1 ? 1 : 0;
                    p.lesPoints += aGagner==1 ? lesNouveauxPoints : -lesNouveauxPoints;
                }
                //enregistrer le fichier. suprimer et reecrire.
                supprimerEnregistrerFichier();
                b = true;
            }
        }
        if(!b){
            listeScores.insere(new Score(pseudo, (aGagner==0) ? 0 : (aGagner==1) ? 1 : 0, 1));
            supprimerEnregistrerFichier();
        }
    }

    // 0 egalité, 1 pseudo1 a gagné, 2 pseudo2 à gagné.
    public void enregistrerScore(String pseudo1, String pseudo2, int quiAGagner) { // 0, 1, 2
        //TODO ids codé en dur!
        int lesNouveauxPoints =  calculePointsAbsolu();
        enregistrerScore(pseudo1, (quiAGagner==0) ? 0 : (quiAGagner==1) ? 1 : -1, lesNouveauxPoints);
        enregistrerScore(pseudo2, (quiAGagner==0) ? 0 : (quiAGagner==2) ? 1 : -1 , lesNouveauxPoints);
    }

    void supprimerEnregistrerFichier() {
        fichierClassement = new File(nomFichierClassement);
        try {
            FileWriter fileWriter = new FileWriter(fichierClassement,false);
            Iterateur<Score> iterateur = listeScores.iterateur();
            while(iterateur.aProchain()){
                Score sc = iterateur.prochain();
                String s = sc.pseudo + "\t" + sc.nbVictoires + "\t" + sc.nbParties + "\t" + sc.lesPoints + "\n";
                fileWriter.write(s);
            }
            fileWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    int calculePointsAbsolu() {
        int nbPileJ1 = jeu.nbPilesJoueur(1);
        int nbPileJ2 = jeu.nbPilesJoueur(2);
        int lesPoints = Math.abs(nbPileJ1 - nbPileJ2);
        return lesPoints;
    }
}
