package views;

import java.util.Observable;

import javax.swing.JLabel;

import models.Game;

public class ViewSimpleGame extends Ecran {
    JLabel message;

    public ViewSimpleGame(Game g) {
        super(g, "Game");
        message = new JLabel("Current turn : "+ g.turn, JLabel.CENTER);
        ecran.add(message);
        setDimension(600, 600);
    }

    public void setMessage(JLabel message) {
        this.message = message;
    }

    public void update(Observable arg0, Object arg1) {
        message.setText("Current turn : " + ((Game)arg0).turn);
    }
}
