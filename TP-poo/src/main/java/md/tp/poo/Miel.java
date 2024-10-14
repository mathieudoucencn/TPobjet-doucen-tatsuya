/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */
public class Miel extends Nourriture{
    
    /**
     * Constructeur avec position, effet, durée et caractéristique.
     *
     * @param p Position du miel.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
   public Miel(Point2D p, int effet, int duree, String caract) {
        super("Miel", p, effet, duree, caract); 
    }

    /**
     * Constructeur par copie.
     *
     * @param m Miel à copier.
     */
    public Miel(Miel m) {
        super(m); 
    }
    /**
     * Constructeur par défaut.
     */
    public Miel() {
        super("Miel");  
    }
    
}
