public class SimpleComportementCommandArreter extends Comportement {
    public SimpleComportementCommandArreter(ViewCommand cVw) {
        super(cVw);
    }

    public void ajuste() {
        cView.run.setEnabled(true);
        cView.step.setEnabled(true);
        cView.pause.setEnabled(false);
        cView.restart.setEnabled(false);
    }
}
