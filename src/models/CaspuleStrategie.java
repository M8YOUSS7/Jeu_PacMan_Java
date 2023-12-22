package models;

import java.util.ArrayList;

public class CaspuleStrategie extends AbstractAdvanceStrategie {

    public CaspuleStrategie(PacmanGame game) {
        super(game);
    }
    
    public AgentAction getAction(Agent a, Maze m) {
        //l'idee est de calculer la position de la plus proche capsule et de se rapprocher de celle-ci

        PositionAgent closerCapsule = getCloserCapsule(a.pos, m);
        ArrayList<PositionAgent> path = findShortestPath(a.getPos(), closerCapsule, m);
            return moveCloser(a.pos, path.get(1));
    }
}
