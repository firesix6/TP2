package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    // Création des doublures (mocks)

    Joueur joueurInsolvable = mock(Joueur.class);
    De de1 = mock(De.class);
    De de2 = mock(De.class);
    Banque banque = mock(Banque.class);
    @Test
    void testJoueurInsolvable() throws JeuFermeException, DebitImpossibleException {

        // Configuration du joueur insolvable pour lancer une exception lorsqu'il est débité
        doThrow(new DebitImpossibleException("Joueur Insolavable")).when(joueurInsolvable).debiter(anyInt());

        // Création du jeu avec la banque
        JeuClass jeu = new JeuClass(banque);

        // Exécution du test
        //jeu.jouer(joueurInsolvable, de1, de2);
        assertThrows(JeuFermeException.class, () -> jeu.jouer(joueurInsolvable, de1, de2));

        // Vérification que le jeu n'a pas touché aux dés
        verifyNoInteractions(de1, de2);

        // Vérification que la banque n'a pas été sollicitée
        verifyNoInteractions(banque);
    }
}
