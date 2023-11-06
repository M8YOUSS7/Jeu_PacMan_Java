package models;
import java.util.Random;

public class AleatStrategie implements Strategie {

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        int i =new Random().nextInt(5);
        AgentAction act = new AgentAction((a.getPos().getDir() == Maze.STOP) ? new Random().nextInt(4) : a.getPos().getDir());
        
        while(!isLegalMove(a, act, m)) {
           i = new Random().nextInt(5);
           act = new AgentAction(i); 
        }
        
        return act;
    }
}
