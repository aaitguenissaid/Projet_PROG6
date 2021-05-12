import Vue.InterfaceUtilisateur;

import javax.swing.*;

public class Avalam {
    public static void main(String[] args) {
        Jeu j = new Jeu();
        SwingUtilities.invokeLater(new InterfaceUtilisateur());
    }
}
