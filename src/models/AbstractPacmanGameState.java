package models;

public abstract class AbstractPacmanGameState {
    PacmanGame game;
    protected int timer =0; 

    AbstractPacmanGameState(PacmanGame g) {
        game = g;
    }
    
    public abstract void setTimer(int t);

    public abstract void checkDeaths(Agent a);

    
    public Boolean isGhotPos(PositionAgent pa) {
        for(Agent f : game.listeAgents) {
            if(f instanceof Fantome && f.pos.equals(pa)) return true;
        }

        return false;
    }

    public Boolean isPacmanPos(PositionAgent pa) {
        for(Agent p : game.listeAgents) {
            if(p instanceof Pacman && p.pos.equals(pa)) return true;
        }

        return false;
    }
}
