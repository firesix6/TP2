package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
class JeuClassNewTest {

    Joueur joueur = mock(Joueur.class);
    De de1 = mock(De.class);
    De de2 = mock(De.class);
    BanqueImpl banque = mock(BanqueImpl.class);

    @Test
    void testBanqueInsolvable() throws JeuFermeException, DebitImpossibleException {
// Configuration des mocks
        when(joueur.mise()).thenReturn(10);
        when(joueur.joueurEstSolvable(10)).thenReturn(true);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);
        when(banque.est_solvable()).thenReturn(true, false);


        // Création de l'instance de JeuClassNew à tester
        JeuClassNew jeu = new JeuClassNew(banque);

        // Exécution du test
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueur, de1, de2));

        // Vérification des interactions

        verify(joueur).mise();
        verify(joueur).joueurEstSolvable(10);
        verify(de1).lancer();
        verify(de2).lancer();
        verify(banque, times(2)).est_solvable();
        verifyNoMoreInteractions(joueur, de1, de2, banque);

    }

}