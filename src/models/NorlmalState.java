package models;

import java.util.ArrayList;

public class NorlmalState extends AbstractPacmanGameState {

    NorlmalState(PacmanGame g) {
        super(g);
    }

    @Override
    public void checkDeaths(Agent a) {
        if((a instanceof Pacman && isGhotPos(a.pos)) || a instanceof Fantome) {
            game.listeAgents.removeIf(agt -> agt instanceof Pacman && a.pos.equals(agt.pos));
        }   //if(a instanceof Fantome && isPacmanPos(a.pos))
    }

    @Override
    public void setTimer(int t) {
        if(t>0) {
            game.setState(new CapsulePeriodeState(game, t));
        }
    }
}
