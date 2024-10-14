/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */
public class Loup extends Monstre implements Combattant {


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

    public Loup(int pv, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super("Loup", pv, dA, pPar, paAtt, paPar, p);
    }

    /**
     * Constructeur par copie
     *
     * @param l Lapin a copier
     *
     */
    public Loup(Loup l) {
        super(l);

    }

    /**
     * Constructeur par defaut
     *
     *
     */
    public Loup() {
        super("Loup");

    }

    

}
