package views;

import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelVirtualArrows extends JFrame {
    public JButton up;
    public JButton down;
    public JButton left;
    public JButton right;
    public JButton stop;
    public JPanel surEcoute;

    public PanelVirtualArrows() {
        super("Virtual Arrows");
        this.setSize(200, 200);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);

        surEcoute = new JPanel(new GridLayout(3, 3));
        surEcoute.setOpaque(true);
        surEcoute.setFocusable(true);

        up = new JButton(new ImageIcon(new ImageIcon("icons/up_arrow.png", "Up Arrow").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        down = new JButton(new ImageIcon(new ImageIcon("icons/down_arrow.png", "Down Arrow").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        left = new JButton(new ImageIcon(new ImageIcon("icons/left_arrow.png", "Left Arrow").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        right = new JButton(new ImageIcon(new ImageIcon("icons/right_arrow.png", "Right Arrow").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));
        stop = new JButton(new ImageIcon(new ImageIcon("icons/stop.jpg", "Stop").getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT)));

        surEcoute.add(new JPanel());
        surEcoute.add(up);
        surEcoute.add(new JPanel());
        surEcoute.add(left);
        surEcoute.add(stop);
        surEcoute.add(right);
        surEcoute.add(new JPanel());
        surEcoute.add(down);
        surEcoute.add(new JPanel());

        this.add(surEcoute);
    }
    
}
