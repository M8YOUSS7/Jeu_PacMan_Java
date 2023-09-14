public abstract class Comportement {
    ViewCommand cView;

    public Comportement(ViewCommand cVw) {
        cView = cVw;
        ajuste();
    }

    public abstract void ajuste();
}
