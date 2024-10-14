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
 * Classe qui représente une epée
 */
public class Epee extends Objet implements Utilisable {

    private int duree;
    private int valDam;

    /**
     * Constructeur avec position et valeur de soin.
     *
     * @param p Position de la potion
     * @param valDam
     * @param duree
     */
    public Epee(Point2D p, int valDam, int duree) {
        super("Epee", p);
        this.valDam = valDam;
        this.duree = duree;
    }

    public Epee(Epee e) {
        super(e);
        this.valDam = e.valDam;
        this.duree = e.duree;
    }

    public Epee() {
        super("Epee");
    }

    @Override
    public void utiliser(Personnage p) {

        if (p instanceof Guerrier) {
            ((Guerrier) p).setBonusArme(this.valDam);
        } else {
            System.out.println("ce personnage ne sait pas utiliser cette arme...");
        }
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getValDam() {
        return valDam;
    }

    public void setValDam(int valDam) {
        this.valDam = valDam;
    }

}
