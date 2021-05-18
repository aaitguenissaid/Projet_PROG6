package Modele;

import Global.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class Preferences {
    public static final String COULEUR_J1="couleurJ1";
    public static final String COULEUR_J2="couleurJ2";
    public static final String COULEUR_THEME="couleurTheme";
    public static final String RELANCE_AUTOMATIQUE="relanceAutomatique";
    public static final String EST_AUTORISE_HISTORIQUE="estAutoriseHistorique";
    public static final String EST_AUTORISE_SUGGESTION="estAutoriseSuggestion";
    public static final String IA_AFFRONTEMENT="IA_Affrontement";
    public static final String IA_1="IA_1";
    public static final String IA_2="IA_2";

    public static final ArrayList<String> attributs = attributsValides();

    Properties user_props;
    Properties default_props;
    File user_props_file;

    private static ArrayList<String> attributsValides() {
        ArrayList<String> ret = new ArrayList<>();
        ret.add(COULEUR_J1);
        ret.add(COULEUR_J2);
        ret.add(COULEUR_THEME);
        ret.add(RELANCE_AUTOMATIQUE);
        ret.add(EST_AUTORISE_HISTORIQUE);
        ret.add(EST_AUTORISE_SUGGESTION);
        ret.add(IA_AFFRONTEMENT);
        ret.add(IA_1);
        ret.add(IA_2);
        return ret;
    }

    public Preferences() {
        //Propriétés par défaut
        default_props = Configuration.instance().getProperties();

        //Fichiers et chemins
        String directory_path = System.getProperty("user.home") + File.separator + Configuration.home_directory;
        String user_props_filename = directory_path + File.separator + Configuration.instance().lis("user_config");
        user_props_file = new File(user_props_filename);

        //Création du fichier de propriétés utilisateur s'il n'existe pas
        if(!Files.exists(Paths.get(user_props_filename))) {
            try {
                //Création du répertoire Avalam (s'il n'existe pas) dans le répertoire de l'utilisateur
                Files.createDirectories(Paths.get(directory_path));
                //Récupération des propriétés par défaut
                default_props.store(new FileOutputStream(user_props_file), "Creation of user's property file");
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        user_props = new Properties();

        //Chargement des propriétés de l'utilisateur
        try {
            user_props.load(new FileInputStream(user_props_file));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String get(String attribut) {
        return get(attribut, false);
    }

    public String get(String attribut, boolean defaut) {
        Properties p = (defaut) ? default_props : user_props;
        if(attributs.contains(attribut)) {
            return p.getProperty(attribut);
        } else {
            System.err.println("Preferences.get: Attribut invalide");
            return null;
        }
    }

    public void set(String attribut, String value) {
        if(attributs.contains(attribut)) {
            user_props.setProperty(attribut, value);
            enregistrerPropriete(attribut);
        } else {
            System.err.println("Preferences.set: Attribut invalide");
        }
    }

    private void enregistrerPropriete(String attribut) {
        try {
            user_props.store(new FileOutputStream(user_props_file), "Update of " + attribut);
        }catch(Exception e) {
            System.err.println("Impossible de sauvegarder la propriété " + attribut + ".");
            System.err.println("La modification sera perdue après la fermeture de l'application.");
            e.printStackTrace();
        }
    }

    public void reinitialiserProprietes() {
        try {
            default_props.store(new FileOutputStream(user_props_file), "Reinitialisation des preferences utilisateur");
        } catch(Exception e) {
            System.err.println("Impossible de réinitialiser les préférences utilisateur.");
            e.printStackTrace();
        }
        user_props = new Properties();
        try {
            user_props.load(new FileInputStream(user_props_file));
        }catch(Exception e) {
            System.err.println("Impossible de recharger les préférences après réinitialisation.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    //Tests des préférences
    /*public static void main(String[] args) {
        Preferences prefs = new Preferences();
        System.out.println(prefs.get(Preferences.COULEUR_J1, false));
        System.out.println(prefs.get(Preferences.COULEUR_J2, true));
        System.out.println(prefs.get(Preferences.EST_AUTORISE_HISTORIQUE, false));
        System.out.println(prefs.get(Preferences.EST_AUTORISE_SUGGESTION, false));
        System.out.println(prefs.get(Preferences.IA_1, true));
        System.out.println(prefs.get(Preferences.IA_2, false));

        System.out.println("\nCouleur J1 avant update : " + prefs.get(Preferences.COULEUR_J1));
        prefs.set(Preferences.COULEUR_J1, String.valueOf(10));
        System.out.println("Couleur J1 après update : " + prefs.get(Preferences.COULEUR_J1));

        prefs.reinitialiserProprietes();
    }*/
}
