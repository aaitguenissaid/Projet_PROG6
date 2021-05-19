package Modele;

import Global.Configuration;

import java.io.File;

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
}
