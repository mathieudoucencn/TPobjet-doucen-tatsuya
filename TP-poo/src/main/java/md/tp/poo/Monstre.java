package md.tp.poo;

import java.util.Random;

/**
 * Classe qui représente un monstre basique
 * @author mathi & woota
 */

public class Monstre extends Creature implements Combattant {
    
    //attributs

    //Méthodes
    /**
     * Constructeur avec paramètres
     *
     * @param n Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position
     */
    public Monstre(String n, int pv, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super(n, pv, dA, pPar, paAtt, paPar, p);
    }

    /**
     * Constructeur par copie
     *
     * @param m Monstre a copier
     *
     */
    public Monstre(Monstre m) {
        super(m);
    }

    /**
     * Constructeur par defaut
     *
     * @param n
     */
    public Monstre(String n) {
        super(n);
    }
    
    /**
     * fonction de déplacement d'un monstre
     * @param world 
     */
    @Override
    public void deplace(World world) {
        //déplacement aléatoire
        Random rand = new Random();
        int dx = 0; int dy = 0;
        Point2D newPos;
        int alea = rand.nextInt(2);
        
        do {
            //pas de déplacement en diagonale
            if (alea == 1){
                dx = rand.nextInt(3) - 1;
            } else {
                dy = rand.nextInt(3) - 1;
            }
            
            int newX = this.getPosition().getX() + dx;
            int newY = this.getPosition().getY() + dy;

            newPos = new Point2D(newX, newY);

        } while (world.outside(newPos) || world.creaEstOccupee(newPos) || world.objEstOccupee(newPos) || (newPos == world.getJouer().getPersonnage().getPosition()));

        this.getPosition().translate(dx, dy);
    }
    
    /**
     * fonction de combat d'un monstre
     * @param c 
     * @param world 
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
        
        if (distance <= 1) {

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
                if (c.getPtVie() <= 0) {
                    int i = world.getCreatures().indexOf(c);
                    System.out.println("vous avez vaincu " + c.getTypeNom() + " !");
                    world.getCreatures().remove(i);
                }
                System.out.println(this.getTypeNom() + "--->" + n + " : " + damage + "damage ");
                System.out.println(n + " a " + c.getPtVie() + " PV restants");
            } else {
                System.out.println(this.typeNom + "--->" + n + " : missed attack");
            }

        } else if (distance > 1) {
            System.out.println(this.typeNom + "--->" + n + " : too far to attack");
        }

    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    public String getTexteSauvegarde() {
        return  this.typeNom + " " + this.ptVie + " " + this.degAtt + " " + this.ptPar + " " + this.pageAtt+
               " " + this.pagePar + " " + this.position.getX() + " " + this.position.getY();
    }
}
