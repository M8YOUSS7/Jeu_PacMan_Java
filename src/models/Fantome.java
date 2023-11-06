package models;

import java.util.Random;

public class Fantome extends Agent {

    public Fantome(PositionAgent p) {
        super(p);
    }

    @Override
    public AgentAction play() {
        return new AgentAction(new Random().nextInt(5));
    }
    
    public String toString() {
        return "Fantôme --- " + super.toString();
    }
}
