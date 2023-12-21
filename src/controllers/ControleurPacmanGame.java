package controllers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.ManualStrategie;
import models.Maze;
import models.PacmanGame;
import views.ViewCommand;
import views.ViewPacmanGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ControleurPacmanGame extends AbstractController {
    Comportement cmptmt;
    ViewPacmanGame pkmView;
    ViewCommand pkmViewCommand;
    ManualStrategie manualStrategie;

    protected String fileName = "originalClassic.lay";

    public ControleurPacmanGame(int mxt) throws Exception {
        super(PacmanGame.getIstance(mxt, new Maze("layouts/originalClassic.lay")));
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

        pkmViewCommand.virtualRowsBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(pkmViewCommand.virtualRowsBox.isSelected()) pkmViewCommand.pva.setVisible(true); else pkmViewCommand.pva.setVisible(false);
            }
        });
        
        pkmViewCommand.manuelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie = getPkmModel().setPacmanManuel();
                cmptmt = new ComportementManuel(pkmViewCommand);
                pkmViewCommand.virtualRowsBox.setSelected(true);
                pkmViewCommand.pva.setVisible(true);
            }
        });

        pkmViewCommand.pva.up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie.setAction(KeyEvent.VK_UP);
                getPkmModel().step();
            }
        });

        pkmViewCommand.pva.down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie.setAction(KeyEvent.VK_DOWN);
                getPkmModel().step();
            }
        });

        pkmViewCommand.pva.left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie.setAction(KeyEvent.VK_LEFT);
                getPkmModel().step();
            }
        });


        pkmViewCommand.pva.stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie.setAction(KeyEvent.VK_SPACE);
                getPkmModel().step();
            }
        });

        pkmViewCommand.pva.right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                manualStrategie.setAction(KeyEvent.VK_RIGHT);
                getPkmModel().step();
            }
        });

        pkmViewCommand.pva.surEcoute.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(cmptmt instanceof ComportementManuel) {
                    if(event.getKeyCode() == KeyEvent.VK_UP) {
                        manualStrategie.setAction(KeyEvent.VK_UP);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
                        manualStrategie.setAction(KeyEvent.VK_DOWN);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
                        manualStrategie.setAction(KeyEvent.VK_LEFT);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        manualStrategie.setAction(KeyEvent.VK_RIGHT);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_SPACE) {
                        manualStrategie.setAction(KeyEvent.VK_SPACE);
                        getPkmModel().step();
                    }
                }
            }
        });

        pkmViewCommand.pva.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if(cmptmt instanceof ComportementManuel) {
                    if(event.getKeyCode() == KeyEvent.VK_UP) {
                        manualStrategie.setAction(KeyEvent.VK_UP);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
                        manualStrategie.setAction(KeyEvent.VK_DOWN);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
                        manualStrategie.setAction(KeyEvent.VK_LEFT);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        manualStrategie.setAction(KeyEvent.VK_RIGHT);
                        getPkmModel().step();
                    } else if(event.getKeyCode() == KeyEvent.VK_SPACE) {
                        manualStrategie.setAction(KeyEvent.VK_SPACE);
                        getPkmModel().step();
                    }
                }
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
