package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class ScaryStrategie extends AbstractAdvanceStrategie {
    ScaryStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction act;
        ArrayList <AgentAction> legalMoves = new ArrayList<AgentAction>();
        
        for(int i=0; i<4; i++) {
            act = new AgentAction(i);
            if(isLegalMove(a, act, m)) {
                legalMoves.add(act);
            }
        }

        legalMoves.removeAll(legalMoves.stream().filter(action -> (a instanceof Pacman && game.isFantomePos(getNewPositionAgent(a, action))) || (a instanceof Fantome && game.isPacmanPos(getNewPositionAgent(a, action)))).collect(Collectors.toList()));

        return (legalMoves.isEmpty()) ? new AgentAction(AgentAction.STOP) : legalMoves.get(new Random().nextInt(legalMoves.size()));
    }
}