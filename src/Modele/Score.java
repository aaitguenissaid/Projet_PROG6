package Modele;

public class Score implements Comparable<Score> {

    String pseudo;
    int nbVictoires;
    int nbParties;
    float ratio;
    int lesPoints;

    Score(String pseudo, int nbVictoires, int nbParties) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        if(nbParties>0) {
            ratio = (float) nbVictoires / nbParties;
            lesPoints = 500;
        }
    }

    Score(String pseudo, int nbVictoires, int nbParties, int lesPoints) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        this.lesPoints = lesPoints;
        if(nbParties>0)
            ratio = (float) nbVictoires / nbParties;

    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbVictoires() {
        return nbVictoires;
    }

    public int getNbParties() {
        return nbParties;
    }

    public int getLesPoints() {
        return lesPoints;
    }

    @Override
    public int compareTo(Score o) {
        return o.lesPoints- lesPoints;
    }
}
