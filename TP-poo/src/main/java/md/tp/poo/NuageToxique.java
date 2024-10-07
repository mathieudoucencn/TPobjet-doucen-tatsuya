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
    
    private int degAtt;

    public NuageToxique(Point2D p, int degAtt) {
        super(p);
    }
    
    public NuageToxique() {
        super();
    }

    @Override
    public void combattre(Creature c) {
        if(c.getPtVie() - degAtt > 0) c.setPtVie(c.getPtVie() - degAtt);
        else  c.setPtVie(0);
        System.out.println("Nuage toxique attaque la creature : " + c.getNom());
    }

    @Override
    public void deplace() {
        
        System.out.println("Nuage toxique se deplace.");
        Random rand = new Random();
        int dx, dy;
        World world = World.getInstance();
        Point2D newPos;

        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
            int newX = pos.getX() + dx;
            int newY = pos.getY() + dy;
            newPos = new Point2D(newX, newY);
        } while (world.outside(newPos));
       
    }

    @Override
    public void utiliser(Personnage p) {
        
        System.out.println("Nuage toxique affecte " + p.getNom());
    }
}
