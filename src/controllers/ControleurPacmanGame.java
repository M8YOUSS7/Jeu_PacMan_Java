package controllers;

import java.io.File;

import javax.swing.JFileChooser;

import models.Maze;
import models.PacmanGame;
import views.ViewPacmanGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurPacmanGame extends AbstractController {
    ViewPacmanGame pkmView;
    PacmanGame pkmModel;

    public ControleurPacmanGame(int mxt) throws Exception {
        super(new PacmanGame(mxt, new Maze("layouts/originalClassic.lay")));
        pkmView = new ViewPacmanGame(game);
        pkmModel = ((PacmanGame) game);

        pkmView.chgLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                JFileChooser jfc = new JFileChooser("./layouts");

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);
        
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    String fileName = selectedFile.getName();
                    System.out.println(fileName);
                    try {
                        Maze maze = new Maze("layouts/" + fileName);
                        pkmModel.setLabyrinthe(maze);
                        pkmView.pkmPannel.setMaze(pkmModel.getLabyrinthe());
                        pkmView.pkmPannel.repaint();
                        pkmView.setDimension(maze.getSizeX() *20, maze.getSizeY() *20);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pkmModel.initializeGame();
                }
            }
            });


            pkmView.exitApp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });

        
        pkmView.afficher();
    }

    public ViewPacmanGame getPkViewPacmanGame() {
        return pkmView;
    }

    public PacmanGame getPkmModel() {
        return pkmModel;
    }
    
}
