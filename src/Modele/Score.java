package Modele;

public class Score implements Comparable<Score> {

    String pseudo;
    int nbVictoires;
    int nbParties;
    float ratio;

    Score(String pseudo, int nbVictoires, int nbParties) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        ratio = nbVictoires / nbParties;
    }
    Score(String pseudo, int nbVictoires, int nbParties, float ratio) {
        this.pseudo = pseudo;
        this.nbVictoires = nbVictoires;
        this.nbParties = nbParties;
        this.ratio = ratio;
    }

    String getPseudo() {
        return pseudo;
    }

    int getNbVictoires() {
        return nbVictoires;
    }

    int getNbParties() {
        return nbParties;
    }

    float getRatio() {
        return ratio;
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
