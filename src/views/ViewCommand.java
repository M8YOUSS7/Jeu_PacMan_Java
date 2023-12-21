package views;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.util.Observable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controllers.AbstractController;
import models.Game;

public class ViewCommand extends Ecran {
    public JPanel global;
    public JPanel haut;
    public JPanel bas;
    public JPanel plus;
    public JLabel infoTour;
    public JButton restart;
    public JButton pause;
    public JButton run;
    public JButton step;
    public JSlider slider;

    public JButton manuelButton;
    public JCheckBox virtualRowsBox;
    public PanelVirtualArrows pva;

    public AbstractController control;

    public ViewCommand(Game g, AbstractController c) {
        super(g, "Commande");

        global = new JPanel(new GridLayout(3, 1));

        haut = new JPanel(new GridLayout(1, 4));
        
        Icon restartIcon = new ImageIcon("icons/icon_restart.png");
        restart = new JButton(restartIcon);
        haut.add(restart);

        Icon pauseIcon = new ImageIcon("icons/icon_pause.png");
        pause = new JButton(pauseIcon);
        haut.add(pause);
        

        Icon runIcon = new ImageIcon("icons/icon_run.png");
        run = new JButton(runIcon);
        haut.add(run);

        Icon stepIcon = new ImageIcon("icons/icon_step.png");
        step = new JButton(stepIcon);
        haut.add(step);
        
        bas = new JPanel(new GridLayout(1, 2));
        
        slider = new JSlider(1, 10, 1);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.add(new Label("Number of turn per second"));
        //slider.setLabelTable();
        
        bas.add("Number of turns per second", slider);
        infoTour = new JLabel("Turn : 0", JLabel.CENTER);
        bas .add(infoTour);
        
        plus = new JPanel(new GridLayout(1, 2));

        Icon manuelIcon = new ImageIcon(new ImageIcon("icons/manuel.png", "Mode Manuel").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
        manuelButton = new JButton(manuelIcon);
        plus.add(manuelButton);

        virtualRowsBox = new JCheckBox("Virtual Rows");
        virtualRowsBox.setSelected(false);
        virtualRowsBox.setEnabled(false);
        plus.add(virtualRowsBox);

        global.add(haut);
        global.add(bas);
        global.add(plus);
        ecran.add(global);

        setDimension(600, 300);
        setLocation(4000, 350);
        control = c;

        pva = new PanelVirtualArrows();
        pva.setVisible(false);
    }

    public void update(Observable arg0, Object arg1) {
        infoTour.setText("Turn : " + ((Game)arg0).turn);
    }
        
}
