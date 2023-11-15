package tests;

import java.util.ArrayList;
import java.util.stream.Collectors;

import controllers.ControleurPacmanGame;
import models.Agent;

public class Test {
    public static void main(String[] args) throws Exception {
        /*
        SimpleGame sGame = new SimpleGame(35);
        //ViewSimpleGame sView = new ViewSimpleGame(sGame);
        //cView.setLocation(256, 350);
        //sView.afficher();
        ViewCommand cView = new ViewCommand(sGame, null);
        cView.setLocation(256, 350);
        cView.afficher();
        
        sGame.init();
        //sGame.step();
        //sGame.run();
        sGame.lunch();
        
        
        ControllerSimpleGame ctr = new ControllerSimpleGame();
        */

        ControleurPacmanGame ctr = new ControleurPacmanGame(15000);
        
        //ctr.play();
    }
}