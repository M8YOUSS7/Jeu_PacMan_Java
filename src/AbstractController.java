public abstract class AbstractController {
    Game game;

    AbstractController(Game g) {
        game = g;
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
