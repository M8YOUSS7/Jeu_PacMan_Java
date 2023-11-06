package models;

public class Fantome extends Agent {

    public Fantome(PositionAgent p) {
        super(p);
    }

    public Fantome(PositionAgent p, Strategie s) {
        super(p, s); 
    }

    @Override
    public AgentAction play(Maze m) {
        return strategy.getAction(this, m);
    }
    
    public String toString() {
        return "Fantôme --- " + super.toString();
    }
}
