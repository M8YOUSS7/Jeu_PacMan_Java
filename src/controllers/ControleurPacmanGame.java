package controllers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.Maze;
import models.PacmanGame;
import views.ViewCommand;
import views.ViewPacmanGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurPacmanGame extends AbstractController {
    Comportement cmptmt;
    ViewPacmanGame pkmView;
    private ViewCommand pkmViewCommand;
    protected String fileName = "originalClassic.lay";

    public ControleurPacmanGame(int mxt) throws Exception {
        super(new PacmanGame(mxt, new Maze("layouts/originalClassic.lay")));
        pkmView = new ViewPacmanGame(game);
        pkmViewCommand = new ViewCommand(game, this);
        cmptmt = new SimpleCompotementBtnRestart(pkmViewCommand);
        
        connectBouttons();
        initializeMenu();

        pkmViewCommand.afficher();
        pkmView.afficher();
    }

    public ViewPacmanGame getPkViewPacmanGame() {
        return pkmView;
    }

    public PacmanGame getPkmModel() {
        return ((PacmanGame) game);
    }
    
    protected void initializeMazeDeFichier() {
        JFileChooser jfc = new JFileChooser("./layouts");
        
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            fileName = selectedFile.getName();
            System.out.println(fileName);
            try {
                Maze maze = new Maze("layouts/" + fileName);
                getPkmModel().setLabyrinthe(maze);
                game.init();
                pkmView.setMaze(maze);
                pkmView.setDimension(maze.getSizeX() *20, maze.getSizeY() *20);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void connectBouttons() {
        pkmViewCommand.restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    try {
                        getPkmModel().setLabyrinthe(new Maze("layouts/" + fileName));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    restart();
                    pkmView.setMaze(getPkmModel().getLabyrinthe());
                    cmptmt = new SimpleCompotementBtnRestart(pkmViewCommand);
                }
            });

        pkmViewCommand.pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    pause();
                    cmptmt = new SimpleComportementBtnPause(pkmViewCommand);
                }
            });

        pkmViewCommand.run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    play();
                    cmptmt = new SimpleComportementBtnRun(pkmViewCommand);
                }
            });

        pkmViewCommand.step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    step();
                    cmptmt = new SimpleComportementBtnStep(pkmViewCommand);
                }
            });

        pkmViewCommand.slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                    setSpeed(1000 / (double)pkmViewCommand.slider.getValue());
                }
            });
    }

    protected void initializeMenu() {
        pkmView.chgLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                initializeMazeDeFichier();
            }
            });


        pkmView.exitApp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    public String getFileName() {
        return fileName;
    }
}
