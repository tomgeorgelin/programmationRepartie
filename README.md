

# reverseImage project 

Projet de programmation répartie en Java (rmi)
@IUT Nancy Charlemagne
2019-2020

 - Baudon Nicolas
 - Georgelin Tom
## URL vers webetu :

[Lien vers la page de téléchargement des 
ressources](https://webetu.iutnc.univ-lorraine.fr/www/georgeli4u/progRep)


## Fonctionnement 

Ce projet permet de répartir le traitement d'un calcul (ici effet miroir 
d'une image). 
Un client va vouloir inverser sont image et pour cela va l'envoyer au 
serveur qui va la découper puis la distribuer à des nœuds de calcul 
(machines). Ces machines vont chacune traiter une petite partie de 
l'image puis la renvoyer. Ce qui va permettre de recréer l'image et de 
la retourner au client au client. 
 

## Fonctionnement 

- Dans le dossier serveur 
	- Lancer rmiregistry
	- Tout compiler et lancer le main
- Dans le dossier noeud
	- Tout compiler et lancer le main avec en argument l'ip du 
serveur  
- Dans le dossier client
	- Tout compiler et lancer le main avec en argument l'image ainsi 
que l'ip du serveur  

Si tout ce passe bien le client trouvera dans le dossier un fichier 
"result.jpg" qui sera l'image inversée. 

