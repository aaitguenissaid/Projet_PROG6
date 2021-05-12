import Modele.Jeu;
import Vue.InterfaceUtilisateur;

import javax.swing.*;

import java.awt.*;

public class Avalam {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new InterfaceUtilisateur());
        Jeu j = new Jeu();
        System.out.println("\n");
        System.out.println(j.bouge(new Point(0,4), new Point(0,5)));
        System.out.println("\n");
        System.out.println(j);
    }
}
