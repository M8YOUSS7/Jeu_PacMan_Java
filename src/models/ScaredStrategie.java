package models;

import java.util.ArrayList;
import java.util.Optional;
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
        ArrayList <AgentAction> legalActions = new ArrayList<AgentAction>();

        IntStream.rangeClosed(1, 4).forEach(i -> {
            AgentAction act = new AgentAction(i);
            if(isLegalMove(a, act, m)) {
                legalActions.add(act);
            }
        });

        legalActions.removeAll(legalActions.stream().filter(action -> (a instanceof Pacman && game.isFantomePos(getNewPositionAgent(a, action))) || (a instanceof Fantome && game.isPacmanPos(getNewPositionAgent(a, action)))).collect(Collectors.toList()));
        PositionAgent save = a.getPos();

        AgentAction res = legalActions.stream().filter(action -> {
            a.setPos(getNewPositionAgent(a, action));
            return !IntStream.rangeClosed(1, 4).filter(i -> {
                AgentAction act = new AgentAction(i);
                return isLegalMove(a, act, m);
            }).findFirst().isEmpty();
        }).findAny().get();

        a.setPos(save);
        return (legalActions.isEmpty()) ? new AgentAction(AgentAction.STOP) : res;
    }
}
