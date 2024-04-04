package org.example;

public class BanqueImpl implements Banque {

    private int solde;
    private int seuil;

    public BanqueImpl(int solde, int seuil) {
        this.solde = solde;
        this.seuil = seuil;
    }
    @Override
    public boolean est_solvable() {
        return solde >= seuil ;
    }

    @Override
    public void crediter(int i) {
        this.solde += i;
    }

    @Override
    public void debiter(int i) {
        this.solde -= i;
    }
}
