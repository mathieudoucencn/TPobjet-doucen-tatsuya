/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */
/**
 * Super classe pour les éléments du jeu (creatures, objets).
 */
public abstract class ElementDeJeu {

    protected Point2D position;

    public ElementDeJeu(Point2D position) {
        this.position = new Point2D(position);
    }

    public ElementDeJeu(ElementDeJeu e) {
        this.position = new Point2D(e.getPosition());
    }
    

    public ElementDeJeu() {
        this.position = new Point2D();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public abstract void affiche();
}
