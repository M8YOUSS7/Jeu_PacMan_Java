package models;

import java.util.ArrayList;

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
                if(isLegalMove(a, action, m)) {
                    return action;
                }

                action = new LinearStrategie().getAction(a, m);
                if(isLegalMove(a, action, m)) {
                    return action;
                }
            }

            action = new CaspuleStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m)) {
                return action;
            }

            action = new FoodStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m)) {
                return action;
            }

            action = new ScaredStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m)) {
                return action;
            }

            ArrayList<PositionAgent> corners = new ArrayList<>();
            corners.add(new PositionAgent(0, 0, 0));
            corners.add(new PositionAgent(0, m.getSizeY()-1, 0));
            corners.add(new PositionAgent(m.getSizeX()-1, 0, 0));
            corners.add(new PositionAgent(m.getSizeX()-1, m.getSizeY()-1, 0));
            corners.add(new PositionAgent(m.getSizeX()/2, m.getSizeY()/2, 0));

            for(PositionAgent corner : corners) {
                ArrayList<PositionAgent> path = findShortestPath(a.getPos(), corner, m);
                AgentAction action = moveCloser(a.pos, path.get(1));
                if(isLegalMove(a, action, m))
                    return action;
            }
            
            action = new LinearStrategie().getAction(a, m);
                return action;
    }
}
