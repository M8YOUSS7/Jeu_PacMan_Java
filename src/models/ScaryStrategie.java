package models;

import java.util.ArrayList;

public class ScaryStrategie extends AbstractAdvanceStrategie {
    ScaryStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction res;

        if(a instanceof Pacman) {
            // chercher tous les fantomes et les attaquer
            PositionAgent capsule = getCloserCapsule(a.getPos(), m);
            ArrayList<PositionAgent> path = findShortestPath(a.getPos(), capsule, m);
            res = getActionFromPos(a.getPos(), path.get(1));
            return res;
        }
        
        // chercher tous les pacmans et les attaquer
        
        res = new AgentAction(a.getPos().getDir());
        return res;
    }
}