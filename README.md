## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

1) # Création de l’architecture d’un jeu séquentiel
   - ## Game.java

      Ce fichier contient la classe abstraite `Game` qui représente un jeu générique. Cette classe implémente l'interface `Runnable` et étend la classe `Observable`.
      
      ### Fonctionnalités
      
      - `turn` : un entier représentant le tour actuel du jeu.
      - `maxTurn` : un entier représentant le nombre maximum de tours du jeu.
      - `isRunning` : un booléen indiquant si le jeu est en cours d'exécution.
      - `thread` : un objet `Thread` utilisé pour exécuter le jeu en tant que processus séparé.
      - `time` : un long représentant le temps de pause entre chaque tour du jeu.
      
      ### Méthodes
      
      - `init()` : initialise le jeu en appelant la méthode abstraite `initializeGame()`, met à jour les observateurs et les notifie.
      - `step()` : effectue une étape du jeu en appelant la méthode abstraite `takeTurn()`, met à jour les observateurs et les notifie. Si le jeu est terminé ou si le nombre maximum de tours est atteint, appelle la méthode `gameOver()`.
      - `pause()` : met le jeu en pause en réinitialisant le booléen `isRunning` et en arrêtant le thread.
      - `run()` : exécute le jeu en boucle tant que `isRunning` est vrai. Appelle la méthode `step()` et met le thread en pause pendant le temps spécifié.
      - `lunch()` : démarre le jeu en initialisant le booléen `isRunning` à vrai et en créant un nouveau thread pour exécuter le jeu.
      
      ### Utilisation
      
      Pour utiliser la classe `Game`, vous devez créer une classe qui étend `Game` et implémenter les méthodes abstraites `initializeGame()`, `takeTurn()`, `gameContinue()` et `gameOver()`. Vous pouvez également ajouter des observateurs pour suivre l'état du jeu.

   - # SimpleGame.java

      Ce fichier contient la classe SimpleGame, qui est une sous-classe de la classe Game. La classe SimpleGame représente un jeu simple avec des tours et une condition de fin.

      ## Utilisation
      
      Pour utiliser la classe SimpleGame, vous devez l'instancier en spécifiant un entier en paramètre lors de la création de l'objet. Cet entier représente le nombre maximum de tours du jeu.

   - #Tests fait.


2) # Création des premiers éléments de l’interface graphique
   - ## Ecran.java
      
      Ce fichier contient la classe abstraite `Ecran` qui représente une fenêtre d'affichage dans le jeu Pac-Man.
      
      ### Fonctionnalités
      
      - La classe `Ecran` est une classe abstraite qui implémente l'interface `Observer`.
      - Elle contient des attributs tels que `game` (instance de la classe `Game`), `ecran` (instance de `JFrame`), `hauteur` et `largeur`.
      - Le constructeur de `Ecran` prend en paramètre une instance de `Game` et un titre pour la fenêtre.
      - La méthode `setDimension` permet de définir la taille de la fenêtre.
      - La méthode `setLocation` permet de définir la position de la fenêtre sur l'écran.
      - Les méthodes `setHauteur` et `setLargeur` permettent de définir respectivement la hauteur et la largeur de la fenêtre.
      - Les méthodes `afficher` et `masquer` permettent respectivement d'afficher et de masquer la fenêtre.

   - ## ViewSimpleGame.java

      Ce fichier contient la classe `ViewSimpleGame` qui est une vue pour le jeu PacMan. Il s'agit d'une extension de la classe `Ecran` et implémente l'interface `Observer`.
      
      ### Fonctionnalités
      
      - Affiche le tour actuel du jeu dans une étiquette (`JLabel`).
      - Met à jour l'étiquette lorsque le tour du jeu change.
      
      ### Utilisation
      
      1. Importez la classe `ViewSimpleGame` dans votre projet.
      2. Créez une instance de `Game` (modèle du jeu).
      3. Créez une instance de `ViewSimpleGame` en passant l'instance de `Game` en paramètre.
      4. Ajoutez l'instance de `ViewSimpleGame` à votre interface utilisateur.
   

   - ## ViewCommand.java
      Ce fichier contient la classe ViewCommand qui est une vue pour afficher les commandes du jeu. Cette classe hérite de la classe Ecran et implémente l'interface Observer.
      
      ### Attributs
         - global : un objet de type JPanel qui représente le conteneur global de la vue.
         - haut : un objet de type JPanel qui représente la partie supérieure de la vue.
         - bas : un objet de type JPanel qui représente la partie inférieure de la vue.
         - infoTour : un objet de type JLabel qui affiche le numéro du tour en cours.
         - restart : un objet de type JButton qui permet de redémarrer le jeu.
         - pause : un objet de type JButton qui permet de mettre le jeu en pause.
         - run : un objet de type JButton qui permet de lancer le jeu.
         - step : un objet de type JButton qui permet d'avancer d'un tour dans le jeu.
         - slider : un objet de type JSlider qui permet de régler le nombre de tours par seconde.
         - control : un objet de type AbstractController qui représente le contrôleur associé à la vue.

     ### Constructeur
      Le constructeur de la classe ViewCommand prend en paramètres un objet de type Game et un objet de type AbstractController. Il initialise les différents composants de la vue et les ajoute au conteneur global. Il définit également la dimension et la position de la fenêtre de la vue.
      
     ### Méthode update
      La méthode update est une méthode de l'interface Observer qui est appelée lorsqu'un changement est observé dans l'objet observé. Dans cette méthode, le numéro du tour en cours est mis à jour et affiché dans le label infoTour.



