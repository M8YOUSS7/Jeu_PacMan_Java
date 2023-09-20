package views;

public class SimpleComportementBtnRun extends Comportement {
    public SimpleComportementBtnRun(ViewCommand cVw) {
        super(cVw);
    }

    public void ajuste() {
        cView.run.setEnabled(false);
        cView.step.setEnabled(true);
        cView.pause.setEnabled(true);
        cView.restart.setEnabled(true);
    }
    
}
