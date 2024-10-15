package md.tp.poo;
/**
 * Classe représentant un point sur une grille de position
 * @author woota & mathi
 */

public class Point2D {
    
    //attributs
    private int X;
    private int Y;
    
    //méthodes
    
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
    
    /**
     * Constructeur par défaut
     */
    public Point2D() {
        this.X = 0;
        this.Y = 0;
    }
    
    /**
     * 
     * @return 
     */
    public int getX() {
        return X;
    }
    
    /**
     * 
     * @param coordX 
     */
    public void setX(int coordX) {
        this.X = coordX;
    }
    
    /**
     * 
     * @return 
     */
    public int getY() {
        return Y;
    }
    
    /**
     * 
     * @param coordY 
     */
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
