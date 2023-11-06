package models;

import java.util.ArrayList;

public class PacmanGame extends Game {
    protected ArrayList<Agent> listeAgents;
    protected Maze labyrinthe;
    public static int period =0;

    public PacmanGame(int mt, Maze m) {
        super(mt);
        listeAgents = new ArrayList<Agent>();
        labyrinthe = m;
    }

    @Override
    public void initializeGame() {
        for(PositionAgent pa : labyrinthe.getPacman_start()) {
            listeAgents.add(new Pacman(pa));
        }

        for(PositionAgent pa : labyrinthe.getGhosts_start()) {
            listeAgents.add(new Fantome(pa));
        }
    }

    @Override
    public void takeTurn() {
        for(Agent e : listeAgents) {
            try {
                AgentAction act = e.play();
                if(isLegalMove(e, act)) {   
                    if(labyrinthe.isFood(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy())) {
                        labyrinthe.setFood(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy(), false);
                    } else if(labyrinthe.isCapsule(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy())) {
                        labyrinthe.setCapsule(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy(), false);
                    }

                    moveAgent(e, act);
                    System.out.println("Tour " + turn + " du jeu en cours.\n" + e + "\n ok");
                } else {
                    System.out.println("Tour " + turn + " du jeu en cours.\n" + e + "\n no");
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }   
    }

    @Override
    public boolean gameContinue() {
        return turn < maxTurn;
    }

    @Override
    public void gameOver() {
        pause();
        System.out.println("Jeu terminer après " + turn + " tours.");
    }

    public Maze getLabyrinthe() {
        return labyrinthe;
    }
    
    public void setLabyrinthe(Maze m) {
        labyrinthe = m;
    }

    public Boolean isLegalMove(Agent a, AgentAction act) {
        return !(labyrinthe.isWall(a.pos.getX() + act.get_vx(), a.pos.getY() + act.get_vy()));
    }
    
    public void moveAgent(Agent a, AgentAction act) {
        a.setPos(a.pos.getX() + act.get_vx(), a.pos.getY() + act.get_vy());
    }

    Boolean checkPacmanDeath(Pacman p) {
        Boolean res = labyrinthe.isGhostPos(p.getPos());
            if(res) {
                labyrinthe.getPacman_start().remove(p.getPos());
                listeAgents.remove(p);
            }
        return res;
    }

    void checkCapsulePeriod() {
        if(period == 0) {
            
        }
    }
}
