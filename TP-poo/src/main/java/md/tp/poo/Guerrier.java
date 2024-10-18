package md.tp.poo;

import java.util.Random;

/**
 * classe qui représente un personnage capable de se battre au CaC
 * @author mathi
 */
public class Guerrier extends Personnage implements Combattant {
    
    //attributs
    private int bonusArme = 0;
    
    //méthodes
    /**
     * Constructeur par paramètres
     * @param nom
     * @param pv
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param dMax
     * @param p 
     */
    public Guerrier(String nom, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super("Guerrier" ,nom, pv, dA, pPar, paAtt, paPar, dMax, p);
    }
    
    /**
     * Constructeur par copie
     * @param g 
     */
    public Guerrier(Guerrier g) {
        super(g);
        this.bonusArme = g.bonusArme;
    }
    
    /**
     * Constructeur par default
     */
    public Guerrier() {
        super("Guerrier");
    }
    
    /**
     * 
     * @return 
     */
    public int getBonusArme() {
        return bonusArme;
    }
    
    /**
     * 
     * @param bonusArme 
     */
    public void setBonusArme(int bonusArme) {
        this.bonusArme = bonusArme;
    }

    /**
     * combat au corps a corps
     * @param c 
     */
    @Override
    public void combattre(Creature c, World world) {

        Random alea = new Random();
        double distance = this.getPosition().distance(c.getPosition());

        
        String n = null;
        if (c instanceof Personnage) {
            Personnage p = (Personnage) c;
            n = p.getNom();
        } else if (c instanceof Monstre) {
            n = c.getTypeNom();
        }

        if (distance == 1) {
            int randAtt = alea.nextInt(100) + 1;
            if (randAtt <= this.getPageAtt()) {
                int randDef = alea.nextInt(100) + 1;
                int damage;
                if (randDef > c.getPagePar()) {
                    damage = this.getDegAtt() + this.getBonusArme();
                } else {
                    damage = this.getDegAtt() + this.getBonusArme() - c.getPtPar();
                }
                c.setPtVie(c.getPtVie() - damage);
                if (c.getPtVie() <= 0) {
                    int i = world.getCreatures().indexOf(c);
                    System.out.println("vous avez vaincu " + c.getTypeNom() + " !");
                    world.getCreatures().remove(i);
                }
                System.out.println(this.nom + "--->" + n + " : " + damage + "damage ");
                System.out.println(n + " a " + c.getPtVie() + " PV restants");
            } else {
                System.out.println(this.getNom() + "--->" + n + " : missed attack");
            }
        } else {
            System.out.println(this.getNom() + "--->" + n + " : too far to attack");
        }
    }

}
