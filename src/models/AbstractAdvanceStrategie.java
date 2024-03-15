package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public abstract class AbstractAdvanceStrategie implements Strategie {
    protected PacmanGame game;
    
    AbstractAdvanceStrategie(PacmanGame game) {
        this.game = game;
    }

    protected class Node {
        PositionAgent position;
        int distance;
        Node predecessor;

		public Node(PositionAgent position, int distance, Node predecessor) {
			this.position = position;
			this.distance = distance;
			this.predecessor = predecessor;
		}

        public boolean seen(PositionAgent position) {
            Node currentNode = this;

            while (currentNode != null) {
                if(currentNode.position.equals(position))
                    return true;
                currentNode = currentNode.predecessor;
            }
            return false;
        }

        public boolean equals(Node node) {
            Node currentNode = this;
            Node currentNode_ = node;

            while (currentNode != null && currentNode_ != null) {
                if(!currentNode.position.equals(currentNode_.position))
                    return false;
                currentNode = currentNode.predecessor;
                currentNode_ = currentNode_.predecessor;
            }
                return true;
        }

        public ArrayList<PositionAgent> getPath() {
            ArrayList<PositionAgent> path = new ArrayList<>();
            Node currentNode = this;

            while (currentNode != null) {
                path.add(currentNode.position);
                currentNode = currentNode.predecessor;
            }

            	return path;
        }
    }



	public ArrayList<ArrayList<PositionAgent>> findAllPath(PositionAgent start, PositionAgent end, Maze maze) {
        Map<PositionAgent, Node> nodes = new HashMap<>();
        Node endNode = new Node(end, 0, null);
        
        nodes.put(end, endNode);
        
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        queue.add(endNode);
        
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            ArrayList<PositionAgent> neighbors = getNeighbors(currentNode.position, maze, currentNode.position);
            
			for (PositionAgent neighbor : neighbors) {
                if(!currentNode.seen(neighbor)) {
					if(nodes.entrySet().stream().anyMatch(e -> e.getValue().seen(neighbor))) {

						nodes.entrySet().stream().filter(e -> e.getValue().seen(neighbor)).forEach(e -> {

							Node neighborNode = e.getValue();

							while (neighborNode != null) {
								if(neighborNode.position.equals(neighbor)) {
									
									if(neighborNode.distance > currentNode.distance +1) {
										nodes.put(neighbor, new Node(neighbor, currentNode.distance +1, currentNode));
									}
									
									break;
								}
								neighborNode = neighborNode.predecessor;
							}

						});
					} else {
						Node neighborNode = new Node(neighbor, currentNode.distance +1, currentNode);
						nodes.put(neighbor, neighborNode);
						if(!neighbor.equals(start))
                            queue.add(neighborNode);
					}
				}
            }
        }

        ArrayList<ArrayList<PositionAgent>> paths = nodes.entrySet().stream().filter(v -> v.getValue().seen(start)).map(v -> v.getValue().getPath()).collect(Collectors.toCollection(ArrayList::new));

        return paths;
    }


    
    public ArrayList<PositionAgent> findShortestPath(PositionAgent start, PositionAgent end, Maze maze) {
        //return findAllPath(start, end, maze).stream().filter(path -> path.get(0).equals(start)).findFirst().get();
        return findAllPath(start, end, maze).stream().min(Comparator.comparingInt(ArrayList::size)).get();
    }



    private ArrayList<PositionAgent> getNeighbors(PositionAgent position, Maze maze, PositionAgent lastPosition) {
        // Implémentez cette méthode pour retourner les voisins de la position donnée qui ne sont pas la derniere position
        ArrayList<PositionAgent> res = new ArrayList<>();
        
        if(	position.getY()+1 < maze.getSizeY() && !(maze.isWall(position.getX(), position.getY()+1)) &&
			!(lastPosition.getX() == position.getX() && lastPosition.getY() == position.getY()+1))
				res.add(new PositionAgent(position.getX(), position.getY()+1, AgentAction.NORTH));
        if(position.getY()-1 >= 0 && !(maze.isWall(position.getX(), position.getY()-1)) &&
			!(lastPosition.getX() == position.getX() && lastPosition.getY() == position.getY()-1))
				res.add(new PositionAgent(position.getX(), position.getY()-1, AgentAction.SOUTH));
        if(position.getX()-1 >= 0 && !(maze.isWall(position.getX()-1, position.getY())) &&
			!(lastPosition.getX() == position.getX()-1 && lastPosition.getY() == position.getY()))
				res.add(new PositionAgent(position.getX()-1, position.getY(), AgentAction.EAST));
        if(position.getX()+1 < maze.getSizeX() && !(maze.isWall(position.getX()+1, position.getY())) &&
			!(lastPosition.getX() == position.getX()+1 && lastPosition.getY() == position.getY()))
				res.add(new PositionAgent(position.getX()+1, position.getY(), AgentAction.WEST));

            return res;
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
            return (!capsules.isEmpty()) ? capsules.stream().min((o1, o2) -> findShortestPath(pos, o1, maze).size()-findShortestPath(pos, o2, maze).size()).get() : null;
    }

    public PositionAgent getCloserFood(PositionAgent pos, Maze maze) {
        ArrayList<PositionAgent> foods = new ArrayList<>();

        for(int i = 0; i < game.labyrinthe.getSizeX(); i++) {
            for(int j = 0; j < game.labyrinthe.getSizeY(); j++) {
                if(game.labyrinthe.isFood(i, j)) {
                    foods.add(new PositionAgent(i, j, 0));
                }
            }
        }

        HashMap<PositionAgent, Integer> distances = new HashMap<>();
        foods.forEach(f -> distances.put(f, findShortestPath(pos, f, maze).size()));
        foods.sort((o1, o2) -> distances.get(o1)-distances.get(o2));
        
            return (!foods.isEmpty()) ? foods.get(0) : null;
    }

    public PositionAgent getCloserEnemy(Agent agt, Maze maze) {
        ArrayList<PositionAgent> enemies = new ArrayList<>();

        if(agt instanceof Pacman) {
            for(Agent a : game.listeAgents)
                if(a instanceof Fantome) {
                    enemies.add(a.pos);
                }
        } else {
            for(Agent a : game.listeAgents)
                if(a instanceof Pacman)
                    enemies.add(a.pos);
        }
            return (!enemies.isEmpty()) ? enemies.stream().min((o1, o2) -> findShortestPath(agt.pos, o1, maze).size()-findShortestPath(agt.pos, o2, maze).size()).get() : null;
    }


    protected AgentAction moveCloser(PositionAgent p, PositionAgent p_) {
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

    protected AgentAction moveAway(PositionAgent p, PositionAgent p_) {
        if(p.getX() == p_.getX()) {
            if(p.getY() == p_.getY()+1) {
                return new AgentAction(AgentAction.SOUTH);
            } else if(p.getY() == p_.getY()-1) {
                return new AgentAction(AgentAction.NORTH);
            }
        } else if(p.getY() == p_.getY()) {
            if(p.getX() == p_.getX()+1) {
                return new AgentAction(AgentAction.EAST);
            } else if(p.getX() == p_.getX()-1) {
                return new AgentAction(AgentAction.WEST);
            }
        }
            return new AgentAction(AgentAction.STOP);
    }
}