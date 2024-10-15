package md.tp.poo;

/**
 * Classe qui représente un objet de type Nourriture qui peut donner des bonus ou malus.
 * @author mathi
 */
public class Nourriture extends Objet implements Utilisable {
    
    //attributs
    private int effet;    // Bonus/Malus à appliquer
    private int duree;    // Durée de l'effet
    private String caract;  // Caractéristique affectée (ex: "degAtt", "ptPar")
    
    //méthodes
    /**
     * Constructeur avec paramètres.
     *
     * @param n Nom du type de nourriture (ex: "Eau").
     * @param p Position de la nourriture.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
    public Nourriture(String n, Point2D p, int effet, int duree, String caract) {
        super(n, p);
        this.effet = effet;
        this.duree = duree;
        this.caract = caract;
    }

    /**
     * Constructeur par copie.
     *
     * @param n Nourriture à copier.
     */
    public Nourriture(Nourriture n) {
        super(n);  // コピーコンストラクタ   ??????????????????????????????????????
        this.effet = n.effet;
        this.duree = n.duree;
        this.caract = n.caract;
    }

    /**
     * Constructeur par défaut.
     *
     * @param n
     */
    public Nourriture(String n) {
        super(n);
    }

    /**
     * 
     * @return 
     */
    public int getEffet() {
        return effet;
    }
    
    /**
     * 
     * @param effet 
     */
    public void setEffet(int effet) {
        this.effet = effet;
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
    public String getCaract() {
        return caract;
    }
    
    /**
     * 
     * @param caract 
     */
    public void setCaract(String caract) {
        this.caract = caract;
    }

    /**
     * Utilisation de la Nourriture par un joueur. Applique l'effet sur une
     * caractéristique pendant une durée définie.
     *
     * @param p personnage qui utilise la nourriture.
     */
    @Override
    public void utiliser(Personnage p) {
        System.out.println(p.getNom() + " utilise la nourriture et gagne un effet sur " + this.caract
                + " de " + this.effet + " pour " + this.duree + " tours.");
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Nourriture [effet=" + effet + ", duree=" + duree + ", caract=" + caract + "]";
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    @Override
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.effet + " " + this.duree + " " + this.caract;
    }
}


