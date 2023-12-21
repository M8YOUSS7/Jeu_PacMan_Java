package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class AbstractAdvanceStrategie implements Strategie {
    protected PacmanGame game;
    private Map<PositionAgent, Node> nodes = new HashMap<>();
    
    protected class Node {
        PositionAgent position;
        int distance;
        Node predecessor;
    }

    AbstractAdvanceStrategie(PacmanGame game) {
        this.game = game;
    }

    protected PositionAgent getNewPositionAgent(Agent a, AgentAction act) {
        return new PositionAgent(a.pos.getX() + act.get_vx(), a.pos.getY() + act.get_vy(), act.get_direction());
    }

    protected PositionAgent getNewPositionAgent(PositionAgent pos, AgentAction act) {
        return new PositionAgent(pos.getX() + act.get_vx(), pos.getY() + act.get_vy(), act.get_direction());
    }

    public ArrayList<PositionAgent> findShortestPath(PositionAgent start, PositionAgent end) {
        Node startNode = new Node();
        startNode.position = start;
        startNode.distance = 0;

        nodes.put(start, startNode);

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for (PositionAgent neighbor : getNeighbors(currentNode.position)) {
                Node neighborNode = nodes.getOrDefault(neighbor, new Node());
                neighborNode.position = neighbor;

                int newDistance = currentNode.distance + getDistance(currentNode.position, neighbor);

                if (newDistance < neighborNode.distance) {
                    neighborNode.distance = newDistance;
                    neighborNode.predecessor = currentNode;
                    nodes.put(neighbor, neighborNode);
                    queue.add(neighborNode);
                }
            }
        }

        ArrayList<PositionAgent> path = new ArrayList<>();
        Node currentNode = nodes.get(end);

        while (currentNode != null) {
            path.add(currentNode.position);
            currentNode = currentNode.predecessor;
        }

        Collections.reverse(path);
        return path;
    }

    private ArrayList<PositionAgent> getNeighbors(PositionAgent position) {
        // Implémentez cette méthode pour retourner les voisins de la position donnée
        ArrayList<PositionAgent> res = new ArrayList<>();
        
        res.add(new PositionAgent(position.getX(), position.getY()+1, AgentAction.NORTH));
        res.add(new PositionAgent(position.getX(), position.getY()-1, AgentAction.SOUTH));
        res.add(new PositionAgent(position.getX()-1, position.getY(), AgentAction.EAST));
        res.add(new PositionAgent(position.getX()+1, position.getY(), AgentAction.WEST));

        return res;
    }

    private int getDistance(PositionAgent a, PositionAgent b) {
        return (int)Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    public PositionAgent getCloserCapsule(PositionAgent pos) {
        ArrayList<PositionAgent> capsules = new ArrayList<>();

        for(int i = 0; i < game.labyrinthe.getSizeX(); i++) {
            for(int j = 0; j < game.labyrinthe.getSizeY(); j++) {
                if(game.labyrinthe.isCapsule(i, j)) {
                    capsules.add(new PositionAgent(i, j, 0));
                }
            }
        }

        return capsules.stream().min((o1, o2) -> {
            return findShortestPath(pos, o1).size() - findShortestPath(pos, o2).size();
        }).get();
    }

    public PositionAgent getCloserFood(PositionAgent pos) {
        ArrayList<PositionAgent> capsules = new ArrayList<>();

        for(int i = 0; i < game.labyrinthe.getSizeX(); i++) {
            for(int j = 0; j < game.labyrinthe.getSizeY(); j++) {
                if(game.labyrinthe.isFood(i, j)) {
                    capsules.add(new PositionAgent(i, j, 0));
                }
            }
        }

        return capsules.stream().min((o1, o2) -> {
            return findShortestPath(pos, o1).size() - findShortestPath(pos, o2).size();
        }).get();
    }

    public PositionAgent getCloserEnemy(Pacman p) {
        ArrayList<PositionAgent> enemies = new ArrayList<>();

        for(Agent a : game.listeAgents) {
            if(a instanceof Fantome) {
                enemies.add(a.pos);
            }
        }

        return enemies.stream().min((o1, o2) -> {
            return findShortestPath(p.pos, o1).size() - findShortestPath(p.pos, o2).size();
        }).get();
    }

    public PositionAgent getCloserPacman(Fantome f) {
        ArrayList<PositionAgent> pacmans = new ArrayList<>();

        for(Agent a : game.listeAgents) {
            if(a instanceof Pacman) {
                pacmans.add(a.pos);
            }
        }

        return pacmans.stream().min((o1, o2) -> {
            return findShortestPath(f.pos, o1).size() - findShortestPath(f.pos, o2).size();
        }).get();
    }
}