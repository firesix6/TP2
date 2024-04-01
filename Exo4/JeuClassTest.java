package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class JeuClassTest {

    @Mock
    Banque banqueMock = mock(Banque.class);

    //******** QST4 : TEST DU JEU FERME ***************
    @Test
    void testJouerQuandJeuEstFerme() {
        // Arrange
        JeuClass jeu = new JeuClass(banqueMock);
        jeu.fermer(); // On ferme le jeu

        Joueur joueurMock = mock(Joueur.class);
        De de1Mock = mock(De.class);
        De de2Mock = mock(De.class);

        // Act & Assert
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueurMock, de1Mock, de2Mock));

        // Vérification qu'aucune interaction n'a lieu avec les autres objets
        //verifyZeroInteractions(joueurMock, de1Mock, de2Mock, banqueMock);
    }

    //************** QST5 : JOUEUR INSOLAVBLE
}
