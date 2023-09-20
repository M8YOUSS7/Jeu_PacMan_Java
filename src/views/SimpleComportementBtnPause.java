package views;

public class SimpleComportementBtnPause extends Comportement {
    public SimpleComportementBtnPause(ViewCommand cVw) {
        super(cVw);
    }

    public void ajuste() {
        cView.run.setEnabled(true);
        cView.step.setEnabled(true);
        cView.pause.setEnabled(false);
        cView.restart.setEnabled(true);
    }
}
