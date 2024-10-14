/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package md.tp.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mathi
 */
public class TestWoE {

    public static void main(String[] args) {
        World world = new World();
        
        System.out.println("Voulez vous charger un monde?");
        System.out.println("Oui: 1, Non: 2");
        
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
            case 2:
                world.creationJouer("you");
                world.creerMondeAlea();
                break;
            default:
                break;
        }
        
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
