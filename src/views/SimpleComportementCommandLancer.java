package views;

public class SimpleComportementCommandLancer extends Comportement {
    public SimpleComportementCommandLancer(ViewCommand cView) {
        super(cView);
    }

    public void ajuste() {
        cView.run.setEnabled(false);
        cView.step.setEnabled(false);
        cView.pause.setEnabled(true);
        cView.restart.setEnabled(true);
    }
}
