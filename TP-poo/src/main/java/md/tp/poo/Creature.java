/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {
    
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

    public Creature(String n) {
        super();
        this.typeNom = n;
    }
    
    public Creature() {
        super();
    }

    @Override
    public void affiche() {
        System.out.println(this.ptVie + ',' + this.degAtt + ','
                + this.ptPar + ',' + this.pageAtt + ',' + this.pagePar);
        this.getPosition().affiche();
    }

    public String getTypeNom() {
        return typeNom;
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

    public void removeCreature(World world) {
        world.getCreatures().remove(this);
    }

}
