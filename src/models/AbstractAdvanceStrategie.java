package models;

public abstract class AbstractAdvanceStrategie implements Strategie {
    protected PacmanGame game;
    
    AbstractAdvanceStrategie(PacmanGame game) {
        this.game = game;
    }

    protected PositionAgent getNewPositionAgent(Agent a, AgentAction act) {
        return new PositionAgent(a.pos.getX() + act.get_vx(), a.pos.getY() + act.get_vy(), act.get_direction());
    }

    protected PositionAgent getNewPositionAgent(PositionAgent pos, AgentAction act) {
        return new PositionAgent(pos.getX() + act.get_vx(), pos.getY() + act.get_vy(), act.get_direction());
    }
}
