package md.tp.poo;

/**
 * Classe abstraite qui représente un objet basique
 * @author woota & mathi
 */

public abstract class Objet extends ElementDeJeu{
    
    //attributs    
    protected String typeNom;

    //méthodes
    /**
     * Constructeur avec position.
     *
     * @param n Nom du type d'objet.
     * @param p Position de l'objet.
     */
    public Objet(String n, Point2D p) {
        super(p);  
        this.typeNom = n;
    }

    /**
     * Constructeur par copie.
     *
     * @param o Objet à copier.
     */
    public Objet(Objet o) {
        super(o);  
        this.typeNom = o.typeNom;
    }

    /**
     * Constructeur avec seulement le nom du type d'objet.
     *
     * @param n Nom du type d'objet.
     */
    public Objet(String n) {
        super();  
        this.typeNom = n;
    }

    /**
     * Constructeur par défaut
     */
    public Objet() {
        super();  
    }
    
    /**
     * 
     */
    public void affiche(){ 
    }
    
    /**
     * fonction d'effacement de l'objet
     * @param world 
     */
    public void removeObjet(World world) {
        int i = world.getObjets().indexOf(this);
        world.getObjets().remove(i);
    }

    public String getTypeNom() {
        return typeNom;
    }
    

    /**
     * Applique l'effet de l'objet sur un personnage
     * @param p Personnage cible
     */
    public abstract void utiliser(Personnage p);
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY();
    }
}
