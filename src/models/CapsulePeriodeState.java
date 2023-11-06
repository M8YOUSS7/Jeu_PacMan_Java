package models;

import java.util.ArrayList;

public class CapsulePeriodeState extends AbstractPacmanGameState {

    CapsulePeriodeState(PacmanGame g) {
        super(g);
        timer = 20;
    }

    CapsulePeriodeState(PacmanGame g, int t) {
        super(g);
        timer = t;
    }

    @Override
    public void checkDeaths(Agent a) {
        
        if((a instanceof Fantome && isPacmanPos(a.pos)) || a instanceof Pacman) {
            game.listeAgents.removeIf(agt -> agt instanceof Fantome && a.pos.equals(agt.pos));
        }
    }

    @Override
    public void setTimer(int t) {
        if (t>0) {
            System.out.println("Capsule Period State, time : " + timer + ".\n");
            timer = t;
        } else {
            game.setState(new NorlmalState(game));
        }
    }
}
