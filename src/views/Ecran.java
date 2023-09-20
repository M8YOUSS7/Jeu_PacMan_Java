package views;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import models.Game;

import java.util.Observable;
import java.util.Observer;

public abstract class Ecran implements Observer {
    public Game game;
    public JFrame ecran;
    public int hauteur;
    public int largeur;

    public Ecran(Game g, String tittle) {
        game =g;
        ecran = new JFrame(tittle);
        g.addObserver(this);
    }

    public void setDimension(int dimX, int dimY) {
        ecran.setSize(new Dimension(dimX, dimY));
    }

    public void setLocation(int difHauteur, int difLargeur) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centre = ge.getCenterPoint();
        int dx = centre.x - (largeur/2) - difLargeur;
        int dy = centre.y - (hauteur/2) - difHauteur;
        ecran.setLocation(dx, dy);
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
        ecran.setLocation(largeur, hauteur);
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
        ecran.setLocation(largeur, hauteur);
    }

    public void afficher() {
        ecran.setVisible(true);
    }

    public void masquer() {
        ecran.setVisible(false);
    }
}
