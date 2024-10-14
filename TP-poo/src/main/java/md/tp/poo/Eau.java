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
 * Classe Eau, un type spécifique de Nourriture qui donne un bonus sur degAtt.
 */
public class Eau extends Nourriture {

    /**
     * Constructeur avec position, effet, durée et caractéristique.
     *
     * @param p Position de l'eau.
     * @param effet Valeur de l'effet (positif pour bonus, négatif pour malus).
     * @param duree Nombre de tours pour lesquels l'effet est actif.
     * @param caract La caractéristique modifiée (ex: degAtt, ptPar).
     */
   public Eau(Point2D p, int effet, int duree, String caract) {
        super("Eau", p, effet, duree, caract); 
    }

    /**
     * Constructeur par copie.
     *
     * @param e Eau à copier.
     */
    public Eau(Eau e) {
        super(e); 
    }
    /**
     * Constructeur par défaut.
     */
    public Eau() {
        super("Eau");  
    }
    
    public String getTexteSauvegarde() {
        return this.typeNom + " " + position.getX() + " " + position.getY() + " " + this.getEffet() + " " + this.getDuree() + " " + this.getCaract();
    }
}

