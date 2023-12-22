package models;

import java.util.ArrayList;

public class ScaredStrategie extends AbstractAdvanceStrategie {
    public ScaredStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        //l'idee est de calculer la position du plus proche enemy et de s'enoigner de lui
        // cherche un chemin sur lequel il n'y a pas de pacman et inversement

        PositionAgent closerEnemy = getCloserEnemy(a, m);
        if(closerEnemy != null) {
            ArrayList<PositionAgent> path = findShortestPath(a.getPos(), closerEnemy, m);
            return moveAway(a.pos, path.get(1));
        }

        return new AgentAction(AgentAction.STOP);
    }
}
