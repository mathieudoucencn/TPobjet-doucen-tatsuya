/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author woota
 */
public class Jouer {

    private Personnage personnage;
    private Inventaire inventaire;

    // Constructors
    public Jouer(Personnage p) {
        this.personnage = p;
        this.inventaire = new Inventaire();

    }

    public Jouer(String nom) {
        this.inventaire = new Inventaire();
        this.personnage = new Personnage();

    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }

    public void deplace(World world) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Dans quelle direction voulez-vous aller ?");
        System.out.println("Gauche: G, Droite: D, Haut: H, Bas: B");

        String dir = null;
        try {
            dir = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Point2D newPos = new Point2D(personnage.getPosition());

        switch (dir) {
            case "G":
                newPos.translate(0, -1);
                break;
            case "D":
                newPos.translate(0, 1);
                break;
            case "H":
                newPos.translate(-1, 0);
                break;
            case "B":
                newPos.translate(1, 0);
                break;
            default:
                System.out.println("Direction invalide.");
                return;
        }

        if (!world.outside(newPos) && !world.creaEstOccupee(newPos)) {
            personnage.setPosition(newPos);
            Objet obj = world.getObjetAtPosition(newPos);
            if (obj != null) {
                if (obj instanceof Utilisable) {
                    this.getInventaire().ajouterObjet((Utilisable) obj);
                    System.out.println("Vous avez ramasse : " + obj.toString());
                    obj.removeObjet(world);
                } else if (obj instanceof NuageToxique) {
                    ((NuageToxique) obj).combattre(personnage);
                }
            }
            personnage.mettreAJourEffets();

        } else {
            System.out.println("Vous ne pouvez pas vous deplacer ici.");
        }
    }

    public void choisirCibleEtCombattre(World world) {

        Creature cible = null;
        for (Creature c : world.getCreatures()) {
            if (c != personnage) {
                double distance = personnage.getPosition().distance(c.getPosition());
                if (distance <= personnage.getDistAttMax()) {
                    cible = c;
                }
            }
        }
        if (cible != null) {
            String n = null;
            if (cible instanceof Personnage) {
                Personnage p = (Personnage) cible;
                n = p.getNom();
            } else if (cible instanceof Monstre) {
                Monstre m = (Monstre) cible;
                n = m.getTypeNom();
            }
            personnage.combattre(cible);
            if (cible.getPtVie() <= 0) {
                System.out.println(n + "a ete vaincu");
                cible.removeCreature(world);
            }
        } else {
            System.out.println("Aucune cible");
        }
    }

    public void utiliserObjet() {
        if (inventaire.getItems().isEmpty()) {
            System.out.println("Votre inventaire est vide.");
            return;
        }

        System.out.println("Votre inventaire :");
        inventaire.afficherInventaire();

        System.out.println("Quel objet voulez-vous utiliser ? (entrez le numero)");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }

        if (choix < 1 || choix > inventaire.getItems().size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Utilisable u = inventaire.getItems().get(choix - 1);
        u.utiliser(personnage);
        inventaire.retirerObjet(u);
        personnage.ajouterEffetActif(u);
        personnage.mettreAJourEffets();
    }

}
