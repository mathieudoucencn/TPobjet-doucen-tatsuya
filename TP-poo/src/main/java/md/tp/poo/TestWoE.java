/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package md.tp.poo;

/**
 *
 * @author mathi
 */
public class TestWoE {

    public static void main(String[] args) {
        World world = World.getInstance();
        world.creerMondeAlea();

        Jouer jouer = new Jouer();
        Personnage perso = jouer.choix(world);
        
        boolean gameOver = false;
        while (!gameOver) {
            world.afficheMonde();
            jouer.tour(world);

            if (perso.getPtVie() <= 0) {
                System.out.println("game over");
                gameOver = true;
                continue;
            }

            world.deplace();
        }

        /*
        Connection conn = DatabaseTools.connect();
        if (conn != null) {
            System.out.println("connection succes");
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("connection fail.");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your login:");
        String login = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = DatabaseTools.authenticatePlayer(login, password);
        String codename = login;

        World world = World.getInstance();
        boolean loaded = false;

        if (!isAuthenticated) {
            System.out.println("Not enregisterd, creating...");

            System.out.print("Enter your codename of your character: ");
            codename = scanner.nextLine();

            DatabaseTools.registerPlayer(codename, password);
            

        
            System.out.println("Creating a new game...");

            System.out.print("Enter the name of the world: ");
            String nomPartie = scanner.nextLine();
            world.setNom(nomPartie);
            System.out.print("Enter the name of the savedata: ");
            String nomSauvegarde = scanner.nextLine();

            world.creerMondeAlea();

            Jouer jouer = new Jouer();
            Personnage perso = jouer.choix(world);
            if (perso == null) {
                System.out.println("failed making a characater");
                return;
            }
            jouer.setNom(codename); 
            world.setJouer(jouer);
            world.getCreatures().add(perso);

            startGame(scanner, world, jouer, perso, codename, nomPartie, nomSauvegarde);

        } else {
            System.out.println("login succes");
            System.out.println("codename: " + codename);

            DatabaseTools.listSavedGames(codename);

            System.out.println("Load saved data? (yes/no) ");
            String loadChoice = scanner.nextLine();
/*
            if (loadChoice.equalsIgnoreCase("yes")) {
                System.out.print("Enter the name of the savedata: ");
                String nomSauvegarde = scanner.nextLine();

                DatabaseTools.readWorld(codename, nomSauvegarde, world);
                if (world.isLoaded()) {
                    System.out.println("savedata loaded.");
                    loaded = true;

                    Jouer jouer = world.getJouer();
                    Personnage perso = jouer.getPersonnage();

                    startGame(scanner, world, jouer, perso, codename, "", nomSauvegarde);

                } else {
                    System.out.println("there is no such savedata. Starting a new game.");
                    loaded = false;
                }
            }

            if (!loaded) {
                System.out.println("Starting new game.");

                System.out.print("Enter the name of the world. ");
                String nomPartie = scanner.nextLine();
                System.out.print("Enter the name of the savedata. ");
                String nomSauvegarde = scanner.nextLine();

                world.creerMondeAlea();

                Jouer jouer = new Jouer();
                Personnage perso = jouer.choix(world);
                if (perso == null) {
                    System.out.println("error making a character.");
                    return;
                }
                world.setJouer(jouer);
                world.getCreatures().add(perso);

                startGame(scanner, world, jouer, perso, codename, nomPartie, nomSauvegarde);
            }
        }

        scanner.close();
            */
    }
            
/*
    private static void startGame(Scanner scanner, World world, Jouer jouer, Personnage perso, String codename, String nomPartie, String nomSauvegarde) {
        boolean gameOver = false;
        while (!gameOver) {
            world.afficheMonde();
            jouer.tour(world);

            if (perso.getPtVie() <= 0) {
                System.out.println("game over");
                gameOver = true;
                continue;
            }

            world.deplace();
        }

            /*
            System.out.print("Save world? (yes/no): ");
            String saveChoice = scanner.nextLine();
            if (saveChoice.equalsIgnoreCase("yes")) {
                DatabaseTools.saveWorld(codename, nomPartie, nomSauvegarde, world);
                System.out.println("World saved.");
            }

            System.out.print("continue playing? (yes/no): ");
            String continueChoice = scanner.nextLine();
            if (continueChoice.equalsIgnoreCase("no")) {
                gameOver = true;
            }
        }

        System.out.print("Do you want to delete world (yes/no): ");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("yes")) {
            DatabaseTools.removeWorld(codename, nomSauvegarde);
            System.out.println("world deleted");
        } else {
            System.out.println("world saved");
        }
    }
*/
}
