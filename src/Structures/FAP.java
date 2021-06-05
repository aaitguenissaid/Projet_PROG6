package Structures;


public abstract class FAP<Bob> {
    Sequence<Bob> s;

    abstract void insere(Bob element);
    public Bob extrait() {
        return s.extraitTete();
    }
    public boolean estVide() {
        return s.estVide();
    }
    public Iterateur iterateur(){
        return s.iterateur();
    }
}
