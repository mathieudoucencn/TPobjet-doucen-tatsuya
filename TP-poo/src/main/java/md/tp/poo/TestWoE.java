package md.tp.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe principale pour faire tourner le jeu
 * @author mathi & woota
 */
public class TestWoE {

    public static void main(String[] args) {
        //création du monde
        World world = new World();
        
        //chargement ou non
        System.out.println("Voulez vous charger un monde? (méthode de sauvegarde textuelle différente de celle sur hippocampus)");
        System.out.println("Oui: 1, Non: 2");
        
        //récuperation du choix du joueur
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }
        
        switch (choix) {
            //si oui: lequel? et on le charge
            case 1:
                System.out.println("Lequel?");
                String input = null;
                try {
                    input = (br.readLine());
                } catch (IOException e) {
                    System.out.println("Entree invalide.");
                }
                world.chargementPartie(input);
                break;
            //sinon, on en fait un nouveau. (ici aléatoire)
            case 2:
                world.creationJouer("you");
                world.creerMondeAlea();
                break;
            default:
                break;
        }
        
        //boucle de fonctionnement du jeu
        boolean gameOver = false;
        while (!gameOver) {
            world.afficheMonde();
            world.tourDeJeu();
            world.deplace();
            if (world.getJouer().getPersonnage().getPtVie() <= 0) {
                System.out.println("game over");
                gameOver = true;
            }
        }
    }
}
