# NeisisMinage
C'est un plugin conçu pour un serveur minage, il y a une économie à base de points et un système d'expérience. Le joueur 
reçois à sa connexion une pioche configuré dans le fichier de configuration à l'emplacement 0 dans son inventaire. Cette pioche est
incassable. Lorsqu'un joueur monte de niveau, il est possible d'ajouter des enchantements sur la pioche. Lorsqu'un joueur mine des blocs
qui permettent de gagner de l'expérience / points, le minerai n'est pas loot. Le plugin intègre un shop qui permet de 
dépenser les points cumulés en minant.

## Commandes :
- **/xpmine** Permettre au joueur de voir son niveau, son expérience et le nombre d'expérience requis pour monter de niveau
- **/pointsmine** Permettre au joueur de voir son solde de points
- **/minepoints [joueur]** Voir le solde de points d'un joueur
- **/minepointsadd [joueur] [nombre]** Ajouter des points à un joueur
- **/minepointstake [joueur] [nombre]** Retirer des points à un joueur
- **/minepointsreset [joueur]** Réinitialiser les points d'un joueur
- **/minexp [joueur]** Voir l'expérience d'un joueur
- **/minexpadd [joueur] [nombre]** Ajouter de l'expérience à un joueur
- **/minexpremove [joueur] [nombre]** Retirer de l'expérience à un joueur
- **/minexpreset [joueur]** Réinitialiser l'expérience d'un joueur
- **/mineshop** Ouvrir le menu shop
- **/minageshopcreate [prix]** Créer un shop à partir de l'item qu'à le joueur dans la main

## Permissions :
- **neisisminage.commands.balance** Exécuter la commande */minepoints*
- **neisisminage.commands.add** Exécuter la commande */minepointsadd*
- **neisisminage.commands.take** Exécuter la commande */minepointstake*
- **neisisminage.commands.reset** Exécuter la commande */minepointsreset*
- **neisisminage.commands.xp** Exécuter la commande */minexp*
- **neisisminage.commands.xp.add** Exécuter la commande */minexpadd*
- **neisisminage.commands.xp.remove** Exécuter la commande */minexpremove*
- **neisisminage.commands.xp.reset** Exécuter la commande */minexpreset*
- **neisisminage.shop.create** Exécuter la commande */minageshopcreate*
- **neisisminage.shop.delete** Pouvoir supprimer un shop

## Créer un shop
Pour créer un shop, un joueur ayant la permission requise doit effectuer la commande **/minageshopcreate** avec un 
item dans la main, le prix doit être un nombre entier supérieur à 0. Aucun shop similaire ne doit déjà exister.

## Supprimer un shop
Pour supprimer un shop, un joueur ayant la permission requise doit effectuer la commande **/mineshop**. Il pourra cliquer 
sur sa touche de drop afin de supprimer le shop

## Technique :
- Java **8**
- Spigot **1.8.8**
- Base de donnée **MySQL**