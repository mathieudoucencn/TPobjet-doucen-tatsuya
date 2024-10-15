package md.tp.poo;

/**
 * Classe qui représente l'inventaire du joueur
 * @author woota
 */

import java.util.ArrayList;

public class Inventaire {
    
    //attributs
    private final ArrayList<Utilisable> items;
    
    //méthodes
    /**
     * Constructeur par défault
     */
    public Inventaire() {
        items = new ArrayList<>();
    }
    
    /**
     * 
     * @param u 
     */
    public void ajouterObjet(Utilisable u) {
        items.add(u);
    }
    
    /**
     * 
     * @param u 
     */
    public void retirerObjet(Utilisable u) {
        items.remove(u);
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Utilisable> getItems() {
        return items;
    }
    
    /**
     * 
     */
    public void afficherInventaire() {
        int index = 1;
        for (Utilisable u : items) {
            System.out.println(index + " - " + u.toString());
            index++;
        }
    }
    
     
}