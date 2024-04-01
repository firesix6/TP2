package org.example;
public class JeuClass {
    private Banque banque;
    private boolean ferme;

    public JeuClass(Banque labanque) {
        this.banque = labanque;
        this.ferme = false;
    }

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!estOuvert()) {
            throw new JeuFermeException("Le jeu est fermé.");
        }

        int mise = joueur.mise();
        joueur.debiter(mise);
        int resultatDe1 = de1.lancer();
        int resultatDe2 = de2.lancer();
        int sommeDes = resultatDe1 + resultatDe2;
        if (sommeDes == 7) {
            if (banque.est_solvable()) {
                joueur.crediter(2 * mise);
                banque.debiter(2 * mise);
            } else {
                fermer();
                throw new JeuFermeException("La banque n'est plus solvable.");
            }
        } else {
            fermer();
        }
    }

    public void fermer() {
        // Fermeture du jeu
        this.ferme = true;
    }

    public boolean estOuvert() {
        // Vérification si le jeu est ouvert
        if (ferme) return false; 
        return true;
    }
}
