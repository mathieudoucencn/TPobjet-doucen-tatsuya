package md.tp.poo;

/**
 * Classe qui représente un consommable
 * @author woota & mathi
 */

public class Miel extends Nourriture{
    
    //attributs
    
    //méthodes
    /**
     * Constructeur avec paramètres.
     *
     * @param p Position du miel.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
   public Miel(Point2D p, int effet, int duree, String caract) {
        super("Miel", p, effet, duree, caract); 
    }

    /**
     * Constructeur par copie.
     *
     * @param m Miel à copier.
     */
    public Miel(Miel m) {
        super(m); 
    }
    
    /**
     * Constructeur par défaut.
     */
    public Miel() {
        super("Miel");  
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
