package md.tp.poo;

/**
 * Classe qui représente une épée
 * @author woota & mathi
 */

public class Epee extends Objet implements Utilisable {
    
    //attributs
    private int duree;
    private int valDam;
    
    //méthodes
    /**
     * Constructeur avec position et valeur de soin.
     *
     * @param p Position de la potion
     * @param valDam
     * @param duree
     */
    public Epee(Point2D p, int valDam, int duree) {
        super("Epee", p);
        this.valDam = valDam;
        this.duree = duree;
    }
    
    /**
     * Constructeur par copie
     * @param e 
     */
    public Epee(Epee e) {
        super(e);
        this.valDam = e.valDam;
        this.duree = e.duree;
    }
    
    /**
     * Constructeur par défault
     */
    public Epee() {
        super("Epee");
    }
    
    /**
     * utilisation de l'épee
     * @param p 
     */
    @Override
    public void utiliser(Personnage p) {

        if (p instanceof Guerrier) {
            ((Guerrier) p).setBonusArme(this.valDam);
        } else {
            System.out.println(p.getNom() + "a trouve epee mais ne sait pas utiliser cette arme...");
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getDuree() {
        return duree;
    }
    
    /**
     * 
     * @param duree 
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    /**
     * 
     * @return 
     */
    public int getValDam() {
        return valDam;
    }
    
    /**
     * 
     * @param valDam 
     */
    public void setValDam(int valDam) {
        this.valDam = valDam;
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.valDam + " " + this.duree;
    }
}
