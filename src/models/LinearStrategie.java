package models;

import java.util.Random;

public class LinearStrategie implements Strategie {

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction act = new AgentAction((a.getPos().getDir()==4) ? new Random().nextInt(4) : a.getPos().getDir());

        while(!isLegalMove(a, act, m)) {
            act = new AgentAction(new Random().nextInt(5));
        }

        return act;
    }
    
}
