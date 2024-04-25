package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;

public interface UtilisateurApi {
    void creerUtilisateur(Utilisateur utilisateur) throws ServiceException;

    void validerUtilisateur(Utilisateur utilisateur);

    int getIdUtilisateur(Utilisateur utilisateur);
}