package md.tp.poo;

/**
 * Super classe pour les éléments du jeu (creatures, objets).
 * @author woota
 */

public abstract class ElementDeJeu {
    
    //attributs
    protected Point2D position;
    
    //méthodes
    /**
     * Constructeur avec paramètres
     * @param position 
     */
    public ElementDeJeu(Point2D position) {
        this.position = new Point2D(position);
    }
    
    /**
     * Constructeur par copie
     * @param e 
     */
    public ElementDeJeu(ElementDeJeu e) {
        this.position = new Point2D(e.getPosition());
    }
    
    /**
     * Constructeur par default
     */
    public ElementDeJeu() {
        this.position = new Point2D();
    }
    
    /**
     * 
     * @return 
     */
    public Point2D getPosition() {
        return position;
    }
    
    /**
     * 
     * @param position 
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    public abstract void affiche();
}
