package md.tp.poo;

import java.util.Random;

/**
 * Classe qui représente un nuage toxique.
 * @author woota & mathi
 */

public class NuageToxique extends Objet implements Deplacable, Combattant {
    
    //attributs
    private int pageAtt;
    private int degAtt;
    
    //méthodes
    /**
     * Constructeur par paramètres
     * @param p
     * @param pageAtt
     * @param degAtt 
     */
    public NuageToxique(Point2D p, int pageAtt, int degAtt) {
        super(" NuageToxique", p);
        this.pageAtt = pageAtt;
        this.degAtt = degAtt;
    }
    
    /**
     * Constructeur par copie
     * @param n 
     */
    public NuageToxique(NuageToxique n) {
        super(n);
        this.pageAtt = n.pageAtt;
        this.degAtt = n.degAtt;
    }
    
    /**
     * Constructeur par défault
     */
    public NuageToxique() {
        super();
    }
    
    /**
     * fonction de "combat" d'un nuage toxique
     * @param c 
     */
    @Override
    public void combattre(Creature c, World world) {
        Random alea = new Random();
        int randAtt = alea.nextInt(100) + 1;
        
        String n = null;
        if (c instanceof Personnage) {
            Personnage p = (Personnage) c;
            n = p.getNom();
        } else if (c instanceof Monstre) {
            n = c.getTypeNom();
        }

        if (randAtt <= this.pageAtt) {
            if (c.getPtVie() - degAtt > 0) {
                c.setPtVie(c.getPtVie() - degAtt);
                System.out.println(n + " a " + c.getPtVie() + " PV restants");
            } else {
                c.setPtVie(0);
                int i = world.getCreatures().indexOf(c);
                System.out.println(c.getTypeNom() + " est mort par intoxication");
                world.getCreatures().remove(i);
            }
            System.out.println("Nuage toxique attaque la creature : " + n);
        } else {
            System.out.println("nuage --->" + n + " : missed attack");
        }
    }
    
    /**
     * fonction de déplacement d'un nuage toxique
     * @param world 
     */
    @Override
    public void deplace(World world) {

        Random rand = new Random();
        int dx, dy;
        Point2D newPos;

        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
            int newX = this.getPosition().getX() + dx;
            int newY = this.getPosition().getY() + dy;
            newPos = new Point2D(newX, newY);
        } while (world.outside(newPos));

        this.setPosition(newPos);
    }
    
    /**
     * fonction d'utilisation d'un nuage toxique
     * @param p 
     */
    @Override
    public void utiliser(Personnage p) {

        System.out.println("Nuage toxique affecte " + p.getNom());
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
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    @Override
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.pageAtt + " " + this.degAtt;
    }
    
}
