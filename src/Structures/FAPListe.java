package Structures;

public class FAPListe<Bill extends Comparable<Bill>> extends FAP<Bill> {
    SequenceListe<Bill> s;
    int t;
    public FAPListe() {
        t=0;
        s = new SequenceListe<>();
        super.s = s;
    }

    @Override
    public void insere(Bill element) {
        Maillon<Bill> courant, precedent;
        t++;
        precedent = null;
        courant = s.tete;
        while ((courant != null) && (element.compareTo(courant.element) > 0)) {
            precedent = courant;
            courant = courant.suivant;
        }

        Maillon<Bill> m = new Maillon<>();
        m.element = element;
        m.suivant = courant;
        if (precedent == null)
            s.tete = m;
        else
            precedent.suivant = m;
        if (courant == null)
            s.queue = m;
    }
}
