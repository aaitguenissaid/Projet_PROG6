package Modele;

import java.util.Random;

public class TestClassement {

    public static void main(String[] args) {
        Jeu j = new Jeu();
        Classement classement = new Classement(j);
        Random r = new Random();
        int n = 10;
        int m = 100;
        String[] pseudos = {"aaaaa", "bbbbb", "ccccc", "vvvvv", "eeeee", "fffff", "ggggg", "hhhhh", "kkkkk", "jjjjj"};

        for(int i = 0; i<m/2-1; i+=2) {
            int nbPseudo = r.nextInt(n-1);
            Score score = new Score(pseudos[nbPseudo], r.nextInt(10), r.nextInt(10));
            Score score1 = new Score(pseudos[nbPseudo+1], r.nextInt(10), r.nextInt(10));
            classement.enregistrerScore(pseudos[nbPseudo], pseudos[nbPseudo+1],r.nextBoolean());
        }

    }

}
