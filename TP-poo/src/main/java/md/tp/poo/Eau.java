package md.tp.poo;

/**
 * Classe Eau, un type spécifique de Nourriture qui donne un bonus sur degAtt.
 * @author woota & mathi
 */


public class Eau extends Nourriture {
    
    //attributs
    
    //méthodes
    /**
     * Constructeur avec position, effet, durée et caractéristique.
     *
     * @param p Position de l'eau.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
   public Eau(Point2D p, int effet, int duree, String caract) {
        super("Eau", p, effet, duree, caract); 
    }

    /**
     * Constructeur par copie.
     *
     * @param e Eau à copier.
     */
    public Eau(Eau e) {
        super(e); 
    }
    
    /**
     * Constructeur par défaut.
     */
    public Eau() {
        super("Eau");  
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.getEffet() + " " + this.getDuree() + " " + this.getCaract();
    }
}

