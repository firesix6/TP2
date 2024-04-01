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
    @Mock
    private UtilisateurApi utilisateurApiMock2 = mock(UtilisateurApi.class);

    @Test
    void creerUtilisateur() throws ServiceException  {

        // Création d'un nouvel utilisateur
        Utilisateur utilisateur = new Utilisateur("Nana", "nars", "nananars@email.com");

        // TODO : Configuration du comportement du mock, utiliser la
        //directive « when » avec sa méthode « thenReturn »
        doThrow(new ServiceException("Erreur lors de la création de l'utilisateur")).when(utilisateurApiMock2).creerUtilisateur(any());
        doNothing().when(utilisateurApiMock1).creerUtilisateur(utilisateur);


        // TODO : Création du service avec le mock
        UserService userService1 = new UserService(utilisateurApiMock1);
        UserService userService2 = new UserService(utilisateurApiMock2);


        // TODO : Appel de la méthode à teste
        userService1.creerUtilisateur(utilisateur);
        // Appel de la méthode à tester qui doit lancer une exception
        try {
            userService2.creerUtilisateur(utilisateur);
            // Si aucune exception n'est lancée, le test échoue
            fail("Une exception ServiceException aurait dû être lancée");
        } catch (ServiceException e) {
            // Vérification de l'appel à l'API
            verify(utilisateurApiMock2, times(1)).creerUtilisateur(utilisateur);
            // Vérification du message d'exception
            assertEquals("Erreur lors de la création de l'utilisateur", e.getMessage());
        }


        // TODO : Vérification de l'appel à l'API
        verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);
        }
}
