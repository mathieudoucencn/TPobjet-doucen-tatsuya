package md.tp.poo;

/**
 * Classe abstraite qui représente une créature basique.
 * @author woota & mathi
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {
    
    //attributs
    protected String typeNom;
    protected int ptVie;
    protected int degAtt;
    protected int ptPar;
    protected int pageAtt;
    protected int pagePar;

    //Méthodes
    /**
     * Constructeur avec paramètres]
     *
     * @param n nom de type
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position
     */
    public Creature(String n, int pv, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super(p);
        this.typeNom = n;
        this.ptVie = pv;
        this.degAtt = dA;
        this.ptPar = pPar;
        this.pageAtt = paAtt;
        this.pagePar = paPar;
    }

    /**
     * Constructeur par copie
     *
     * @param c Creature a copier
     */
    public Creature(Creature c) {
        super(c);
        this.typeNom = c.typeNom;
        this.ptVie = c.ptVie;
        this.degAtt = c.degAtt;
        this.ptPar = c.ptPar;
        this.pageAtt = c.pageAtt;
        this.pagePar = c.pagePar;
    }
    
    /**
     * Constructeur par défault
     * @param n 
     */
    public Creature(String n) {
        super();
        this.typeNom = n;
    }
    
    /**
     * Constructeur à éviter (sans nom)
     */
    public Creature() {
        super();
        this.typeNom = "default";
    }
    
    /**
     * fonction d'affichage des attributs successivement
     */
    @Override
    public void affiche() {
        System.out.println(this.ptVie + ',' + this.degAtt + ','
                + this.ptPar + ',' + this.pageAtt + ',' + this.pagePar);
        this.getPosition().affiche();
    }
    
    /**
     * 
     * @return 
     */
    public String getTypeNom() {
        return typeNom;
    }
    
    /**
     * 
     * @return 
     */
    public int getPtVie() {
        return ptVie;
    }
    
    /**
     * 
     * @param ptVie 
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }
    
    /**
     * 
     * @return 
     */
    public int getDegAtt() {
        return degAtt;
    }
    
    /**
     * 
     * @param degAtt 
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }
    
    /**
     * 
     * @return 
     */
    public int getPtPar() {
        return ptPar;
    }
    
    /**
     * 
     * @param ptPar 
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }
    
    /**
     * 
     * @return 
     */
    public int getPageAtt() {
        return pageAtt;
    }
    
    /**
     * 
     * @param pageAtt 
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }
    
    /**
     * 
     * @return 
     */
    public int getPagePar() {
        return pagePar;
    }
    
    /**
     * 
     * @param pagePar 
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }
    
    /**
     * 
     * @param world 
     */
    public void removeCreature(World world) {
        world.getCreatures().remove(this);
    }

}
