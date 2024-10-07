/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */

import java.util.ArrayList;

public class Inventaire {
    private final ArrayList<Utilisable> items;

    public Inventaire() {
        items = new ArrayList<>();
    }

    public void ajouterObjet(Utilisable u) {
        items.add(u);
    }

    public void retirerObjet(Utilisable u) {
        items.remove(u);
    }

    public ArrayList<Utilisable> getItems() {
        return items;
    }

    public void afficherInventaire() {
        int index = 1;
        for (Utilisable u : items) {
            System.out.println(index + " - " + u.toString());
            index++;
        }
    }
}