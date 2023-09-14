import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ControllerSimpleGame extends AbstractController {
    Comportement cmptmt;

    public ControllerSimpleGame() {
        super(new SimpleGame(15));
        game.init();
        ViewSimpleGame sView = new ViewSimpleGame(game);
        sView.setLocation(100, 350);
        ViewCommand cView = new ViewCommand(game, this);
        cView.setLocation(400, 350);
        

        cView.restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    restart();
                }
            });

        cView.pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    pause();
                    cmptmt = new SimpleComportementCommandArreter(cView);
                    cmptmt.ajuste();
                }
            });

        cView.run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    play();
                    cmptmt = new SimpleComportementCommandLancer(cView);
                    cmptmt.ajuste();
                }
            });

        cView.step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evenement) {
                    step();
                    cmptmt = new SimpleComportementCommandLancer(cView);
                    cmptmt.ajuste();
                }
            });

        cView.slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                    setSpeed((double)cView.slider.getValue() * 1000);
                }
            });

        sView.afficher();
        cView.afficher();
        cmptmt = new SimpleComportementCommandArreter(cView);
        cmptmt.ajuste();
    }


}
