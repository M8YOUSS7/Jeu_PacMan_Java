package models;

public class SimpleGame extends Game {
    public SimpleGame(int mt) {
        super(mt);
    }

    public void initializeGame() {

    }
    
    public void takeTurn() {
        System.out.println("Tour " + turn + " du jeu en cours.");
    }

    public boolean gameContinue() {
        return true;
    }
    
    public void gameOver() {
        System.out.println("Jeu terminer après " + turn + " tours.");

    }
}