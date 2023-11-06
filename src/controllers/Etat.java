package controllers;

public abstract class Etat {
    ControllerSimpleGame control;

    public Etat(ControllerSimpleGame cnt) {
        control = cnt;
        ajuste();
    }

    public abstract void ajuste();
}
