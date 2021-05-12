package Structures;

public class SequenceListe<Titi> implements Sequence<Titi> {
    Maillon<Titi> tete, queue;
    int taille;
    public Titi teteReturn(){
        if(tete!=null)
            return tete.element;
        else
            return null;
    }
    public SequenceListe(){
        taille = 0;
    }
    public void insereTete(Titi element) {
        Maillon<Titi> nouveau = new Maillon<Titi>();
        nouveau.element = element;
        nouveau.suivant = tete;
        taille++;
        if (tete == null) {
            tete = nouveau;
            queue = nouveau;
        } else {
            tete = nouveau;
        }
    }

    public void insereQueue(Titi element) {
        Maillon<Titi> nouveau = new Maillon<Titi>();
        nouveau.element = element;
        nouveau.suivant = null;
        taille++;
        if (tete == null) {
            tete = nouveau;
        } else {
            queue.suivant = nouveau;
        }
        queue = nouveau;
    }

    public Titi extraitTete() {
        if (tete == null)
            throw new RuntimeException("Sequence vide !");
        Titi resultat = tete.element;
        taille--;
        tete = tete.suivant;
        return resultat;
    }

    public boolean estVide() {
        return tete == null;
    }
    public int taille(){
        return taille;
    }
    @Override
    public Iterateur<Titi> iterateur() {
        return new IterateurListe<Titi>(this);
    }

    public String toString() {
        StringBuilder resultat = new StringBuilder("Sequence liste : [ ");
        Maillon<Titi> courant = tete;
        while (courant != null) {
            resultat.append(courant.element).append(" ");
            courant = courant.suivant;
        }
        resultat.append("]");
        return resultat.toString();
    }
}