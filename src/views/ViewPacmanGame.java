package views;

import models.Maze;
import models.Pacman;
import models.PacmanGame;
import models.PositionAgent;
import models.Agent;
import models.Fantome;
import models.Game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ViewPacmanGame extends Ecran {
    public PanelPacmanGame pkmPannel;
    JMenu menu;
    JMenuBar mb;
    public JMenuItem chgLab;
    public JMenuItem exitApp;

    public ViewPacmanGame(Game game) throws Exception {
        super(game, "PacmanGame");

        //configuration de menu
        menu = new JMenu("Menu");
        mb = new JMenuBar();
        chgLab = new JMenuItem("Changer de labyrinthe");
        exitApp = new JMenuItem("Quitter");
        exitApp.setMnemonic(KeyEvent.VK_Q);
        exitApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        exitApp.setToolTipText("Exit application");
        menu.add(chgLab);
        menu.add(exitApp);
        mb.add(menu);
        ecran.setJMenuBar(mb);

        Maze m =((PacmanGame) game).getLabyrinthe();
        pkmPannel = new PanelPacmanGame(m);
        ecran.add(pkmPannel);
        setDimension(m.getSizeX() *20, m.getSizeY() *20);
        setLocation(150, 325);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        ArrayList<PositionAgent> pacmansPos = new ArrayList<PositionAgent>();
        ArrayList<PositionAgent> fantomesPos = new ArrayList<PositionAgent>();
        
        for(Agent e : ((PacmanGame) arg0).getListeAgents()) {
            if(e instanceof Fantome) {
                fantomesPos.add(e.getPos());
            } else pacmansPos.add(e.getPos());
        }

        pkmPannel.setPacmans_pos(pacmansPos);
        pkmPannel.setGhosts_pos(fantomesPos);

        pkmPannel.repaint();
    }

    public void setMaze(Maze maze) {
        pkmPannel.setMaze(maze);
        pkmPannel.repaint();
    }
}
