import Modele.*;

import java.awt.*;

public class Avalam {
    public static void main(String[] args) {
        Jeu j = new Jeu();
        System.out.println("\n");
        System.out.println(j.bouge(new Point(0,4), new Point(0,5)));
        System.out.println("\n");
        System.out.println(j);
    }
}
