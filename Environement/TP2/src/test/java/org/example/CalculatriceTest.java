package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalculatriceTest {
    @Mock
    private Calculatrice calcul = mock(Calculatrice.class);

    @Test
    public void testAdditionner() {
        // Définition du comportement de la méthode "additionner"
        when(calcul.additionner(2, 3)).thenReturn(5);

        // Appel de la méthode à tester
        int resultat = calcul.additionner(2, 3);

        // Vérification du résultat
        assertEquals(5, resultat);

        // Vérification que la méthode "additionner" a été appelée
        verify(calcul).additionner(2, 3);

        // Vérification qu'aucune autre méthode n'a été appelée sur
        // l'objet après l'appel de la méthode "additionner"
        verifyNoMoreInteractions(calcul);

        // TODO : Vérification de l'état de l'objet après l'appel de la
        //méthode "additionner", penser à utiliser la méthode
        //« getState() » de la directive « verify » : // exemple :
        //verify(calcul).getState();
    }
}
