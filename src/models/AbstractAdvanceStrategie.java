package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public abstract class AbstractAdvanceStrategie implements Strategie {
    protected PacmanGame game;
    
    protected class Node {
        PositionAgent position;
        int distance;
        Node predecessor;
    }

    AbstractAdvanceStrategie(PacmanGame game) {
        this.game = game;
    }

    public ArrayList<PositionAgent> findShortestPath(PositionAgent start, PositionAgent end, Maze maze) {
        Map<PositionAgent, Node> nodes = new HashMap<>();
        Node endNode = new Node();
        endNode.position = end;
        endNode.distance = 0;

        nodes.put(end, endNode);

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        queue.add(endNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (PositionAgent neighbor : getNeighbors(currentNode.position, maze)) {
                if(!nodes.entrySet().stream().anyMatch(e -> e.getKey().equals(neighbor))) {
                    Node neighborNode = nodes.getOrDefault(neighbor, new Node());
                    neighborNode.position = neighbor;
                    neighborNode.distance = currentNode.distance +1;
                    neighborNode.predecessor = currentNode;
                    nodes.put(neighbor, neighborNode);
                    queue.add(neighborNode);
                }
            }
        }

        ArrayList<PositionAgent> path = new ArrayList<>();
        Node currentNode = nodes.entrySet().stream().filter(e -> e.getKey().equals(start)).min((n1, n2) -> n1.getValue().distance - n2.getValue().distance).get().getValue();

        while (currentNode != null) {
            path.add(currentNode.position);
            currentNode = currentNode.predecessor;
        }
            return path;
    }

    private ArrayList<PositionAgent> getNeighbors(PositionAgent position, Maze maze) {
        // Implémentez cette méthode pour retourner les voisins de la position donnée
        ArrayList<PositionAgent> res = new ArrayList<>();
        
        if(position.getY()+1 < maze.getSizeY())
            res.add(new PositionAgent(position.getX(), position.getY()+1, AgentAction.NORTH));
        if(position.getY()-1 >= 0)
            res.add(new PositionAgent(position.getX(), position.getY()-1, AgentAction.SOUTH));
        if(position.getX()-1 >= 0)
            res.add(new PositionAgent(position.getX()-1, position.getY(), AgentAction.EAST));
        if(position.getX()+1 < maze.getSizeX())
            res.add(new PositionAgent(position.getX()+1, position.getY(), AgentAction.WEST));

        return res.stream().filter(pos -> !maze.isWall(pos.getX(), pos.getY())).collect(Collectors.toCollection(ArrayList::new));
    }

    

    public PositionAgent getCloserCapsule(PositionAgent pos, Maze maze) {
        ArrayList<PositionAgent> capsules = new ArrayList<>();

        for(int i = 0; i < game.labyrinthe.getSizeX(); i++) {
            for(int j = 0; j < game.labyrinthe.getSizeY(); j++) {
                if(game.labyrinthe.isCapsule(i, j)) {
                    capsules.add(new PositionAgent(i, j, 0));
                }
            }
        }

        return capsules.stream().min((o1, o2) -> findShortestPath(pos, o1, maze).size()-findShortestPath(pos, o2, maze).size()).get();
    }

    public PositionAgent getCloserFood(PositionAgent pos, Maze maze) {
        ArrayList<PositionAgent> capsules = new ArrayList<>();

        for(int i = 0; i < game.labyrinthe.getSizeX(); i++) {
            for(int j = 0; j < game.labyrinthe.getSizeY(); j++) {
                if(game.labyrinthe.isFood(i, j)) {
                    capsules.add(new PositionAgent(i, j, 0));
                }
            }
        }

        return capsules.stream().min((o1, o2) -> findShortestPath(pos, o1, maze).size()-findShortestPath(pos, o2, maze).size()).get();
    }

    public PositionAgent getCloserEnemy(Pacman p, Maze maze) {
        ArrayList<PositionAgent> enemies = new ArrayList<>();

        for(Agent a : game.listeAgents) {
            if(a instanceof Fantome) {
                enemies.add(a.pos);
            }
        }

        return enemies.stream().min((o1, o2) -> findShortestPath(p.pos, o1, maze).size()-findShortestPath(p.pos, o2, maze).size()).get();
    }

    public PositionAgent getCloserPacman(Fantome f, Maze maze) {
        ArrayList<PositionAgent> pacmans = new ArrayList<>();

        for(Agent a : game.listeAgents) {
            if(a instanceof Pacman) {
                pacmans.add(a.pos);
            }
        }

        return pacmans.stream().min((o1, o2) -> findShortestPath(f.pos, o1, maze).size()-findShortestPath(f.pos, o2, maze).size()).get();
    }

    protected AgentAction getActionFromPos(PositionAgent p, PositionAgent p_) {
        if(p.getX() == p_.getX()) {
            if(p.getY() == p_.getY()+1) {
                return new AgentAction(AgentAction.NORTH);
            } else if(p.getY() == p_.getY()-1) {
                return new AgentAction(AgentAction.SOUTH);
            }
        } else if(p.getY() == p_.getY()) {
            if(p.getX() == p_.getX()+1) {
                return new AgentAction(AgentAction.WEST);
            } else if(p.getX() == p_.getX()-1) {
                return new AgentAction(AgentAction.EAST);
            }
        }
        return new AgentAction(AgentAction.STOP);
    }
}