package models;

public interface Strategie {
    public AgentAction getAction(Agent a, Maze m);
    public default Boolean isLegalMove(Agent a, AgentAction act, Maze labyrinthe) {
        return !(labyrinthe.isWall(a.pos.getX() + act.get_vx(), a.pos.getY() + act.get_vy()));
    }
}
