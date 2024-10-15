package md.tp.poo;

/**
 * Classe qui représente une potion de soin.
 * @author woota & mathi
 */

public class PotionSoin extends Objet implements Utilisable {
    
    //attributs
    private int valSoin;
    
    //méthodes
    /**
     * Constructeur avec position et valeur de soin.
     * @param p Position de la potion
     * @param val Valeur de soin
     */
    public PotionSoin(Point2D p, int val) {
        super("PotionSoin",p);
        this.valSoin = val;
    }
    
    /**
     * Constructeur par copie
     * @param ps 
     */
    public PotionSoin(PotionSoin ps) {
        super(ps);
        this.valSoin = ps.valSoin;
    }
    
    /**
     * Constructeur par defaut
     */
    public PotionSoin() {
        super("PotionSoin");
        this.valSoin = 20; 
    }
    
    /**
     * fonction d'utilisation de la potion
     * @param p 
     */
    @Override
    public void utiliser(Personnage p) {
        p.setPtVie(p.getPtVie() + this.valSoin);
        System.out.println(p.getNom() + " a recupere " + this.valSoin + " points de vie.");
    }
    
    /**
     * 
     * @return 
     */
    public int getValSoin() {
        return valSoin;
    }
    
    /**
     * 
     * @param valSoin 
     */
    public void setValSoin(int valSoin) {
        this.valSoin = valSoin;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Potion de soin [valeur de soin=" + valSoin + "]";
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    @Override
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.valSoin;
    }
}
