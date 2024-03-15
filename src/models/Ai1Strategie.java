package models;

import java.util.ArrayList;
import java.util.Random;

public class Ai1Strategie extends AbstractAdvanceStrategie {
        AgentAction action;

        public Ai1Strategie(PacmanGame game) {
            super(game);
            action = new AgentAction(AgentAction.STOP);
        }
        
        public AgentAction getAction(Agent a, Maze m) {
            /***    L'idee est de calculer la position de la plus proche capsule et de se rapprocher de celle-ci s'il y'en as au moins une toute en evitant les fantomes    ***/
            /***    Sinon chercher la nouriture la plus proche et la manger s'il y'en as au moins une toute en evitant les fantomes ***/
            /***    Sinon chercher se diriger vers un coin ou le centre de la map le plus proche toute en evitant les fantomes ***/

            if(game.state instanceof CapsulePeriodeState) {
                action = new ScaryStrategie(game).getAction(a, m);
                if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
                    return action;
                }
                    return new LinearStrategie().getAction(a, m);
            }

            action = new CaspuleStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
                return action;
            }

            action = new FoodStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
                return action;
            }

            action = new ScaredStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
                return action;
            }

            // ArrayList<PositionAgent> corners = new ArrayList<>();
            // corners.add(new PositionAgent(0, 0, 0));
            // corners.add(new PositionAgent(0, m.getSizeY()-1, 0));
            // corners.add(new PositionAgent(m.getSizeX()-1, 0, 0));
            // corners.add(new PositionAgent(m.getSizeX()-1, m.getSizeY()-1, 0));

            // if(a.pos.equals(new PositionAgent(m.getSizeX()/2, m.getSizeY()/2, 0))) {
            //     ArrayList<AgentAction> lAgentActions = new ArrayList<>();

            //     for(PositionAgent corner : corners) {
            //         ArrayList<PositionAgent> path = findShortestPath(a.getPos(), corner, m);
            //         AgentAction action = moveCloser(a.pos, path.get(1));
            //         if(isLegalMove(a, action, m))
            //             lAgentActions.add(action);
            //     }

            //     if(lAgentActions.size() > 0)
            //         return lAgentActions.get(new Random().nextInt(lAgentActions.size()));
            // } else if(corners.contains(a.pos)) {
            //     return moveCloser(a.pos, new PositionAgent(m.getSizeX()/2, m.getSizeY()/2, 0));
            // } else {
            //     ArrayList<AgentAction> lAgentActions = new ArrayList<>();

            //     ArrayList<PositionAgent> path = findShortestPath(a.getPos(), new PositionAgent(m.getSizeX()/2, m.getSizeY()/2, 0), m);
            //     AgentAction action = moveCloser(a.pos, path.get(1));
            //     if(isLegalMove(a, action, m))
            //         lAgentActions.add(action);

            //     for(PositionAgent corner : corners) {
            //         path = findShortestPath(a.getPos(), corner, m);
            //         action = moveCloser(a.pos, path.get(1));
            //         if(isLegalMove(a, action, m))
            //             lAgentActions.add(action);
            //     }

            //     if(lAgentActions.size() > 0)
            //         return lAgentActions.get(new Random().nextInt(lAgentActions.size()));
            // }
                
            
            return new LinearStrategie().getAction(a, m);
    }
}
