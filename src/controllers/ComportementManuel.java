package controllers;

import views.ViewCommand;

public class ComportementManuel extends Comportement {

    public ComportementManuel(ViewCommand cVw) {
        super(cVw);
    }

    @Override
    public void ajuste() {
        cView.run.setEnabled(false);
        cView.step.setEnabled(false);
        cView.pause.setEnabled(false);
        cView.game.isRunning = false;
        cView.manuelButton.setEnabled(false);
        cView.restart.setEnabled(true);
        cView.virtualRowsBox.setEnabled(true);
    }

}
