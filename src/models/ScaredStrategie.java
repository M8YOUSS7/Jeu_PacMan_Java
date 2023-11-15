package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.w3c.dom.ranges.Range;

public class ScaredStrategie extends AbstractAdvanceStrategie {
    ScaredStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        ArrayList <AgentAction> legalMoves = new ArrayList<AgentAction>();

        IntStream.rangeClosed(1, 4).forEach(i -> {
            AgentAction act = new AgentAction(i);
            if(isLegalMove(a, act, m)) {
                legalMoves.add(act);
            }
        });

        legalMoves.removeAll(legalMoves.stream().filter(action -> (a instanceof Pacman && game.isFantomePos(getNewPositionAgent(a, action))) || (a instanceof Fantome && game.isPacmanPos(getNewPositionAgent(a, action)))).collect(Collectors.toList()));

        return (legalMoves.isEmpty()) ? new AgentAction(AgentAction.STOP) : legalMoves.get(new Random().nextInt(legalMoves.size()));
    }
}
