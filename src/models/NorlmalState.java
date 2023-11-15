package models;

public class NorlmalState extends AbstractPacmanGameState {

    NorlmalState(PacmanGame g) {
        super(g);
    }

    @Override
    public void checkDeaths(Agent a) {
        if((a instanceof Pacman && isGhotPos(a.pos))) {
            a.setIsDead(true);
        } else if (a instanceof Fantome) {
            for(Agent agt : game.getListeAgents()) {
                if(agt instanceof Pacman && agt.getPos().equals(a.getPos())) {
                    agt.setIsDead(true);
                }
            }
        }
    }

    @Override
    public void setTimer(int t) {
        if(t>0) {
            game.setState(new CapsulePeriodeState(game, t));
        }
    }
}
