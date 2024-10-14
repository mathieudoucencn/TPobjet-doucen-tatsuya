/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */

/**
 * Classe qui représente une potion de soin.
 */
public class PotionSoin extends Objet implements Utilisable {

    private int valSoin;

    /**
     * Constructeur avec position et valeur de soin.
     * @param p Position de la potion
     * @param val Valeur de soin
     */
    public PotionSoin(Point2D p, int val) {
        super("PotionSoin",p);
        this.valSoin = val;
    }

    public PotionSoin(PotionSoin ps) {
        super(ps);
        this.valSoin = ps.valSoin;
    }

    public PotionSoin() {
        super("PotionSoin");
        this.valSoin = 20; 
    }

    @Override
    public void utiliser(Personnage p) {
        p.setPtVie(p.getPtVie() + this.valSoin);
        System.out.println(p.getNom() + " a recupere " + this.valSoin + " points de vie.");
    }

    public int getValSoin() {
        return valSoin;
    }

    public void setValSoin(int valSoin) {
        this.valSoin = valSoin;
    }

    @Override
    public String toString() {
        return "Potion de soin [valeur de soin=" + valSoin + "]";
    }
    
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.valSoin;
    }
}