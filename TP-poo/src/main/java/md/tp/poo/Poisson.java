package md.tp.poo;

/**
 *
 * @author woota & mathi
 */
public class Poisson extends Nourriture {
    
    //attributs
    
    //méthodes
    /**
     * Constructeur avec position, effet, durée et caractéristique.
     *
     * @param p Position du poisson.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
   public Poisson(Point2D p, int effet, int duree, String caract) {
        super("Poisson", p, effet, duree, caract); 
    }

    /**
     * Constructeur par copie.
     *
     * @param p Poisson à copier.
     */
    public Poisson(Poisson p) {
        super(p); 
    }
    
    /**
     * Constructeur par défaut.
     */
    public Poisson() {
        super("Poisson");  
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    @Override
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.getEffet() + " " + this.getDuree() + " " + this.getCaract();
    }
}
