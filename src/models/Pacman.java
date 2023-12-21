package models;

public class Pacman extends Agent {
    int food =0;

    public Pacman(PositionAgent p) {
        super(p);
    }

    public Pacman(PositionAgent p, Strategie s) {
        super(p, s); 
    }

    @Override
    public AgentAction play(Maze m) {
        return strategy.getAction(this, m);
    }

    public String toString() {
        return "Pacman --- " + super.toString() + ", food : " + food;
    }

    public void eatFood(int f) {
        food += f;
    }

    public ManualStrategie getManualStrategie() {
        return (strategy instanceof ManualStrategie) ? (ManualStrategie) strategy : null;
    }
}
