package models;

import java.util.ArrayList;

public class FoodStrategie extends AbstractAdvanceStrategie {

    public FoodStrategie(PacmanGame game) {
        super(game);
    }

    public AgentAction getAction(Agent a, Maze m) {
        //l'idee est de calculer la position du plus proche nouriture et de le manger
        /***    Attention que pour les packmans !   ***/

        PositionAgent closerFood = getCloserFood(a.pos, m);
            return moveCloser(a.pos, closerFood);
    }
}
