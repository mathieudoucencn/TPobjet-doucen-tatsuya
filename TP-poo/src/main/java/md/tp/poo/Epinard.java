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
 * Classe Epinard, un type spécifique de Nourriture qui donne un bonus sur degAtt.
 */

public class Epinard extends Nourriture {
    
        /**
     * Constructeur avec position, effet, durée et caractéristique.
     * @param p Position de l'épinard.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
    public Epinard(Point2D p, int effet, int duree, String caract) {
        super(p, effet, duree, caract);  
    }
    
    /**
     * Constructeur par copie.
     * @param e Epinard à copier.
     */
    public Epinard(Epinard e) {
        super(e);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Epinard() {
        super();
    }
}
