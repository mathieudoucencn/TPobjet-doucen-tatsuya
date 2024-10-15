package md.tp.poo;

import java.util.Random;

/**
 * Classe qui représente un archer.
 * @author mathi & woota
 */

public class Archer extends Personnage implements Combattant {

    //attributs
    private int nbFleches;
    
    //méthodes
    /**
     * Constructeur avec paramètres
     *
     * @param nom Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax Distance maximale d'attaque
     * @param p Position
     * @param nbf Nombre de fleches
     *
     */
    public Archer(String nom, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbf) {
        super("Archer",nom, pv, dA, pPar, paAtt, paPar, dMax, p);
        this.nbFleches = nbf;
    }

    /**
     * Constructeur par copie
     * @param a Archer a copier
     *
     */
    public Archer(Archer a) {
        super(a);
        this.nbFleches = a.nbFleches;
    }

    /**
     * Constructeur par defaut
     * 
     */
    public Archer() {
        super("Archer");
    }
    
    /**
     * 
     * @return 
     */
    public int getNbFleches() {
        return nbFleches;
    }
    
    /**
     * 
     * @param nbFleches 
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }
    
    /**
     * combat à distance!
     * @param c 
     */
    @Override
    public void combattre(Creature c) {

        Random alea = new Random();
        double distance = this.getPosition().distance(c.getPosition());

        String n = null;
        if (c instanceof Personnage) {
            Personnage p = (Personnage) c;
            n = p.getNom();
        } else if (c instanceof Monstre) {
            n = c.getTypeNom();
        }

        if (distance <= 1) {
            super.combattre(c);
        } else if (distance < this.distAttMax) {

            if (this.getNbFleches() > 0) {
                nbFleches--;

                int randAtt = alea.nextInt(100) + 1;

                if (randAtt <= this.pageAtt) {

                    int randDef = alea.nextInt(100) + 1;
                    int damage;
                    if (randDef > c.getPagePar()) {
                        damage = this.degAtt;
                    } else {
                        damage = this.degAtt - c.getPtPar();
                    }

                    c.setPtVie(c.getPtVie() - damage);
                    System.out.println(this.nom + "--->" + n + " : " + damage + "damage ");
                } else {
                    System.out.println(this.nom + "--->" + n + " : missed shooting ");
                }
            } else {
                System.out.println(this.nom + "--->" + n + " : no arrows to shoot");
            }

        } else {
            System.out.println(this.nom + "--->" + n + " : too far to attack");
        }

    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    @Override
    public String getTexteSauvegarde() {
        return  this.typeNom + " " + this.nom + " " + this.ptVie + " " + this.degAtt + " " + this.ptPar + " " + this.pageAtt
                + " " + this.pagePar + " " + this.distAttMax + " " + this.position.getX() + " " + this.position.getY() + " " + this.getNbFleches();
    }

}
