import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public abstract class Game extends Observable implements Runnable {
    int turn;
    int maxTurn;
    boolean isRunning;
    Thread thread;
    long time;

    public Game(int mt) {
        maxTurn = mt;
        time = 1000;
    }

    public void init() {
        turn =0;
        isRunning =true;
        initializeGame();
        setChanged();
        notifyObservers();
    }

    public abstract void initializeGame();

    public void step() {
        if(gameContinue() && (turn < maxTurn)) {
            takeTurn();
            turn++;
            setChanged();
            notifyObservers();
        } else {
            isRunning =false;
            gameOver();
        }
    }

    public abstract void takeTurn();
    public abstract boolean gameContinue();
    public abstract void gameOver();

    public void pause() {
        isRunning =false;
    }

    public void run() {
        while(isRunning !=false) {
            step();
            try {
                Thread.sleep(time);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void lunch() {
        isRunning =true;
        thread = new Thread(this);
        thread.start();
    }
}
