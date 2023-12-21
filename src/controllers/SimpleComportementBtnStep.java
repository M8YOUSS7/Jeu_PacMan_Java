package controllers;

import views.ViewCommand;

public class SimpleComportementBtnStep extends Comportement {
    public SimpleComportementBtnStep(ViewCommand cVw) {
        super(cVw);
    }

    public void ajuste() {
        cView.run.setEnabled(true);
        cView.step.setEnabled(true);
        cView.pause.setEnabled(false);
        cView.restart.setEnabled(true);
        cView.game.isRunning = false;
    }
}
