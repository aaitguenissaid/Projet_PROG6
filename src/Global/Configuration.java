package Global;

import Structures.Sequence;
import Structures.SequenceListe;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
    static Configuration instance = null;
    Properties prop;
    Logger logger;
    public static final String home_directory = "Avalam";

    public static Configuration instance() {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }

    public static InputStream charge(String nom) {
        // La méthode de chargement suivante ne dépend pas du système de fichier et sera
        // donc utilisable pour un .jar
        // Attention, par contre, le fichier doit se trouver dans le CLASSPATH
        return ClassLoader.getSystemClassLoader().getResourceAsStream(nom);
    }

    static void chargerProprietes(Properties p, InputStream in, String nom) {

        try {
            p.load(in);
        } catch (IOException e) {
            System.err.println("Impossible de charger " + nom);
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    protected Configuration() {
        // On charge les propriétés
        InputStream in = charge("defaut.cfg");
        Properties defaut = new Properties();
        chargerProprietes(defaut, in, "defaut.cfg");
        // Il faut attendre le dernier moment pour utiliser le logger
        // car celui-ci s'initialise avec les propriétés
        String message = "Fichier de propriétés defaut.cfg chargé";
        String nom = System.getProperty("user.home") + File.separator + ".sokoban";
        try {
            in = new FileInputStream(nom);
            prop = new Properties(defaut);
            chargerProprietes(prop, in, nom);
            logger().info(message);
            logger().info("Fichier de propriétés " + nom + " chargé");
        } catch (FileNotFoundException e) {
            prop = defaut;
            logger().info(message);
        }
    }

    public String lis(String nom) {
        String value = prop.getProperty(nom);
        if (value != null) {
            return value;
        } else {
            throw new NoSuchElementException("Propriété " + nom + " manquante");
        }
    }

    public String lisFichier(String nom) {
        String fichier = lis(nom);
        fichier = fichier.replace('/', File.separatorChar);
        return fichier;
    }

    public Logger logger() {
        if (logger == null) {
            System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s : %5$s%n");
            logger = Logger.getLogger("Sokoban.Logger");
            logger.setLevel(Level.parse(lis("LogLevel")));
        }
        return logger;
    }

    //Retourne une liste de Points devant être vides dans la grille du Avalam
    public ArrayList<Point> getEmptyPoints() {
        String filename = this.lisFichier("FichierEmptyPoints");
        InputStream in = Configuration.charge(filename);
        if(in==null) return new ArrayList<>();
        Scanner sc = new Scanner(in);

        ArrayList<Point> ret = new ArrayList<>();
        while(sc.hasNextLine()) {
            String pt = sc.nextLine();
            String[] coords = pt.split(",");
            if(coords.length<2) continue;
            Point p = new Point (Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
            ret.add(p);
        }

        return ret;
    }

    public PrintWriter ouvreFichierEcriture(String nom) {
        return ouvreFichierEcriture(nom, "");
    }

    public PrintWriter ouvreFichierEcriture(String nom, String complement) {
        String file = this.lisFichier(nom) + complement;
        String home = System.getProperty("user.home");
        if(!Files.exists(Paths.get(home + File.separator + home_directory))) {
            try {
                Files.createDirectories(Paths.get(home + File.separator + home_directory));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        String filename = home + File.separator + home_directory + File.separator + file;
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(filename);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return printWriter;
    }

    public Properties getProperties() {
        return this.prop;
    }

    public Scanner ouvrirFichierLecture(String nom) {
        return ouvrirFichierLecture(nom, "");
    }

    public Scanner ouvrirFichierLecture(String nom, String complement) {
        String file = this.lisFichier(nom) + complement;
        String home = System.getProperty("user.home");
        String filename = home + File.separator + home_directory + File.separator + file;
        if(!Files.exists(Paths.get(filename))) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
        } catch(Exception e) {
            return null;
        }
        return new Scanner(inputStream);
    }
}
