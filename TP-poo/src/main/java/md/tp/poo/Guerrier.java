/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

import java.util.Random;

/**
 * classe qui reprﾃｩsente un personnage capable de se battre au CaC
 *
 * @author mathi
 */
public class Guerrier extends Personnage implements Combattant {

    private int bonusArme = 0;

    public Guerrier(String nom, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super("Guerrier" ,nom, pv, dA, pPar, paAtt, paPar, dMax, p);
    }

    public Guerrier(Guerrier g) {
        super(g);
        this.bonusArme = g.bonusArme;
    }

    public Guerrier() {
        super("Guerrier");
    }


    public int getBonusArme() {
        return bonusArme;
    }

    public void setBonusArme(int bonusArme) {
        this.bonusArme = bonusArme;
    }

    //combat au corps a corps
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
                System.out.println(this.getNom() + "--->" + n + " : " + damage + "damage ");
            } else {
                System.out.println(this.getNom() + "--->" + n + " : missed attack");
            }
        } else {
            System.out.println(this.getNom() + "--->" + n + " : too far to attack");
        }
    }

}
