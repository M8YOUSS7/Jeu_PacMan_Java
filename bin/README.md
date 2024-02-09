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

   - #Tests
     Reuissis.


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
         
         #### Utilisation
         
         Pour utiliser la classe `AbstractController`, vous devez l'étendre et implémenter les méthodes nécessaires pour contrôler le jeu.
         
         #### Méthodes
         
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

   4. # Tests
      Reuissis.
   
4) # Réalisations à effectuer pour obtenir une première simulation de jeu
   ## ControleurPacmanGame.java
      Ce fichier contient la classe ControleurPacmanGame, qui est un contrôleur pour le jeu Pacman. Il gère les interactions entre le modèle PacmanGame et les vues ViewPacmanGame et ViewCommand.

      ### Fonctionnalités
      - Initialise le jeu Pacman avec un labyrinthe par défaut.
      - Permet de charger un labyrinthe à partir d'un fichier.
      - Gère les actions des boutons de la vue de commande (restart, pause, run, step).
      ### Utilisation
      1. Instanciez un objet ControleurPacmanGame en spécifiant le nombre maximum de tours (mxt) pour le jeu.
      2. Le jeu Pacman sera initialisé avec un labyrinthe par défaut.
      3. Utilisez les boutons de la vue de commande pour interagir avec le jeu:
         - Le bouton restart permet de redémarrer le jeu avec le même labyrinthe.
         - Le bouton pause permet de mettre le jeu en pause.
         - Le bouton run permet de reprendre le jeu après une pause.
         - Le bouton step permet d'avancer d'un tour à la fois dans le jeu.
      ### Personnalisation du labyrinthe
      Pour charger un labyrinthe à partir d'un fichier, utilisez la méthode **initializeMazeDeFichier()**. Cette méthode ouvrira une boîte de dialogue pour sélectionner un fichier de labyrinthe. Une fois le fichier sélectionné, le labyrinthe sera chargé et le jeu sera initialisé avec ce nouveau labyrinthe.

   ## ViewPacmanGame.java
      Ce fichier contient la classe `ViewPacmanGame` qui est responsable de l'affichage graphique du jeu Pacman. Il s'agit d'une vue du modèle de jeu Pacman, qui est chargée de mettre à jour l'interface utilisateur en fonction des changements survenus dans le modèle.

      ### Fonctionnalités

      - Affichage du labyrinthe Pacman.
      - Gestion du menu avec des options pour changer de labyrinthe et quitter l'application.
      - Mise à jour de l'interface utilisateur en fonction des positions des agents (Pacmans et Fantômes) dans le jeu.

      ### Utilisation

      Pour utiliser cette classe, vous devez l'instancier en lui passant une instance de la classe `Game` en tant que paramètre du constructeur. Assurez-vous d'avoir les dépendances nécessaires pour exécuter l'application.

   ## PacmanGame.java
      Ce fichier contient la classe PacmanGame qui représente le jeu Pacman. Il étend la classe Game et implémente les fonctionnalités spécifiques au jeu Pacman.

      ### Fonctionnalités
         - Gestion des agents : la classe PacmanGame maintient une liste d'agents (pacman et fantômes) et gère leur initialisation, leurs mouvements et leurs actions.
         - Initialisation du jeu : la méthode initializeGame() initialise les agents et les place sur le labyrinthe.
         - Déroulement du jeu : la méthode takeTurn() permet de jouer un tour du jeu, en faisant jouer chaque agent à tour de rôle.
         - Conditions de fin de jeu : la méthode gameContinue() vérifie si le jeu doit continuer en fonction de la présence de pacmans et de fantômes.
         - Fin de jeu : la méthode gameOver() est appelée lorsque le jeu est terminé et affiche un message de fin de jeu.
      
      ### Utilisation
         1. Pour utiliser la classe PacmanGame, vous pouvez créer une instance en appelant la méthode statique getInstance(int mt, Maze m). Cette méthode garantit qu'il n'y aura qu'une seule instance de PacmanGame dans l'application.

         2. Une fois l'instance créée, vous pouvez appeler les méthodes initializeGame(), takeTurn(), gameContinue() et gameOver() pour gérer le déroulement du jeu.

      ### Dépendances
         #### Ce fichier dépend des classes suivantes :

         + Game : une classe abstraite représentant un jeu générique.
         + Agent : une classe abstraite représentant un agent dans le jeu.
         + Maze : une classe représentant le labyrinthe du jeu Pacman.
         + AbstractPacmanGameState : une classe abstraite représentant l'état du jeu Pacman.
         + PositionAgent : une classe représentant la position d'un agent dans le labyrinthe.
         + Pacman : une classe représentant le personnage Pacman.
         + Fantome : une classe représentant les fantômes du jeu.
         
         Assurez-vous d'inclure ces classes dans votre projet pour pouvoir utiliser la classe PacmanGame correctement.

   ## AbstractPacmanGameState.java

      Ce fichier contient la définition de la classe `AbstractPacmanGameState` dans le package `models`. Cette classe est une classe abstraite qui représente l'état du jeu Pacman(CapsulePeriode, NormarleState, ).

      ### Fonctionnalités

      - La classe contient une référence vers l'objet `PacmanGame` pour accéder aux informations du jeu.
      - Elle possède une variable `timer` pour gérer le temps.
      - Elle définit les méthodes abstraites `setTimer` et `checkDeaths` qui doivent être implémentées par les classes dérivées.
      - Elle fournit les méthodes `isGhostPos` et `isPacmanPos` pour vérifier si une position donnée correspond à celle d'un fantôme ou d'un Pacman respectivement.

      ### Utilisation

      Pour utiliser cette classe, vous devez créer une classe dérivée qui implémente les méthodes abstraites `setTimer` et `checkDeaths`. Vous pouvez également utiliser les méthodes `isGhostPos` et `isPacmanPos` pour effectuer des vérifications sur les positions des agents dans le jeu.

   ## Comportement.java
      La classe `Comportement` est une classe abstraite qui fournit une base pour implémenter des comportements spécifiques pour les boutons de la `ViewCommand` du jeu PacMan.

      ### Méthodes

      - `ajuste()`: Méthode abstraite qui doit être implémentée par les classes dérivées pour ajuster le comportement.

      ### Utilisation

      Pour utiliser cette classe, vous devez créer une classe dérivée de `Comportement` et implémenter la méthode `ajuste()` en fonction des besoins spécifiques.

   ## PanelVirtualArrows

      Le fichier `PanelVirtualArrows.java` est une classe de vue qui représente un panneau contenant des boutons avec des flèches virtuelles et elle écoute les flèches clavier. Ce panneau est utilisé dans PacManGame et gère le deplacer du pacman au sein du jeu.

      ### Fonctionnalités

      - Affichage de boutons avec des flèches virtuelles (haut, bas, gauche, droite, arrêt).
      - Utilisation d'icônes pour représenter les flèches.
      - Disposition en grille des boutons.

      ### Utilisation

      1. Importez la classe `PanelVirtualArrows` dans votre projet.
      2. Créez une instance de `PanelVirtualArrows`.
      3. Ajoutez le panneau à votre interface graphique.


   ## Strategie.java
      Ce fichier contient l'interface `Strategie` qui définit le contrat pour les stratégies utilisées par l'agent PacMan dans le jeu Maze.

      ### Méthodes

      - `getAction(Agent a, Maze m)`: Cette méthode retourne l'action que l'agent doit effectuer en fonction des informations données par l'agent `a` et le labyrinthe `m`. Elle doit être implémentée par les classes qui implémentent cette interface.

      - `isLegalMove(Agent a, AgentAction act, Maze labyrinthe)`: Cette méthode permet de vérifier si un mouvement donné est légal pour l'agent `a` dans le labyrinthe `labyrinthe`. Par défaut, elle vérifie si la position suite au mouvement n'est pas un mur. Cette méthode peut être surchargée si besoin.

      ### Utilisation

      Pour utiliser cette interface, vous pouvez créer des classes qui implémentent cette interface et définir vos propres stratégies pour l'agent PacMan. Vous pouvez également surcharger la méthode `isLegalMove` si vous souhaitez une logique différente pour vérifier la légalité des mouvements.

      N'oubliez pas d'importer la classe `Agent` et `Maze` pour utiliser les paramètres de la méthode `getAction`.

      Exemple d'utilisation cf LinearStrategie.java

   ## AbstractAdvanceStrategie

      Ce fichier contient la classe `AbstractAdvanceStrategie` qui est une classe abstraite implémentant l'interface `Strategie`. Cette classe fournit des méthodes pour la gestion avancée des stratégies dans le jeu Pacman.

      ### Fonctionnalités

      - `findAllPath(PositionAgent start, PositionAgent end, Maze maze)`: Cette méthode permet de trouver tous les chemins possibles entre deux positions dans un labyrinthe donné.
      - `findShortestPath(PositionAgent start, PositionAgent end, Maze maze)`: Cette méthode permet de trouver le chemin le plus court entre deux positions dans un labyrinthe donné.
      - `getNeighbors(PositionAgent position, Maze maze)`: Cette méthode retourne les voisins d'une position donnée dans un labyrinthe donné.
      - `getCloserCapsule(PositionAgent pos, Maze maze)`: Cette méthode retourne la capsule la plus proche d'une position donnée dans un labyrinthe donné.
      - `getCloserEnemy(PositionAgent pos, Maze maze)`: Cette méthode retourne la l'enemie le plus proche d'une position donnée dans un labyrinthe donné.
      - `getCloserFood(PositionAgent pos, Maze maze)`: Cette méthode retourne la l'enemie le plus proche d'une position donnée dans un labyrinthe donné.

      ### Utilisation

      Pour utiliser la classe `AbstractAdvanceStrategie`, vous devez l'étendre et implémenter les méthodes abstraites de l'interface `Strategie`.


   ## ScaryStrategie

      Ce fichier contient la classe `ScaryStrategie` qui représente une stratégie effrayante pour le jeu PacMan. Cette classe hérite de la classe `AbstractAdvanceStrategie` et implémente la méthode `getAction` pour déterminer l'action à effectuer par un agent.

      ### Fonctionnalités

      - Calcul de la position de l'ennemi le plus proche et déplacement vers lui.
      - Recherche d'un Pacman à attaquer et inversement.

      ### Utilisation

      1. Assurez-vous d'avoir importé la classe `ScaryStrategie` dans votre projet.
      2. Créez une instance de `ScaryStrategie` en lui passant une instance de `PacmanGame` en paramètre.
      3. Appelez la méthode `getAction` en lui passant un agent et un labyrinthe en paramètres pour obtenir l'action à effectuer.

   # Ai1Strategie.java

      Ce fichier contient la classe `Ai1Strategie`, qui est une implémentation d'une stratégie pour le jeu Pac-Man. Cette classe hérite de la classe `AbstractAdvanceStrategie` et définit une série d'actions à effectuer par l'agent contrôlé par l'IA.

      ### Fonctionnalités

      La classe `Ai1Strategie` implémente la méthode `getAction` qui retourne l'action à effectuer par l'agent en fonction de l'état actuel du jeu et de la disposition des éléments dans le labyrinthe.

      Les fonctionnalités principales de cette classe sont les suivantes :

      - Calcul de la position de la capsule la plus proche et déplacement vers celle-ci tout en évitant les fantômes.
      - Recherche de la nourriture la plus proche et consommation de celle-ci tout en évitant les fantômes.
      - Déplacement vers un coin ou le centre de la carte le plus proche tout en évitant les fantômes.

      ### Utilisation

      Pour utiliser la classe `Ai1Strategie`, vous devez l'instancier en lui passant une instance de la classe `PacmanGame` dans le constructeur. Ensuite, vous pouvez appeler la méthode `getAction` en lui passant l'agent contrôlé par l'IA et le labyrinthe actuel en tant que paramètres.

   # Ai2Strategie.java

      Ce fichier contient la classe Ai2Strategie, qui est une classe de stratégie avancée pour le jeu Pac-Man.

      ### Description

      La classe Ai2Strategie étend la classe AbstractAdvanceStrategie et implémente la méthode getAction. L'objectif de cette stratégie est de calculer la position du Pac-Man le plus proche et de s'en rapprocher s'il y en a au moins un. Sinon, la stratégie cherchera à s'enfuir.

      ### Fonctionnalités

      - La classe Ai2Strategie hérite de la classe AbstractAdvanceStrategie et utilise les méthodes et fonctionnalités de cette classe.
      - La méthode getAction calcule la position du Pac-Man le plus proche et retourne l'action à effectuer pour s'en rapprocher ou s'enfuir.
      - La classe utilise d'autres classes de stratégie telles que ScaryStrategie, ScaredStrategie et LinearStrategie pour prendre des décisions en fonction de l'état du jeu.

      ### Utilisation

      Pour utiliser la classe Ai2Strategie, vous pouvez l'instancier dans votre code et appeler la méthode getAction en lui passant l'agent et le labyrinthe en paramètres.
