/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author woota
 */

/**
 * Classe qui gère un joueur humain dans le jeu.
 * Le joueur peut choisir son personnage, se déplacer, combattre et utiliser des objets.
 */
public class Jouer {
    private String nom;
    private Personnage personnage;
    private Inventaire inventaire;
    private Point2D pt;
    
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

    public Point2D getPt() {
        return pt;
    }

    public void setPt(Point2D pt) {
        this.pt = pt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Constructors
    public Jouer(Personnage p) {
        this.personnage = p;
        this.inventaire = new Inventaire();
        
        Random rand = new Random();

        int x = rand.nextInt(World.getNum());
        int y = rand.nextInt(World.getNum());

        Point2D newPos = new Point2D(x, y);
        this.personnage.setPos(newPos);
    }

    public Jouer() {
        this.inventaire = new Inventaire();
        this.personnage = new Personnage();
        
        Random rand = new Random();

        int x = rand.nextInt(World.getNum());
        int y = rand.nextInt(World.getNum());

        Point2D newPos = new Point2D(x, y);
        this.personnage.setPos(newPos);
        
    }

   
    
    // Method to allow a player to take a turn
    public void tour(World world) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Que voulez-vous faire ?");
        System.out.println("1 - Se deplacer");
        System.out.println("2 - Combattre");
        System.out.println("3 - Utiliser un objet");

        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entrée invalide.");
            return;
        }

        switch (choix) {
            case 1:
                deplace(world);
                break;
            case 2:
                choisirCibleEtCombattre();
                break;
            case 3:
                utiliserObjet();
                break;
            default:
                System.out.println("Choix invalide.");
        }

        
        personnage.mettreAJourEffets();
    }


    // Method to allow a player to choose their character
    public Personnage choix(World world) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Quel personnage voulez-vous choisir ?");
        System.out.println("Archer: A, Guerrier: G");

       

        String choix = null;
        try {
            choix = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

       

        switch (choix) {
            case "A":
                Archer archer = new Archer(this.nom, 65, 12, 8, 85, 50, 5, this.personnage.getPos(), 10);
                setPersonnage(archer);
                return archer;
            case "G":
                Guerrier guerrier = new Guerrier(this.nom, 100, 20, 15, 75, 60, 1, this.personnage.getPos());
                setPersonnage(guerrier);
                return guerrier;
            default:
                System.out.println("Choix invalide.");
                return null;
        }
    }

    // Method to handle player movement
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

        Point2D newPos = new Point2D(personnage.getPos());

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
                newPos.translate(1,0);
                break;
            default:
                System.out.println("Direction invalide.");
                return;
        }

        if (!world.outside(newPos) && !world.creaEstOccupee(newPos)) {
            personnage.setPos(newPos);
            System.out.println("Vous vous etes deplace a x:" + newPos.getX()+" y:"+newPos.getY());

           
            Objet obj = world.getObjetAtPosition(newPos);
            if (obj != null){
                if(obj instanceof Utilisable) {
                this.getInventaire().ajouterObjet((Utilisable) obj);
                System.out.println("Vous avez ramasse : " + obj.toString());
                world.removeObjet(obj);
                }
                else if (obj instanceof NuageToxique){
                    ((NuageToxique)obj).combattre(personnage);
                }
            }
        } else {
            System.out.println("Vous ne pouvez pas vous deplacer ici.");
        }
    }

    // Method to choose a target and engage in combat
   public void choisirCibleEtCombattre() {
        // Implémentation pour choisir une cible à attaquer
        Creature cible = null;
        double distanceMin = Double.MAX_VALUE;

        World world = World.getInstance();
        for (Creature c : world.getCreatures()) {
            if (c != personnage) {
                double distance = personnage.getPos().distance(c.getPos());
                if (distance <= personnage.getDistAttMax() && distance < distanceMin) {
                    cible = c;
                    distanceMin = distance;
                }
            }
        }

        if (cible != null) {
            personnage.combattre(cible);
            if (cible.getPtVie() <= 0) {
                System.out.println(cible.getNom() + " a été vaincu !");
                world.removeCreature(cible);
            }
        } else {
            System.out.println("Aucune cible à portée.");
        }
    }

    // Method to use an item from the player's inventory
     public void utiliserObjet() {
        if (inventaire.getItems().isEmpty()) {
            System.out.println("Votre inventaire est vide.");
            return;
        }

        System.out.println("Votre inventaire :");
        inventaire.afficherInventaire();

        System.out.println("Quel objet voulez-vous utiliser ? (entrez le numéro)");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entrée invalide.");
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