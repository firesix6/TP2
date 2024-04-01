package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;

public interface UtilisateurApi {
    void creerUtilisateur(Utilisateur utilisateur) throws ServiceException;
}