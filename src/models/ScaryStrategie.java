package models;

import java.util.ArrayList;

public class ScaryStrategie extends AbstractAdvanceStrategie {
    ScaryStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        //l'idee est de calculer la position du plus proche enemy et de se rapprocher de lui
        // chercher un pacman et l'attaquer et iversement

        PositionAgent closerEnemy = getCloserEnemy(a, m);
        ArrayList<PositionAgent> path = findShortestPath(a.getPos(), closerEnemy, m);
            return moveCloser(a.pos, path.get(1));
    }
}