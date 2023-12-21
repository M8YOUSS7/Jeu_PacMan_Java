package models;

import java.util.ArrayList;

public class ScaredStrategie extends AbstractAdvanceStrategie {
    public ScaredStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction res;

        //l'idee est de calculer la position du plus proche enemy et de s'enoigner de lui

        if(a instanceof Pacman) {
            // chercher un chemin sur lequel il n'y a pas de fantome
            PositionAgent capsule = getCloserCapsule(a.getPos(), m);
            ArrayList<PositionAgent> path = findShortestPath(a.getPos(), capsule, m);
            res = getActionFromPos(a.getPos(), path.get(1));
            return res;
        }
        
        // cherche un chemin sur lequel il n'y a pas de pacman
        
        res = new AgentAction(a.getPos().getDir());
        return res;
    }
}
