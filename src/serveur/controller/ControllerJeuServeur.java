package serveur.controller;

import java.util.List;

import models.Maze;
import models.PacmanGame;
import models.ReaderWriter;
import controllers.AbstractController;

public class ControllerJeuServeur extends AbstractController {
    String fileName = "originalClassic.lay";
    List<ReaderWriter> listeJoueurs;

    public ControllerJeuServeur(int mt) throws Exception {
        super(PacmanGame.getIstance(mt, new Maze("layouts/originalClassic.lay")));
        getModele().setPacmanManuel();
    }

    public PacmanGame getModele() {
        return ((PacmanGame) game);
    }

    public void addPlayer(ReaderWriter player) {
        listeJoueurs.add(player);
    }

    public int getPlayerCount() {
        return listeJoueurs.size();
    }

    public void removePlayer(ReaderWriter player) {
        listeJoueurs.remove(player);
    }
}
