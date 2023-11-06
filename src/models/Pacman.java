package models;

import java.util.Random;

public class Pacman extends Agent {

    public Pacman(PositionAgent p) {
        super(p);
    }

    @Override
    public AgentAction play() {
        return new AgentAction(new Random().nextInt(5));
    }

    public String toString() {
        return "Pacman --- " + super.toString();
    }
}
