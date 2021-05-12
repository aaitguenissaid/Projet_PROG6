package Structures;


public class SequenceListe<Titi> implements Sequence<Titi> {
    Maillon<Titi> tete, queue;
    public Titi teteReturn(){
        if(tete!=null)
            return tete.element;
        else
            return null;
    }
    public void insereTete(Titi element) {
        Maillon<Titi> nouveau = new Maillon<>();
        nouveau.element = element;
        nouveau.suivant = tete;
        if (tete == null) {
            tete = nouveau;
            queue = nouveau;
        } else {
            tete = nouveau;
        }
    }

    public void insereQueue(Titi element) {
        Maillon<Titi> nouveau = new Maillon<>();
        nouveau.element = element;
        nouveau.suivant = null;
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
        tete = tete.suivant;
        return resultat;
    }

    public boolean estVide() {
        return tete == null;
    }

    @Override
    public Iterateur<Titi> iterateur() {
        return new IterateurListe<>(this);
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