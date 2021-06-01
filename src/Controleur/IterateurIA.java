package Controleur;

import java.util.ArrayList;

public class IterateurIA {
    int indice;
    int indiceVoisin;
    IAFort ia;
    byte[] configuration;

    public IterateurIA(IAFort ia, byte[] configuration){
        int indice = 0;
        int indiceVoisin = 0;
        this.ia = ia;
        this.configuration = configuration;
    }

    public boolean aProchain() {
        if(indice < configuration.length - 1)
            return true;
        else if (indice == configuration.length - 1){
            ArrayList<Integer> voisinsAcc = ia.voisinsAccessibles(configuration, indice);
            return  (indiceVoisin < voisinsAcc.size()) ? true : false;
        }
        else
            return false;
    }

    public byte[] prochain() {

        ArrayList<Integer> voisinsAcc = ia.voisinsAccessibles(configuration, indice);
        if (indice == 0){
            while (voisinsAcc.size() == 0){
                indice++;
                voisinsAcc = ia.voisinsAccessibles(configuration, indice);
            }
        }
        int indiceDepart = indice;
        byte couleurDepart = ia.getCouleur(configuration[indice]);
        byte hauteurDeaprt = ia.getHauteur(configuration[indice]);
        byte[] copy = ia.copyTable(configuration);
        int indiceArrivee = voisinsAcc.get(indiceVoisin);
        byte hauteurArrivee = ia.getHauteur(configuration[indiceArrivee]);
        byte nouveauHauteur = (byte) (hauteurDeaprt + hauteurArrivee);
        byte nouveauValueArrivee = ia.setValue(couleurDepart, nouveauHauteur);
        copy[indiceDepart] = 0;
        copy[indiceArrivee] = nouveauValueArrivee;
        if (indiceVoisin+1 < voisinsAcc.size()){
            indiceVoisin++;
        } else {
            indiceVoisin = 0;
            indice++;
            if (indice < 48){
                ArrayList<Integer> voisinsAc = ia.voisinsAccessibles(configuration, indice);
                while ((voisinsAc.size() == 0) && (indice+1 < 48)){
                    indice++;
                    voisinsAc = ia.voisinsAccessibles(configuration, indice);
                }
            }
        }
        return copy;
    }

    public boolean equals(byte[] tableau){
        for (int i = 0; i < tableau.length; i++){
            if (tableau[i] != configuration[i])
                return false;
        }
        return true;
    }

}
