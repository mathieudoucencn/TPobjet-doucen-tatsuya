
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
 * Classe représentant un point en 2D.
 */
public class Point2D {

    private int X;
    private int Y;

    /**
     * Constructeur par défaut
     */
    public Point2D() {
        this.X = 0;
        this.Y = 0;
    }

    /**
     * Constructeur avec coordonnées.
     * @param x Coordonnée X.
     * @param y Coordonnée Y.
     */
    public Point2D(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Constructeur par copie.
     * @param point Point à copier.
     */
    public Point2D(Point2D point) {
        this.X = point.X;
        this.Y = point.Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int coordX) {
        this.X = coordX;
    }

    public int getY() {
        return Y;
    }

    public void setY(int coordY) {
        this.Y = coordY;
    }

    /**
     * Modifie la position du point.
     * @param x Nouvelle coordonnée X.
     * @param y Nouvelle coordonnée Y.
     */
    public void setPosition(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Déplace le point de dx et dy.
     * @param dx Déplacement en X.
     * @param dy Déplacement en Y.
     */
    public void translate(int dx, int dy) {
        this.X += dx;
        this.Y += dy;
    }

    /**
     * Vérifie si deux points sont égaux.
     * @param pt Point à comparer.
     * @return true si les points sont égaux, false sinon.
     */
    public boolean equals(Point2D pt) {
        return ((this.getX() == pt.getX()) && (this.getY() == pt.getY()));
    }

    /**
     * Affiche les coordonnées du point.
     */
    public void affiche() {
        System.out.println("[" + X + " ; " + Y + "]");
    }

    /**
     * Calcule la distance entre ce point et un autre point.
     * @param point Point cible.
     * @return Distance entre les deux points.
     */
    public double distance(Point2D point) {
        int dx = this.X - point.getX();
        int dy = this.Y - point.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
