package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        IntStream.rangeClosed(0, 3).forEach(i -> {
            AgentAction act = new AgentAction(i);
            if(isLegalMove(a, act, m)) {
                legalActions.add(act);
            }
        });

        legalActions.removeAll(legalActions.stream().filter(action -> (a instanceof Pacman && game.isFantomePos(getNewPositionAgent(a, action))) || (a instanceof Fantome && game.isPacmanPos(getNewPositionAgent(a, action)))).collect(Collectors.toList()));
        PositionAgent save = a.getPos();

        AgentAction res = new AgentAction(a.getPos().getDir());

        System.out.println("Facing Action --- ");
        System.out.println(res);

        if(!legalActions.isEmpty()) {
            if(legalActions.contains(res)) {
                a.setPos(getNewPositionAgent(a, res));
                if(IntStream.rangeClosed(0, 3).filter(i -> {
                    AgentAction act = new AgentAction(i);
                    return isLegalMove(a, act, m);
                }).toArray().length > 0) {
                    a.setPos(save);
                    return res;
                };
            } else {
                a.setPos(save);
                final AgentAction test = res;
                final List<AgentAction> legalActions_ = legalActions.stream().filter(action -> !action.equals(test)).collect(Collectors.toList());
                
                System.err.println("legalActionList");
                legalActions_.forEach(action -> System.out.println(action.toString()));
                
                //legalActions = new ArrayList<AgentAction>(legalActions_);
            }
        }

        AgentAction res_ = legalActions.stream().filter(action -> {
            a.setPos(getNewPositionAgent(a, action));
            return !IntStream.rangeClosed(0, 3).filter(i -> {
                AgentAction act = new AgentAction(i);
                return isLegalMove(a, act, m);
            }).findFirst().isEmpty();
        }).max(Comparator.comparing(AgentAction::getSumOffVectors)).get();

        a.setPos(save);
        
        System.out.println("legalAction");
        legalActions.forEach(action -> System.out.println(action.toString()));
        
        System.out.println("res");
        System.out.println(res_.toString());

        return (legalActions.isEmpty()) ? new AgentAction(AgentAction.STOP) : res_;
    }
}
