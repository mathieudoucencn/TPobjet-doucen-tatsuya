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
 * Classe abstraite qui représente un objet
 */

public abstract class Objet extends ElementDeJeu{
    
    protected String typeNom;

        
    /**
     * Constructeur avec position.
     *
     * @param n Nom du type d'objet.
     * @param p Position de l'objet.
     */
    public Objet(String n, Point2D p) {
        super(p);  
        this.typeNom = n;
    }

    /**
     * Constructeur par copie.
     *
     * @param o Objet à copier.
     */
    public Objet(Objet o) {
        super(o);  
        this.typeNom = o.typeNom;
    }

    /**
     * Constructeur avec seulement le nom du type d'objet.
     *
     * @param n Nom du type d'objet.
     */
    public Objet(String n) {
        super();  
        this.typeNom = n;
    }

    /**
     * Constructeur par défaut
     */
    public Objet() {
        super();  
    }


    
    public void affiche(){
        
    }
    
    public void removeObjet(World world) {
        world.getObjets().remove(this);
    }
    
    
    /**
     * Applique l'effet de l'objet sur un personnage
     * @param p Personnage cible
     */
    public abstract void utiliser(Personnage p);
    
   
}
