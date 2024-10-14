package md.tp.poo;

import java.util.Random;

public class Monstre extends Creature {

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
     *
     * @param n
     */
    public Monstre(String n) {
        super(n);
    }

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

        } while (world.outside(newPos) || world.creaEstOccupee(newPos) || world.objEstOccupee(newPos) || (newPos == world.getJouer().getPersonnage().getPosition()));

        this.getPosition().translate(dx, dy);
    }

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
                System.out.println(this.typeNom + "--->" + n + " : " + damage + "damage ");
            } else {
                System.out.println(this.typeNom + "--->" + n + " : missed attack");
            }

        } else if (distance > 1) {
            System.out.println(this.typeNom + "--->" + n + " : too far to attack");
        }

    }
    
    public String getTexteSauvegarde() {
        return  this.typeNom + " " + this.ptVie + " " + this.degAtt + " " + this.ptPar + " " + this.pageAtt+
               " " + this.pagePar + " " + this.position.getX() + " " + this.position.getY();
    }
}
