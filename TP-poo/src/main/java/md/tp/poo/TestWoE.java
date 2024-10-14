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

        System.out.println("Do you want to load a world? (Yes:1,No:2)");

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }
        if (choix == 1) {
            System.out.println("Enter the file name you want to load.");
            String nomFichier = null;
            try {
                nomFichier = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            world.chargementPartie(nomFichier);
        } else {
            world.creationJouer("you");
            world.creerMondeAlea();
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
            System.out.println("Do you want to quit game? (Yes:1, No:2)");
            int quit = 0;
            try {
                quit = Integer.parseInt(br.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Entree invalide.");
                return;
            }
            if (quit == 1) {
                break;
            }

        }
        System.out.println("Do you want to save game? (Yes:1, No:2)");
        int save = 0;
        try {
            save = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }
        if (save == 1) {
            world.sauvegarderPartie("test");
        }

    }
}
