package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock
    private UtilisateurApi utilisateurApiMock1 = mock(UtilisateurApi.class);



    //********************************************
    //********* 1ERE QUESTION: EXCEPTION **************
    @Test
    void creerUtilisateur() throws ServiceException {


        Utilisateur utilisateur = new Utilisateur(123,"Nana", "nars", "nananars@email.com");

        //directive « when » avec sa méthode « thenReturn »
        doThrow(new ServiceException("Erreur lors de la création de l'utilisateur")).when(utilisateurApiMock1).creerUtilisateur(any());


        UserService userService1 = new UserService(utilisateurApiMock1);

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

        verify(utilisateurApiMock1, times(1)).creerUtilisateur(utilisateur);

    }


    //********************************************
    //********* 2ERE QUESTION: erreur de valdiation **************

    @Test
    void ValiditerUtilisateur() throws ServiceException {
        Utilisateur utilisateur = new Utilisateur(123,"Nana", "nars", "nananars@email.com");
        UserService userService1 = new UserService(utilisateurApiMock1);

        doNothing().when(utilisateurApiMock1).creerUtilisateur(Mockito.any());

        userService1.creerUtilisateur(utilisateur);

        verify(utilisateurApiMock1).creerUtilisateur(utilisateur);
        verify(utilisateurApiMock1, never()).validerUtilisateur(utilisateur);
    }

    //********************************************
    //********* 3EME QUESTION: Identifiant **************
    @Test
    void VerifId() throws ServiceException {

        Utilisateur utilisateur = new Utilisateur(123,"Nana", "nars", "nananars@email.com");
        UserService userService1 = new UserService(utilisateurApiMock1);

        // Définition d'un ID fictif
        int idUtilisateur = 123;

        // TODO: Configuration du mock pour renvoyer l'ID
        when(utilisateurApiMock1.getIdUtilisateur(utilisateur)).thenReturn(idUtilisateur);

        // Appel de la méthode à tester
        userService1.creerUtilisateur(utilisateur);

        // TODO: Vérification de l'ID de l'utilisateur
        verify(utilisateurApiMock1).creerUtilisateur(utilisateur);

        // Vérification de l'appel à l'API pour récupérer l'ID de l'utilisateur
//        verify(utilisateurApiMock1).getIdUtilisateur(utilisateur);

        // Vérification de l'ID de l'utilisateur
        assertEquals(idUtilisateur, userService1.getIdUtilisateur(utilisateur));
    }


    //********************************************
    //********* 4EME QUESTION: Arguments **************

    @Test
    void TestArg() throws ServiceException {
        // Créer un captor pour capturer les arguments passés à la méthode creerUtilisateur()
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);


        Utilisateur utilisateur = new Utilisateur(123,"Nana", "nars", "nananars@email.com");
        UserService userService = new UserService(utilisateurApiMock1);

        // Définir le comportement du mock pour la méthode creerUtilisateur() en utilisant le captor
        doNothing().when(utilisateurApiMock1).creerUtilisateur(argumentCaptor.capture());

        // Appeler la méthode à tester
        userService.creerUtilisateur(utilisateur);

        // Récupérer l'utilisateur capturé par le captor
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

        // Vérifier les propriétés de l'utilisateur capturé en utilisant ses getters
        assertEquals(123, utilisateurCapture.getId());
        assertEquals("Nana", utilisateurCapture.getNom());
        assertEquals("nars", utilisateurCapture.getPrenom());
        assertEquals("nananars@email.com", utilisateurCapture.getEmail());


    }
}
