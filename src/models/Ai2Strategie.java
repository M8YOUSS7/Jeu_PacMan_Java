package models;

public class Ai2Strategie extends AbstractAdvanceStrategie {
    AgentAction action;

    public Ai2Strategie(PacmanGame game) {
        super(game);
        action = new AgentAction(AgentAction.STOP);
    }

    public AgentAction getAction(Agent a, Maze m) {
        /***    L'idee est de calculer la position du proche pacman et de se rapprocher de lui s'il y'en as au moins    ***/
        /***    Sinon chercher s'enfuir ***/

        if(game.state instanceof NorlmalState) {
            action = new ScaryStrategie(game).getAction(a, m);
            if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
                return action;
            }

            return new LinearStrategie().getAction(a, m);
        }
        
        action = new ScaredStrategie(game).getAction(a, m);
        if(isLegalMove(a, action, m) && action.get_direction() != AgentAction.STOP) {
            return action;
        }

        return new LinearStrategie().getAction(a, m);
    }
}
