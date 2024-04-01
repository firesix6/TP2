package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock1 = mock(UtilisateurApi.class);

    @Test
    void creerUtilisateur() throws ServiceException {
        //********************************************
        //********* 1ERE QUESTION: EXCEPTION **************

        Utilisateur utilisateur = new Utilisateur("Nana", "nars", "nananars@email.com");

        // TODO : Configuration du comportement du mock, utiliser la
        //directive « when » avec sa méthode « thenReturn »
        doThrow(new ServiceException("Erreur lors de la création de l'utilisateur")).when(utilisateurApiMock1).creerUtilisateur(any());


        // TODO : Création du service avec le mock
        UserService userService1 = new UserService(utilisateurApiMock1);

        // TODO : Appel de la méthode à teste
        try {
            userService1.creerUtilisateur(utilisateur);
            // Si aucune exception n'est lancée, le test échoue
            fail("Une exception ServiceException aurait dû être lancée");
        } catch (ServiceException e) {
            // Vérification de l'appel à l'API
            verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);
            // Vérification du message d'exception
            assertEquals("Erreur lors de la création de l'utilisateur", e.getMessage());
        }

        // TODO : Vérification de l'appel à l'API
        verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);


        //********************************************
        //********* 2ERE QUESTION: erreur de valdiation **************

        when(utilisateurApiMock1.creerUtilisateur(utilisateur)).thenReturn(false);

        userService1.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock1).creerUtilisateur(utilisateur);
        verify(utilisateurApiMock1, never()).validerUtilisateur(utilisateur);


    }
}
