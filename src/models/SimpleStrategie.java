package models;

import java.util.ArrayList;
import java.util.Random;

public class SimpleStrategie implements Strategie {

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction act;

        ArrayList <AgentAction> res = new ArrayList<AgentAction>();
        for (int i=0; i<5; i++) {
            act = new AgentAction(i);
            if(isLegalMove(a, act, m)) {
                res.add(act);
            }
        }
        return res.get(new Random().nextInt(res.size()));
    }
    
}
