
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;

public abstract class Ecran implements Observer {
    Game game;
    JFrame ecran;
    int hauteur;
    int largeur;

    public Ecran(Game g, String tittle) {
        game =g;
        ecran = new JFrame(tittle);
        g.addObserver(this);
    }

    protected void setDimension(int dimX, int dimY) {
        ecran.setSize(new Dimension(dimX, dimY));
    }

    protected void setLocation(int difHauteur, int difLargeur) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centre = ge.getCenterPoint();
        int dx = centre.x - (largeur/2) - difLargeur;
        int dy = centre.y - (hauteur/2) - difHauteur;
        ecran.setLocation(dx, dy);
    }

    protected void setHauteur(int hauteur) {
        this.hauteur = hauteur;
        ecran.setLocation(largeur, hauteur);
    }

    protected void setLargeur(int largeur) {
        this.largeur = largeur;
        ecran.setLocation(largeur, hauteur);
    }

    protected void afficher() {
        ecran.setVisible(true);
    }

    protected void masquer() {
        ecran.setVisible(false);
    }
}
