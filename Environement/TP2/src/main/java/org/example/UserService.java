package org.example;

import org.jetbrains.kotlin.protobuf.ServiceException;

public class UserService {
    private final UtilisateurApi utilisateurApi;
    public UserService(UtilisateurApi utilisateurApi) {
        this.utilisateurApi = utilisateurApi;
    }
    public void creerUtilisateur(Utilisateur utilisateur) throws
            ServiceException {
        utilisateurApi.creerUtilisateur(utilisateur);
    }

    public int getIdUtilisateur(Utilisateur utilisateur) {
        return utilisateur.getId();
    }
}
