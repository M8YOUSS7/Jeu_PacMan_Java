package controllers;

public class EtatDebutDeJeu extends Etat {

    public EtatDebutDeJeu(ControllerSimpleGame cnt) {
        super(cnt);
    }

    public void ajuste() {
        control.cView.run.setEnabled(true);
        control.cView.step.setEnabled(true);
        control.cView.pause.setEnabled(false);
        control.cView.restart.setEnabled(false);
    }
    
}
