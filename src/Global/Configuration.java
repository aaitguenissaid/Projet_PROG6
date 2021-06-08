package Global;

import Structures.Point;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
    public static final String home_directory = "Avalam";
    public static String directory = System.getProperty("user.home") + File.separator + home_directory;

    static Configuration instance = null;

    Properties prop, user_prop;
    Logger logger;
    File user_prop_file;

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

    public static URL chargeURL(String nom) {
        return ClassLoader.getSystemClassLoader().getResource(nom);
    }

    static void chargerProprietes(Properties p, InputStream in, String nom) {

        try {
            p.load(in);
        } catch (IOException e) {
            System.err.println("Impossible de charger " + nom);
            System.err.println(e);
            System.exit(1);
        }
    }

    protected Configuration() {
        //###### On charge les propriétés par défaut
        InputStream in = charge("defaut.cfg");
        prop = new Properties();
        chargerProprietes(prop, in, "defaut.cfg");
        logger().info("Fichier de propriétés defaut.cfg chargé");

        //###### Fichiers et chemins
        String user_props_filename = directory + File.separator + prop.getProperty("user_prop");
        user_prop_file = new File(user_props_filename);

        //###### Création du fichier de propriétés utilisateur s'il n'existe pas
        if(!Files.exists(Paths.get(user_props_filename))) {
            try {
                //Création du répertoire Avalam (s'il n'existe pas) dans le répertoire de l'utilisateur
                Files.createDirectories(Paths.get(directory));
                //Récupération des propriétés par défaut
                prop.store(new FileOutputStream(user_prop_file), "Creation of user's property file");
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

        //###### Chargement des propriétés de l'utilisateur
        user_prop = new Properties();
        try {
            user_prop.load(new FileInputStream(user_prop_file));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        logger().info("Fichier de propriétés utilisateur chargé");
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
        String filename = lis("FichierEmptyPoints");
        InputStream in = charge(filename);
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
        String home = System.getProperty("user.home");
        if(!Files.exists(Paths.get(home + File.separator + home_directory))) {
            try {
                Files.createDirectories(Paths.get(home + File.separator + home_directory));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        String filename = home + File.separator + home_directory + File.separator + nom;
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
        String home = System.getProperty("user.home");
        String filename = home + File.separator + home_directory + File.separator + nom;
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
    
    public static String directoryPath() {
        return System.getProperty("user.home") + File.separator + Configuration.home_directory;
    }

    public String get(String attribut) {
        return get(attribut, false);
    }

    public String get(String attribut, boolean defaut) {
        Properties p = (defaut) ? prop : user_prop;
        if(p.stringPropertyNames().contains(attribut)) {
            return p.getProperty(attribut);
        } else {
            if(prop.stringPropertyNames().contains(attribut)) {
                //Il manque des préférences dans le fichier de l'utilisateur.
                //Donc on essaye de les récupérer dans defaut.cfg
                user_prop.setProperty(attribut, prop.getProperty(attribut));
                return user_prop.getProperty(attribut);
            } else {
                System.err.println("Configuration.get: Attribut invalide");
                return null;
            }
        }
    }

    public void set(String attribut, String value) {
        if(user_prop.stringPropertyNames().contains(attribut)) {
            user_prop.setProperty(attribut, value);
            enregistrerPropriete(attribut);
        } else {
            if(prop.stringPropertyNames().contains(attribut)) {
                //Il manque des préférences dans le fichier de l'utilisateur.
                //Donc on essaye de les récupérer dans defaut.cfg
                user_prop.setProperty(attribut, value);
                enregistrerPropriete(attribut);
            } else {
                System.err.println("Configuration.set: Attribut invalide");
            }
        }
    }

    private void enregistrerPropriete(String attribut) {
        try {
            user_prop.store(new FileOutputStream(user_prop_file), "Update of " + attribut);
        }catch(Exception e) {
            System.err.println("Impossible de sauvegarder la propriété " + attribut + ".");
            System.err.println("La modification sera perdue après la fermeture de l'application.");
            e.printStackTrace();
        }
    }

    public void reinitialiserProprietes() {
        try {
            prop.store(new FileOutputStream(user_prop_file), "Reinitialisation des propriétés utilisateur");
        } catch(Exception e) {
            System.err.println("Impossible de réinitialiser les propriétés utilisateur.");
            e.printStackTrace();
        }
        user_prop = new Properties();
        try {
            user_prop.load(new FileInputStream(user_prop_file));
        }catch(Exception e) {
            System.err.println("Impossible de recharger les préférences après réinitialisation.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public byte[] contenuFichier(String nom) {
        String chemin = lis(nom);
        InputStream is = charge(chemin);

        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] byteArray = buffer.toByteArray();
            return byteArray;
        } catch(Exception e) {
            System.err.println("Impoosible de lire l'Icon " + nom);
            return null;
        }
    }

    public static final String COULEUR_THEME="couleurTheme";
    public static final String RELANCE_AUTOMATIQUE="relanceAutomatique";
    public static final String EST_AUTORISE_HISTORIQUE="estAutoriseHistorique";
    public static final String EST_AUTORISE_SUGGESTION="estAutoriseSuggestion";
    public static final String IA_AFFRONTEMENT="IAAffrontement";
    public static final String IA_1="IA_1";
    public static final String IA_2="IA_2";
    public static final String SON_ON="sonOn";
    public static final String IA_COMMENCE="IACommence";
    public static final String PSEUDO_J1="pseudoJ1";
    public static final String PSEUDO_J2="pseudoJ2";
    public static final String HAUTEUR_IA1="hauteurIA1";
    public static final String HAUTEUR_IA2="hauteurIA2";
    public static final String HAUTEUR_IA_AFFRONTEMENT="hauteurIAAffrontement";


    public static final String ICON_BACK="iconBack";
    public static final String ICON_HELP="iconHelp";
    public static final String ICON_SKIP_PREVIOUS="iconSkipPrevious";
    public static final String ICON_ARROW_LEFT="iconArrowLeft";
    public static final String ICON_STOP="iconStop";
    public static final String ICON_ARROW_RIGHT="iconArrowRight";
    public static final String ICON_SKIP_NEXT="iconSkipNext";
    public static final String ICON_SETTINGS="iconSettings";
    public static final String ICON_ACCOUNT="iconAccount";
    public static final String ICON_DISABLED="iconDisabled";
    public static final String ICON_DONE="iconDone";
    public static final String ICON_VOLUME_UP="iconVolumeUp";
    public static final String ICON_VOLUME_OFF="iconVolumeOff";
    public static final String ICON_PLAY="iconPlay";

    public static final String SOUND_1="sound1";
    public static final String SOUND_2="sound2";
}
