l'environement est le dossier intelliij du tp
EXO1:
Pour cet exercice, le probleme du getstate est qu'on peut pas l'utiliser jusqu'avoir au moins 2 instance de la classe calculatrice
--------------------------------------------------------------------------
EXO2:
On fait deux type de test, pour la creation adequate et correcte du user
et un autre pour la creation d'un utilisateur qui engendre une exception
--------------------------------------------------------------------------
Exo3 :
1- traitement d'exception avec le try-catch
2- les erreurs de validation: on cree une methode de validation pour etre sure qu'elle n'est pas appelé et cella en utilisant le never
3- pour l'identifiant, de la meme maniere on cree une methode getId pour faire la verif avec le champs id ajouter aussi 
4- pour les argument on utilise les capteur d'argument et on fait la verif de tout les argument passé connu 
-------------------------------------------------------------------------
EXO4 : 
1- les objets mockés pour tester ka methode jouer sont: l'objet joueur, banque et les deux objets dé
justification: 
-> le joueur doit proposer une mise et etre debite du montant de son pari
-> les dés pour generer les resultats aleatoire 
-> la banque pour pouvoir cree une instance jeu 

2- les classes d'equivalence: 
-> jouer non solvable : perdre/fermer
-> la somme ne vaut pas 7 : perdre/fermer
-> la somme = 7 et banque solvable : Gagner
-> banque non solvable : perdre/fermer


3- codage

4- pour le cas de fermé, on peut confondre entre un test d'etat et test d'interaction, car plusieur interaction sont impliquer pour savoir si c fermé ou pas, mais l'information principale est l'etat interne de la classe Jeu 
-> pour l'implemetation, on verifie directement si le jeu est fermer ou pas 
(j'ai essayer de confirmer cella avec VerifyInteraction aussi, pour etre sure qu'aucune interaction n'a eu lieu !)

5-pour etre sure que le joueur ne touche pas les des, on peut lever une exception quand la fonction debiter est appelé
->si on parle de la verification que le jeu reagit correctement, on peut dire que c un test d'etat, par contre si on veut savoir si le joueur ne touche pas les des apres, on peut dire que c un test d'interaction 

6- on ajoute les deux scenario ou le joueur gagne (somme = 7 et banque solvable )
aussi le cas ou la somme = 7 mais la banque est insolvable ce qui fait fermer le jeu.

7- pour cette question, je cree une nouvelle classe BanqueImpl pour ajouter le seuil, et une nouvelle JeuClassNew pour verifier la banque
