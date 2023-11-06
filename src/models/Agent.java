package models;

public abstract class Agent {
    PositionAgent pos;
    Strategie strategy;

    public Agent(PositionAgent p) {
        pos = p;
        strategy = new AleatStrategie();
    }

    public void setPos(int x, int y) {
        pos.setX(x);
        pos.setY(y);
    }

    public PositionAgent getPos() {
        return pos;
    }

    public abstract AgentAction play();

    public String toString() {
        return "["+ pos.getX() + ", "+ pos.getY() + "]";
    }

    public void setStrategy(Strategie s) {
        strategy =s;
    }

}
