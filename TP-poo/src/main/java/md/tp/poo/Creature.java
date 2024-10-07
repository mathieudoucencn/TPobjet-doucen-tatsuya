/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */
public abstract class Creature extends ElementDeJeu implements Deplacable{
    
    protected String nom;
    protected int ptVie;
    protected int degAtt;
    protected int ptPar;
    protected int pageAtt;
    protected int pagePar;
    protected int distAttMax;
    protected Point2D pos;
    
    //Méthodes

    /**
     * Constructeur avec paramètres
     * @param n Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position
     */
    public Creature(String n, int pv, int dA, int pPar, int paAtt, int paPar, Point2D p){
        this.nom = n;
        this.ptVie = pv;
        this.degAtt = dA;
        this.ptPar = pPar;
        this.pageAtt = paAtt;
        this.pagePar = paPar;
        this.pos = new Point2D(p);
    }
    /**
     * Constructeur par copie
     * @param c Creature a copier
     */

    public Creature(Creature c){
        nom = c.nom;
        ptVie = c.ptVie;
        degAtt = c.degAtt;
        ptPar = c.ptPar;
        pageAtt = c.pageAtt;
        pagePar = c.pagePar;
        pos = c.pos;
    }
    
    public Creature(){
    }
    
    
    public void affiche(){
        System.out.println(this.nom + ',' + this.ptVie + ',' + this.degAtt + ','
                + this.ptPar + ',' + this.pageAtt + ',' + this.pagePar + ',' +
                this.distAttMax + ',');
        this.pos.affiche();
    }
    
    
    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    /**
     *
     * @return
     */
    public String getNom(){
        return this.nom;
    }
    
    /**
     * Methode abstraite de deplacement
     * 
     */
    @Override
    public abstract void deplace();
    
    
    /**
     * 
     * Methode de combat contre une autre creature.
     * @param c Creature cible 
     */
    public void combattre(Creature c){
    
    
    }   
    
    
}
    
