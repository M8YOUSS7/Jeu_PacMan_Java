package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class PacmanGame extends Game {
    protected ArrayList<Agent> listeAgents;

    protected Maze labyrinthe;
    protected AbstractPacmanGameState state;

    private static PacmanGame uniqueInstance;

    private PacmanGame(int mt, Maze m) {
        super(mt);
        listeAgents = new ArrayList<Agent>();
        labyrinthe = m;
        state = new NorlmalState(this);
    }

    public static synchronized PacmanGame getIstance(int mt, Maze m) {
        if(uniqueInstance == null)
            uniqueInstance = new PacmanGame(mt, m);
        return uniqueInstance;
    }

    @Override
    public void initializeGame() {
        listeAgents.clear();
        for(PositionAgent pa : labyrinthe.getPacman_start()) {
            listeAgents.add(new Pacman(pa, new ScaredStrategie(this)));
        }

        for(PositionAgent pa : labyrinthe.getGhosts_start()) {
            listeAgents.add(new Fantome(pa, new LinearStrategie()));
        }
    }
    
    @Override
    public void takeTurn() {
        System.out.println("Tour " + turn + " du jeu en cours.\n");
            for(Agent e : listeAgents) {
                try {
                    AgentAction act = e.play(labyrinthe);
                    if(isLegalMove(e, act)) {
                        if (e instanceof Pacman) {
                            if(labyrinthe.isFood(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy())) {
                                labyrinthe.setFood(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy(), false);
                                ((Pacman) e).eatFood(1);
                            } else if(labyrinthe.isCapsule(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy())) {
                                labyrinthe.setCapsule(e.pos.getX() + act.get_vx(), e.pos.getY() + act.get_vy(), false);
                                state.setTimer(20);
                            }
                        }

                        moveAgent(e, act);
                        state.checkDeaths(e);
                        System.out.println(e + " --- ok\n");
                    } else {
                        System.out.println(e + " --- no\n");
                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        
        listeAgents.removeAll(listeAgents.stream().filter(Agent::isDead).collect(Collectors.toList()));
        state.setTimer(state.timer-1);
    }

    @Override
    public boolean gameContinue() {
        Boolean pac = false, gos =false;
        for(Agent a : listeAgents) {
            if(a instanceof Pacman) {
                pac = true; break;
            }
        }
        for(Agent a : listeAgents) {
            if(a instanceof Fantome) {
                gos = true; break;
            }
        }
        return pac && gos;
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
        a.pos.setDir(act.get_direction());
    }

    public void setState(AbstractPacmanGameState s) {
        state = s;
    }

    protected Strategie getOneStrategie() {
        int s = new Random().nextInt(3);
        if(s == 0) {
            return new AleatStrategie();
        } else if (s==1) {
            return new LinearStrategie();
        } else
            return new AleatStrategie();
    }

    public ArrayList<Agent> getListeAgents() {
        return listeAgents;
    }

    public boolean isPacmanPos(PositionAgent pa) {
        return listeAgents.stream().filter(agt -> agt instanceof Pacman).collect(Collectors.toList()).isEmpty();
    }

    public boolean isFantomePos(PositionAgent pa) {
        return listeAgents.stream().filter(agt -> agt instanceof Fantome).collect(Collectors.toList()).isEmpty();
    }
}
