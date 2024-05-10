package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class JeuClassTest {

    Joueur joueur = mock(Joueur.class);
    De de1 = mock(De.class);
    De de2 = mock(De.class);
    Banque banque = mock(Banque.class);

    //******** QST4 : TEST DU JEU FERME ***************
    @Test
    void testJouerQuandJeuEstFerme() {

        JeuClass jeu = new JeuClass(banque);
        jeu.fermer(); // ferme le jeu

        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));

        verifyNoInteractions(joueur, de1, de2, banque);
    }

    //************** QST5 : JOUEUR INSOLAVBLE

    @Test
    void testJoueurInsolvable() throws DebitImpossibleException {

        doThrow(new DebitImpossibleException("Joueur Insolavable")).when(joueur).debiter(anyInt());

        JeuClass jeu = new JeuClass(banque);
        // jeu.jouer(joueurInsolvable, de1, de2);
        assertThrows(DebitImpossibleException.class, () -> jeu.jouer(joueur, de1, de2));

        verifyNoInteractions(de1, de2);

        verifyNoInteractions(banque);
    }

    //***********************************************
    //********* QST6 : LES AUTRE SCENARIO:
    // ** 6.1: GAGNER: SOMME = 7 ET BANQUE SOLVABLE
    @Test
    void TestJoueurGagne() throws DebitImpossibleException, JeuFermeException {

        //configurer du joueur pour qu'il soit solvable: mise et debiter:
        when(joueur.mise()).thenReturn(10);
        when(joueur.joueurEstSolvable(10)).thenReturn(true);

        when(de1.lancer()).thenReturn(4);
        when(de2.lancer()).thenReturn(3);
        when(banque.est_solvable()).thenReturn(true);

        JeuClass jeu = new JeuClass(banque);
        jeu.jouer(joueur, de1, de2);

        verify(joueur, times(1)).crediter(20);
        verify(banque, times(1)).debiter(20);

    }

    // ** 6.2 SOMME = 7 MAIS BANQUE INSOLVABLE
    @Test
    void TestBanqueInsolvable() throws JeuFermeException, DebitImpossibleException {

        when(joueur.mise()).thenReturn(10);
        when(joueur.joueurEstSolvable(10)).thenReturn(true);

        when(de1.lancer()).thenReturn(4);
        when(de2.lancer()).thenReturn(3);
        when(banque.est_solvable()).thenReturn(false);

        JeuClass jeu = new JeuClass(banque);
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));


        verify(joueur).mise();
        verify(joueur).joueurEstSolvable(10);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(banque).est_solvable();
    }

}
