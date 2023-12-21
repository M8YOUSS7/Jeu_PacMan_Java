package models;

public class ScaredStrategie extends AbstractAdvanceStrategie {
    ScaredStrategie(PacmanGame game) {
        super(game);
    }

    @Override
    public AgentAction getAction(Agent a, Maze m) {
        AgentAction res;

        if(a instanceof Pacman) {
            PositionAgent capsule = getCloserCapsule(a.getPos());
            res = getVectorAction(a.pos, capsule);
            if(capsule != null && isLegalMove(a, res, m)) {
                return res;
            }

        } else {
            res = new AgentAction(a.getPos().getDir());
        }

        return res;
    }
}
