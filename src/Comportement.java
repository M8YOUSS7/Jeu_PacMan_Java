public abstract class Comportement {
    ViewCommand cView;

    public Comportement(ViewCommand cVw) {
        cView = cVw;
    }

    public abstract void ajuste();
}
