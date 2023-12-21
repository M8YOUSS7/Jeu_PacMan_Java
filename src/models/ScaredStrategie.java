package models;

import java.util.ArrayList;

public class ScaredStrategie extends AbstractAdvanceStrategie {
    public ScaredStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction res;

        if(a instanceof Pacman) {
           //PositionAgent capsule = getCloserCapsule(a.getPos());
            //ArrayList<PositionAgent> path = findShortestPath(a.getPos(), capsule);
            //System.out.println("Capsule: " + capsule + " | Path: " + path);            
            //res = (!path.isEmpty()) ? new AgentAction(path.get(0).getDir()) : new AgentAction(AgentAction.STOP);
        } else {
        }
        
        res = new AgentAction(a.getPos().getDir());
        return res;
    }
}
