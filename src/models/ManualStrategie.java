package models;
import java.awt.event.KeyEvent;

public class ManualStrategie implements Strategie {
    public AgentAction action = new AgentAction(AgentAction.STOP);

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        return action;
    }

    public void setAction(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_UP:
                action = new AgentAction(AgentAction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                action = new AgentAction(AgentAction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                action = new AgentAction(AgentAction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                action = new AgentAction(AgentAction.EAST);
                break;
            default:
                action = new AgentAction(AgentAction.STOP);
                break;
        }
    }
}