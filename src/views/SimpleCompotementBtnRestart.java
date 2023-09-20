package views;

public class SimpleCompotementBtnRestart extends Comportement {
    public SimpleCompotementBtnRestart(ViewCommand cVw) {
        super(cVw);
    }

    public void ajuste() {
        cView.run.setEnabled(true);
        cView.step.setEnabled(true);
        cView.pause.setEnabled(false);
        cView.restart.setEnabled(false);
    }
}
