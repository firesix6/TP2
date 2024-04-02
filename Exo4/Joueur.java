package org.example;

public interface Joueur {
    int mise();

    void debiter(int mise) throws DebitImpossibleException;

    void crediter(int i);
    boolean joueurEstSolvable(int mise);
}
