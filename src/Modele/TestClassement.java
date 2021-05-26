package Modele;

import java.util.Random;

public class TestClassement {

    public static void main(String[] args) {
        Classement classement = new Classement();
        Random r = new Random();
        int n = 10;
        int m = 100;
        String[] pseudos = {"aaaaa", "bbbbb", "ccccc", "vvvvv", "eeeee", "fffff", "ggggg", "hhhhh", "kkkkk", "jjjjj"};

        for(int i = 0; i<m; i++) {
            int nbPseudo = r.nextInt(n);
            Score score = new Score(pseudos[nbPseudo], r.nextInt(10), r.nextInt(10));
            classement.enregsitererScore(pseudos[nbPseudo], r.nextBoolean());
        }

    }

}
