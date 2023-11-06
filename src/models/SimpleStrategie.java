package models;

public class SimpleStrategie implements Strategie {

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        int i =0;
        AgentAction act = new AgentAction(i);
        
        while(!isLegalMove(a, act, m) && i<5) {
           i++;
           act = new AgentAction(i); 
        }
        return act;
    }
    
}
