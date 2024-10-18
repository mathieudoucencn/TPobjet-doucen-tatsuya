package md.tp.poo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui représente un monde, dans lequel se déroule une partie
 * @author mathi & woota
 */

public class World {
    
    //attributs
    private String[][] carte;
    private Joueur joueur;
    private int largeur;
    private int hauteur;
    private final int nbProtagoniste;
    private final int nbObjet;
    private final ArrayList<Creature> creatures;
    private final ArrayList<Objet> objets;
    private final ArrayList<Point2D> positions;
    
    //méthodes
    /**
     * Constructeur par défaut
     */
    public World() {
        this.hauteur = 20;
        this.largeur = 20;
        this.nbProtagoniste = 10;
        this.nbObjet = 20;
        this.creatures = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.positions = new ArrayList<>();
    }
    
    /**
     * pour savoir si une position est occupée par une créature
     * @param p
     * @return 
     */
    public boolean creaEstOccupee(Point2D p) {
        for (Creature c : creatures) {
            if (c.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * pour mettre à jour la liste des positions occupées
     */
    public void updatePos() {
        positions.clear();

        if (joueur != null && joueur.getPersonnage() != null) {
            positions.add(new Point2D(joueur.getPersonnage().getPosition()));
        }
        for (Creature c : creatures) {
            if (joueur == null || c != joueur.getPersonnage()) {
                positions.add(new Point2D(c.getPosition()));
            }
        }
        for (Objet o : objets) {
            positions.add(new Point2D(o.getPosition()));
        }
    }
    
    /**
     * pour savoir si une position est occupée
     * @param p
     * @return 
     */
    public boolean posEstOccupee(Point2D p) {
        for (Point2D pos : positions) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * pour savoir si un objet occupe la position p
     * @param p
     * @return 
     */
    public boolean objEstOccupee(Point2D p) {
        for (Objet o : objets) {
            if (o.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * pour savoir si la position p est à l'exterieur des limites du monde
     * @param p
     * @return 
     */
    public boolean outside(Point2D p) {
        return p.getX() >= hauteur || p.getY() >= largeur || p.getX() < 0 || p.getY() < 0;
    }
    
    /**
     * Retourne l'objet présent à une position donnée
     *
     * @param pos Position à vérifier
     * @return Objet à la position donnée, ou null si aucun objet
     */
    public Objet getObjetAtPosition(Point2D pos) {
        for (Objet o : objets) {
            if (o.getPosition().equals(pos)) {
                return o;
            }
        }
        return null;
    }
    
    /**
     * Pour créer un joueur
     * @param nom 
     */
    public void creationJouer(String nom) {

        Random rand = new Random();

        int x = rand.nextInt(this.getHauteur());
        int y = rand.nextInt(this.getLargeur());

        Point2D newPos = new Point2D(x, y);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Quel personnage voulez-vous choisir ?");
        System.out.println("Archer: 1, Guerrier: 2");

        int choix = 0;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }

        Joueur jouer = new Joueur(nom);
        
        switch (choix) {
            case 1:
                jouer.setPersonnage(new Archer(nom, 65, 12, 8, 85, 50, 5, newPos, 10));
                this.setJouer(jouer);
                break;
            case 2:
                jouer.setPersonnage(new Guerrier(nom, 100, 20, 15, 75, 60, 1, newPos));
                this.setJouer(jouer);
                break;
            default:
                break;
        }
    }

    /**
     * Création aléatoire du monde avec créatures aléatoires et objets idem
     */
    public void creerMondeAlea() {
        Random rand = new Random();
        Point2D pt;

        positions.clear();

        for (int i = 0; i < nbProtagoniste; i++) {
            do {
                int x = rand.nextInt(hauteur);
                int y = rand.nextInt(largeur);
                pt = new Point2D(x, y);
            } while (posEstOccupee(pt));

            positions.add(new Point2D(pt));
            int type = rand.nextInt(5);

            switch (type) {
                case 0:
                    Archer archer = new Archer("R" + i, rand.nextInt(100) + 1, rand.nextInt(20) + 1,
                            rand.nextInt(10) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1,
                            rand.nextInt(5) + 1, pt, rand.nextInt(10) + 5);
                    creatures.add(archer);
                    break;
                case 1:
                    Paysan paysan = new Paysan("P" + i, rand.nextInt(100) + 1, rand.nextInt(10) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, 1, pt);
                    creatures.add(paysan);
                    break;
                case 2:
                    Guerrier guerrier = new Guerrier("G" + i, rand.nextInt(100) + 1, rand.nextInt(20) + 1,
                            rand.nextInt(10) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, 1, pt);
                    creatures.add(guerrier);
                    break;
                case 3:
                    Lapin lapin = new Lapin(rand.nextInt(50) + 1, rand.nextInt(5) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, 1, pt);
                    creatures.add(lapin);
                    break;
                case 4:
                    Loup loup = new Loup(rand.nextInt(50) + 1, rand.nextInt(5) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, 1, pt);
                    creatures.add(loup);
                    break;
            }
        }

        for (int i = 0; i < nbObjet; i++) {
            do {
                int x = rand.nextInt(hauteur);
                int y = rand.nextInt(largeur);
                pt = new Point2D(x, y);
            } while (posEstOccupee(pt));

            positions.add(new Point2D(pt));

            int objType = rand.nextInt(4);
            switch (objType) {
                case 0:
                    objets.add(new PotionSoin(pt, 20));
                    break;
                case 1:
                    int dureeEpee = rand.nextInt(3) + 1;
                    objets.add(new Epee(pt, 20, dureeEpee));
                    break;
                case 2:
                    int bonusOrMalus = rand.nextInt(2);
                    int dureeEpinard = rand.nextInt(3) + 1;
                    int effet = rand.nextInt(20) + 10;
                    if (bonusOrMalus == 0) {
                        Eau eau = new Eau(pt, effet, dureeEpinard, "degAtt");// Bonus
                        objets.add(eau);
                    } else {
                        Eau eau = new Eau(pt, -effet, dureeEpinard, "degAtt");//Malus
                        objets.add(eau);
                    }
                    break;
                case 3:
                    objets.add(new NuageToxique(pt, 30, 20));
                    break;
            }
        }
    }
    
    /**
     * fonction d'affichage du monde (a defaut d'avoir codé une interface graphique)
     */
    public void afficheMonde() {
        carte = new String[hauteur][largeur];

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                carte[i][j] = "-";
            }
        }

        for (Creature c : creatures) {
            if (c != joueur.getPersonnage()) {
                int x = c.getPosition().getX();
                int y = c.getPosition().getY();

                if (c instanceof Archer) {
                    carte[x][y] = "A";
                } else if (c instanceof Paysan) {
                    carte[x][y] = "P";
                } else if (c instanceof Lapin) {
                    carte[x][y] = "B";
                } else if (c instanceof Loup) {
                    carte[x][y] = "L";
                } else if (c instanceof Guerrier) {
                    carte[x][y] = "G";
                }
            }

        }
        
        carte[joueur.getPersonnage().getPosition().getX()][joueur.getPersonnage().getPosition().getY()] = "J";

        for (Objet o : objets) {
            int x = o.getPosition().getX();
            int y = o.getPosition().getY();

            if (o instanceof Epee) {
                carte[x][y] = "E";
            } else if (o instanceof PotionSoin) {
                carte[x][y] = "S";
            } else if (o instanceof Eau) {
                carte[x][y] = "W";
            } else if (o instanceof NuageToxique) {
                carte[x][y] = "%";
            }
        }
        
       
        System.out.println("----------------------------CARACTERISTIQUES-------------------------------");
        System.out.println("OBJETS  --->  S:PotionSoin, E:Epee, W:Eau, %:NuageToxique ");
        System.out.println("PERSONNAGES  --->  A:Archer, G:Guerrier, P:Paysan");
        System.out.println("MONSTRES  --->  B:Lapin, L:Loup");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        
        System.out.println("----------------------------"+ joueur.getPersonnage().getNom() + "-----------------------------------");
        System.out.println("your position: " + joueur.getPersonnage().getPosition().getY() + "," + joueur.getPersonnage().getPosition().getX());
        System.out.println("your remaining life points: " + joueur.getPersonnage().getPtVie());
        System.out.println("-------------------------------------------------------------------------------");

         

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(carte[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * fonction pour effectuer un tour de jeu. Une partie est une succession de tours.
     */
    public void tourDeJeu() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Que voulez-vous faire ?");
        System.out.println("1 - Se deplacer");
        System.out.println("2 - Combattre");
        System.out.println("3 - Utiliser un objet");
        System.out.println("4 - Quitter et sauvegarder");

        int choix;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }

        switch (choix) {
            case 1:
                joueur.deplace(this);
                break;
            case 2:
                joueur.choisirCibleEtCombattre(this);
                break;
            case 3:
                joueur.utiliserObjet();
                break;
            case 4:
                String input = null;
                System.out.println("sous quel nom?");
                try {
                    input = (br.readLine());
                } catch (IOException e) {
                    System.out.println("Entree invalide.");
                    return;
                }
                this.sauvegarderPartie(input);
                this.getJouer().getPersonnage().setPtVie(-1);
                return;
            default:
                System.out.println("Choix invalide.");
                break;
        }
        joueur.getPersonnage().mettreAJourEffets();
    }
    
    /**
     * fonction de déplacement aléatoire de toutes les entités non gérées par le joueur
     */
    public void deplace() {

        for (Creature c1 : creatures) {
            if (joueur == null || c1 != joueur.getPersonnage()) {
                c1.deplace(this);
            }
        }

        for (Objet o : objets) {
            if (o instanceof NuageToxique) {
                ((NuageToxique) o).deplace(this);
            }
        }
        updatePos();
    }
    
    /**
     * fonction de sauvegarde d'une partie dans un fichier texte
     * @param nomFichier 
     */
    public void sauvegarderPartie(String nomFichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichier))) {
            writer.write("Largeur " + this.largeur + "\n");
            writer.write("Hauteur " + this.hauteur + "\n");

            for (Creature c : creatures) {
                if (c instanceof Personnage) {
                    Personnage p = (Personnage) c;
                    writer.write(p.getTexteSauvegarde() + "\n");
                } else if (c instanceof Monstre) {
                    Monstre m = (Monstre) c;
                    writer.write(m.getTexteSauvegarde() + "\n");
                }

            }
            for (Objet objet : objets) {
                if (objet instanceof NuageToxique) {
                    NuageToxique n = (NuageToxique) objet;
                    writer.write(n.getTexteSauvegarde() + "\n");
                } else if (objet instanceof Epee) {
                    Epee e = (Epee) objet;
                    writer.write(e.getTexteSauvegarde() + "\n");
                } else if (objet instanceof Epee) {
                    Epee e = (Epee) objet;
                    writer.write(e.getTexteSauvegarde() + "\n");
                } else if (objet instanceof PotionSoin) {
                    PotionSoin p = (PotionSoin) objet;
                    writer.write(p.getTexteSauvegarde() + "\n");
                } else if (objet instanceof Eau) {
                    Eau e = (Eau) objet;
                    writer.write(e.getTexteSauvegarde() + "\n");
                }

            }

            if (joueur != null && joueur.getPersonnage() != null) {
                writer.write("Jouer" + " " + joueur.getPersonnage().getNom() + " " + joueur.getPersonnage().getTexteSauvegarde() + "\n");

                for (Utilisable o : joueur.getInventaire().getItems()) {
                    if (o instanceof NuageToxique) {
                        NuageToxique n = (NuageToxique) o;
                        writer.write(n.getTexteSauvegarde() + "\n");
                    } else if (o instanceof Epee) {
                        Epee e = (Epee) o;
                        writer.write(e.getTexteSauvegarde() + "\n");
                    } else if (o instanceof Epee) {
                        Epee e = (Epee) o;
                        writer.write(e.getTexteSauvegarde() + "\n");
                    } else if (o instanceof PotionSoin) {
                        PotionSoin p = (PotionSoin) o;
                        writer.write(p.getTexteSauvegarde() + "\n");
                    } else if (o instanceof Eau) {
                        Eau e = (Eau) o;
                        writer.write(e.getTexteSauvegarde() + "\n");
                    }
                }
            }
            System.out.println("Partie sauvegardee dans " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * fonction de chargement d'une sauvegarde à partir d'un fichier texte
     * @param nomFichier 
     */
    public void chargementPartie(String nomFichier) {
        FileReader file = null;
        try {
            file = new FileReader(nomFichier);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader reader = new BufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer token = new StringTokenizer(line);
                if (token.hasMoreTokens()) {
                    String type = token.nextToken();

                    switch (type) {
                        case "Largeur":
                            this.largeur = Integer.parseInt(token.nextToken());
                            break;
                        case "Hauteur":
                            this.hauteur = Integer.parseInt(token.nextToken());
                            break;
                        case "Guerrier":
                        case "Paysan":
                        case "Archer":
                            Personnage perso = creerPersonnage(type, token);
                            creatures.add(perso);
                            break;
                        case "Loup":
                        case "Lapin":
                            Monstre monstre = creerMonstre(type, token);
                            creatures.add(monstre);
                            break;
                        case "NuageToxique":
                        case "PotionSoin":
                        case "Epee":
                        case "Eau":
                        case "Poisson":
                        case "Miel":
                            Objet objet = creerObjet(type, token);
                            objets.add(objet);
                            break;
                        case "Jouer":
                            String nom = token.nextToken();
                            Joueur jouer = new Joueur(nom);
                            this.setJouer(jouer);
                            jouer.setPersonnage(creerJoueur(token));
                            break;
                        case "Inventaire":
                            Utilisable item = (Utilisable) creerObjet(token.nextToken(), token);
                            this.joueur.getInventaire().getItems().add(item);
                            break;
                        default:
                            System.out.println("Type inconnu : " + type);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * fonction pour enregistrer un personnage dans un fichier texte
     * @param type
     * @param token
     * @return 
     */
    private Personnage creerPersonnage(String type, StringTokenizer token) {
        String nom = token.nextToken();
        int ptVie = Integer.parseInt(token.nextToken());
        int degAtt = Integer.parseInt(token.nextToken());
        int ptParade = Integer.parseInt(token.nextToken());
        int pageAtt = Integer.parseInt(token.nextToken());
        int pagePar = Integer.parseInt(token.nextToken());
        int distAttMax = Integer.parseInt(token.nextToken());
        int posX = Integer.parseInt(token.nextToken());
        int posY = Integer.parseInt(token.nextToken());

        Point2D newPos = new Point2D(posX, posY);

        switch (type) {
            case "Guerrier":
                return new Guerrier(nom, ptVie, degAtt, ptParade, pageAtt, pagePar, distAttMax, newPos);
            case "Archer":
                int nbFleche = Integer.parseInt(token.nextToken());
                return new Archer(nom, ptVie, degAtt, ptParade, pageAtt, pagePar, distAttMax, newPos, nbFleche);
            case "Paysan":
                return new Paysan(nom, ptVie, degAtt, ptParade, pageAtt, pagePar, distAttMax, newPos);
            default:
                return null;
        }
    }
    
    /**
     * fonction pour enregistrer un monstre dans un fichier texte
     * @param type
     * @param token
     * @return 
     */
    private Monstre creerMonstre(String type, StringTokenizer token) {
        int ptVie = Integer.parseInt(token.nextToken());
        int degAtt = Integer.parseInt(token.nextToken());
        int ptParade = Integer.parseInt(token.nextToken());
        int pageAtt = Integer.parseInt(token.nextToken());
        int pageParade = Integer.parseInt(token.nextToken());
        int posX = Integer.parseInt(token.nextToken());
        int posY = Integer.parseInt(token.nextToken());

        Point2D newPos = new Point2D(posX, posY);

        switch (type) {
            case "Loup":
                return new Loup(ptVie, degAtt, ptParade, pageAtt, pageParade, newPos);
            case "Lapin":
                return new Lapin(ptVie, degAtt, ptParade, pageAtt, pageParade, newPos);
            default:
                return null;
        }
    }
    
    /**
     * fonction pour enregistrer un objet dans un fichier texte
     * @param type
     * @param token
     * @return 
     */
    private Objet creerObjet(String type, StringTokenizer tokenizer) {
        int posX = Integer.parseInt(tokenizer.nextToken());
        int posY = Integer.parseInt(tokenizer.nextToken());
        int val1; int val2;
        Point2D position = new Point2D(posX, posY);

        switch (type) {
            case "NuageToxique":
                val1 = Integer.parseInt(tokenizer.nextToken());
                val2 = Integer.parseInt(tokenizer.nextToken());
                return new NuageToxique(position, val1, val2);
            case "PotionSoin":
                val1 = Integer.parseInt(tokenizer.nextToken());
                return new PotionSoin(position, val1);
            case "Epee":
                val1 = Integer.parseInt(tokenizer.nextToken());
                return new Epee(position, val1, 2);
            case "Poisson":
                val1 = Integer.parseInt(tokenizer.nextToken());
                return new Poisson(position, val1, 2, "ptVie");
            case "Miel":
                val1 = Integer.parseInt(tokenizer.nextToken());
                return new Miel(position, val1, 2, "ptVie");
            case "Eau":
                val1 = Integer.parseInt(tokenizer.nextToken());
                return new Eau(position, val1, 2, "ptVie");
            default:
                return null;
        }
    }
    
    /**
     * fonction pour enregistrer le joueur dans un fichier texte
     * @param type
     * @param token
     * @return 
     */
    private Personnage creerJoueur(StringTokenizer token) {
        String typeNom = token.nextToken();
        String nom = token.nextToken();
        int ptVie = Integer.parseInt(token.nextToken());
        int degAtt = Integer.parseInt(token.nextToken());
        int ptParade = Integer.parseInt(token.nextToken());
        int pageAtt = Integer.parseInt(token.nextToken());
        int pagePar = Integer.parseInt(token.nextToken());
        int distAttMax = Integer.parseInt(token.nextToken());
        int posX = Integer.parseInt(token.nextToken());
        int posY = Integer.parseInt(token.nextToken());

        Point2D newPos = new Point2D(posX, posY);

        switch (typeNom) {
            case "Guerrier":
                return new Guerrier(nom, ptVie, degAtt, ptParade, pageAtt, pagePar, distAttMax, newPos);
            case "Archer":
                int nbFleche = Integer.parseInt(token.nextToken());
                return new Archer(nom, ptVie, degAtt, ptParade, pageAtt, pagePar, distAttMax, newPos, nbFleche);
            default:
                return null;
        }
    }
    
    /**
     * 
     * @return 
     */
    public int getLargeur() {
        return largeur;
    }
    
    /**
     * 
     * @param largeur 
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    
    /**
     * 
     * @return 
     */
    public int getHauteur() {
        return hauteur;
    }
    
    /**
     * 
     * @param hauteur 
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    
    /**
     * 
     * @return 
     */
    public Joueur getJouer() {
        return joueur;
    }
    
    /**
     * 
     * @param jouer 
     */
    public void setJouer(Joueur jouer) {
        this.joueur = jouer;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Objet> getObjets() {
        return objets;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Point2D> getPositions() {
        return positions;
    }
    
    /**
     * 
     * @return 
     */
    public String[][] getCarte() {
        return this.carte;
    }
}