3) # Création du Modèle-Vue-Contrôleur
   1. ## Mise en place du design pattern Observateur
      [!IMPORTANT] [Consulter la la section](#Création des premiers éléments de l’interface graphique)


   2. ##Création du contrôleur pour l’interface graphique
      ### AbstractController
         
         Ce fichier contient la classe `AbstractController` qui est une classe abstraite utilisée pour contrôler le jeu PacMan.
         
         ### Utilisation
         
         Pour utiliser la classe `AbstractController`, vous devez l'étendre et implémenter les méthodes nécessaires pour contrôler le jeu.
         
         ### Méthodes
         
         - `restart()`: Cette méthode permet de redémarrer le jeu en mettant en pause le jeu actuel et en initialisant un nouveau jeu.
         - `step()`: Cette méthode permet de faire avancer le jeu d'un pas.
         - `play()`: Cette méthode permet de lancer le jeu.
         - `pause()`: Cette méthode permet de mettre en pause le jeu.
         - `setSpeed(double speed)`: Cette méthode permet de définir la vitesse du jeu.

   3. # Ajout des écouteurs sur les différents composants de l’interface de commande
      - ## ControllerSimpleGame.java
      
         Ce fichier contient la classe `ControllerSimpleGame`, qui est responsable de la gestion des interactions entre le modèle de jeu, les vues et les actions de l'utilisateur.
         
         ### Fonctionnalités
         
         - Crée une instance de `SimpleGame` avec une vitesse de jeu de 150.
         - Initialise les vues `ViewSimpleGame` et `ViewCommand` pour afficher le jeu et les commandes respectivement.
         - Associe des écouteurs d'événements aux boutons de la vue `ViewCommand` pour gérer les actions de l'utilisateur (redémarrer, mettre en pause, jouer, avancer d'un pas).
         - Associe un écouteur de changement d'état au curseur de vitesse de la vue `ViewCommand` pour ajuster la vitesse du jeu.
         - Définit des comportements spécifiques pour chaque action de l'utilisateur en créant des instances de classes de comportement correspondantes.
         - Affiche les vues `ViewSimpleGame` et `ViewCommand` à des emplacements spécifiques sur l'écran.
         - Initialise le comportement par défaut pour le bouton d'arrêt dans la vue `ViewCommand`.
         
         ### Utilisation
         
         1. Assurez-vous d'avoir les dépendances appropriées pour exécuter le code.
         2. Importez le fichier `ControllerSimpleGame.java` dans votre projet.
         3. Créez une instance de `ControllerSimpleGame` pour démarrer le jeu.
         4. Interagissez avec les commandes de jeu affichées dans la vue `ViewCommand` pour jouer, mettre en pause, redémarrer ou ajuster la vitesse du jeu.
         
         ### Remarques
         
         - Ce fichier utilise des classes et des interfaces provenant d'autres fichiers du projet, notamment `AbstractController`, `SimpleGame`, `ViewSimpleGame`, `ViewCommand` et différentes classes de comportement.
         - Assurez-vous d'avoir les fichiers sources correspondants pour exécuter le code correctement.

   4.

   5.


4) # Réalisations à effectuer pour obtenir une première simulation de jeu

