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

1) Création de l’architecture d’un jeu séquentiel
   - # Game.java

      Ce fichier contient la classe abstraite `Game` qui représente un jeu générique. Cette classe implémente l'interface `Runnable` et étend la classe `Observable`.
      
      ## Fonctionnalités
      
      - `turn` : un entier représentant le tour actuel du jeu.
      - `maxTurn` : un entier représentant le nombre maximum de tours du jeu.
      - `isRunning` : un booléen indiquant si le jeu est en cours d'exécution.
      - `thread` : un objet `Thread` utilisé pour exécuter le jeu en tant que processus séparé.
      - `time` : un long représentant le temps de pause entre chaque tour du jeu.
      
      ## Méthodes
      
      - `init()` : initialise le jeu en appelant la méthode abstraite `initializeGame()`, met à jour les observateurs et les notifie.
      - `step()` : effectue une étape du jeu en appelant la méthode abstraite `takeTurn()`, met à jour les observateurs et les notifie. Si le jeu est terminé ou si le nombre maximum de tours est atteint, appelle la méthode `gameOver()`.
      - `pause()` : met le jeu en pause en réinitialisant le booléen `isRunning` et en arrêtant le thread.
      - `run()` : exécute le jeu en boucle tant que `isRunning` est vrai. Appelle la méthode `step()` et met le thread en pause pendant le temps spécifié.
      - `lunch()` : démarre le jeu en initialisant le booléen `isRunning` à vrai et en créant un nouveau thread pour exécuter le jeu.
      
      ## Utilisation
      
      Pour utiliser la classe `Game`, vous devez créer une classe qui étend `Game` et implémenter les méthodes abstraites `initializeGame()`, `takeTurn()`, `gameContinue()` et `gameOver()`. Vous pouvez également ajouter des observateurs pour suivre l'état du jeu.

   - # SimpleGame.java
      
      Ce fichier contient la classe SimpleGame, qui est une sous-classe de la classe Game. La classe SimpleGame représente un jeu simple avec des tours et une condition de fin.
      
      ## Utilisation
      
      Pour utiliser la classe SimpleGame, vous devez l'instancier en spécifiant un entier en paramètre lors de la création de l'objet. Cet entier représente le nombre maximum de tours du jeu.

3) Création des premiers éléments de l’interface graphique
4) Création du Modèle-Vue-Contrôleur
5) Réalisations à effectuer pour obtenir une première simulation de jeu
