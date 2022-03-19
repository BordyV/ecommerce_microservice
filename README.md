# microservice_E_Commerce
 
Ce projet est un projet visant à montrer le fonctionnement d'une application d'e-commerce orientée micro service. 

5 micro services sont disponibles : 
- cart ( panier )
- client ( html )
- order ( commande )
- product ( produit )

On peut :
- consulter les produits
- consulter le détail des produits
- ajouter au panier
- consulter le panier
- passer une commande
- consulter les commandes
- consulter une commande particulière

Le panier est propre à la session, soit la page du navigateur (faire un refresh garde le panier mais quitter la page le perd).

La suppression du panier se fait via le micro service order vérifiant que le panier existe et est bien supprimé avant d'ajouté la commande.