/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

import java.util.Random;

/**
 *
 * @author woota
 */
/**
 * Classe qui représente un nuage toxique.
 */
public class NuageToxique extends Objet implements Deplacable, Combattant {

    private int pageAtt;
    private int degAtt;

    public NuageToxique(Point2D p, int pageAtt, int degAtt) {
        super(" NuageToxique", p);
        this.pageAtt = pageAtt;
        this.degAtt = degAtt;
    }

    public NuageToxique(NuageToxique n) {
        super(n);
        this.pageAtt = n.pageAtt;
        this.degAtt = n.degAtt;
    }

    public NuageToxique() {
        super();
    }

    @Override
    public void combattre(Creature c) {
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
            } else {
                c.setPtVie(0);
            }
            System.out.println("Nuage toxique attaque la creature : " + n);
        } else {
            System.out.println("nuage --->" + n + " : missed attack");
        }
    }

    @Override
    public void deplace(World world
    ) {

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

    @Override
    public void utiliser(Personnage p) {

        System.out.println("Nuage toxique affecte " + p.getNom());
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    
}
