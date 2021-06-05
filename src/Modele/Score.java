package Modele;

public class Score implements Comparable<Score> {

    String pseudo;
    int nbVictoires;
    int nbParties;
    int lesPoints;

    Score(String pseudo, int nbVictoires, int nbParties) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        if(nbParties>0)
            lesPoints = 500;
    }

    Score(String pseudo, int nbVictoires, int nbParties, int lesPoints) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        this.lesPoints = lesPoints;
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

    void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    void setNbVictoires(int nbVictoires) {
        this.nbVictoires = nbVictoires;
    }

    void setNbParties(int nbParties) {
        this.nbParties = nbParties;
    }

    @Override
    public int compareTo(Score o) {
        return nbVictoires;
    }
}
