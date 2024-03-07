package controllers;
import models.Game;

public abstract class AbstractController {
    protected Game game;

    protected AbstractController(Game g) {
        game = g;
        game.init();
    }
    
    public void restart() {
        game.pause();
        game.init();
    }

    public void step() {
        game.step();
    }

    public void play()  {
        game.lunch();
    }

    public void pause () {
        game.pause();
    }

    public void setSpeed(double speed) {
        game.time = (long)speed;
    }
}
