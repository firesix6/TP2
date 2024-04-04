package org.example;

public interface Banque {
    boolean est_solvable();
    void crediter(int i);

    void debiter(int i);
}
