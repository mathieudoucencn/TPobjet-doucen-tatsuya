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
 * Classe qui représente le monde
 */
public class World {

    private String[][] carte;
    private Jouer jouer;
    private int largeur;
    private int hauteur;
    private final int nbProtagoniste;
    private final int nbObjet;
    private final ArrayList<Creature> creatures;
    private final ArrayList<Objet> objets;
    private final ArrayList<Point2D> positions;

    /**
     * Constructeur privé pour le singleton
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

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    public ArrayList<Point2D> getPositions() {
        return positions;
    }

    public String[][] getCarte() {
        return this.carte;
    }

    public boolean creaEstOccupee(Point2D p) {
        for (Creature c : creatures) {
            if (c.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void updatePos() {
        positions.clear();

        if (jouer != null && jouer.getPersonnage() != null) {
            positions.add(new Point2D(jouer.getPersonnage().getPosition()));
        }
        for (Creature c : creatures) {
            if (jouer == null || c != jouer.getPersonnage()) {
                positions.add(new Point2D(c.getPosition()));
            }
        }
        for (Objet o : objets) {
            positions.add(new Point2D(o.getPosition()));
        }
    }

    public boolean posEstOccupee(Point2D p) {
        for (Point2D pos : positions) {
            if (pos.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public boolean objEstOccupee(Point2D p) {
        for (Objet o : objets) {
            if (o.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

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
     * Création aléatoire du monde avec créatures et objets
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
                        Eau epi = new Eau(pt, effet, dureeEpinard, "degAtt");// Bonus
                        objets.add(epi);
                    } else {
                        Eau epi = new Eau(pt, -effet, dureeEpinard, "degAtt");//Malus
                        objets.add(epi);
                    }
                    break;
                case 3:
                    objets.add(new NuageToxique(pt, 30, 20));
                    break;
            }
        }
    }

    public void afficheMonde() {
        carte = new String[hauteur][largeur];

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                carte[i][j] = "-";
            }
        }

        for (Creature c : creatures) {
            if (c != jouer.getPersonnage()) {
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
            } else {
                System.out.println("your position: " + jouer.getPersonnage().getPosition().getY() + "," + jouer.getPersonnage().getPosition().getX());
                carte[jouer.getPersonnage().getPosition().getX()][jouer.getPersonnage().getPosition().getY()] = "J";
            }
        }

        for (Objet o : objets) {
            int x = o.getPosition().getX();
            int y = o.getPosition().getY();

            if (o instanceof Epee) {
                carte[x][y] = "E";
            } else if (o instanceof PotionSoin) {
                carte[x][y] = "S";
            } else if (o instanceof Eau) {
                carte[x][y] = "E";
            } else if (o instanceof NuageToxique) {
                carte[x][y] = "%";
            }
        }

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(carte[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void tourDeJeu() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("Que voulez-vous faire ?");
        System.out.println("1 - Se deplacer");
        System.out.println("2 - Combattre");
        System.out.println("3 - Utiliser un objet");

        int choix;
        try {
            choix = Integer.parseInt(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Entree invalide.");
            return;
        }

        switch (choix) {
            case 1:
                jouer.deplace(this);
                break;
            case 2:
                jouer.choisirCibleEtCombattre(this);
                break;
            case 3:
                jouer.utiliserObjet();
                break;
            default:
                System.out.println("Choix invalide.");
                break;
        }
        jouer.getPersonnage().mettreAJourEffets();
    }

    public void deplace() {

        for (Creature c1 : creatures) {
            if (jouer == null || c1 != jouer.getPersonnage()) {
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
                writer.write(objet.getTexteSauvegarde() + "\n");
            }

            if (jouer != null && jouer.getPersonnage() != null) {
                writer.write(jouer.getPersonnage().getNom() + " " + jouer.getPersonnage().getTexteSauvegarde() + "\n");

                for (Utilisable o : jouer.getInventaire().getItems()) {
                    Objet ob = (Objet) o;
                    writer.write(ob.getTexteSauvegarde() + "\n");
                }
            }
            System.out.println("Partie sauvegardee dans " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                        case "Loup":
                        case "Lapin":
                            Monstre monstre = creerMonstre(type, token);
                            creatures.add(monstre);
                            break;
                        case "NuageToxique":
                        case "PotionSoin":
                        case "Epee":
                        case "Poisson":
                        case "Miel":
                            Objet objet = creerObjet(type, token);
                            objets.add(objet);
                            break;
                        case "Joueur":
                            String nom = token.nextToken();
                            Jouer jouer = new Jouer(nom);
                            jouer.setPersonnage(creerJoueur(token));
                            break;
                        case "Inventaire":
                            Utilisable item = (Utilisable) creerObjet(token.nextToken(), token);
                            this.jouer.getInventaire().getItems().add(item);
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

    private Objet creerObjet(String type, StringTokenizer tokenizer) {
        int val1 = Integer.parseInt(tokenizer.nextToken());
        int posX = Integer.parseInt(tokenizer.nextToken());
        int posY = Integer.parseInt(tokenizer.nextToken());

        Point2D position = new Point2D(posX, posY);

        switch (type) {
            case "NuageToxique":
                int val2 = Integer.parseInt(tokenizer.nextToken());
                return new NuageToxique(position, val1, val2);
            case "PotionSoin":
                return new PotionSoin(position, val1);
            case "Epee":
                return new Epee(position, val1, 2);
            case "Poisson":
                return new Poisson(position, val1, 2, "ptVie");

            case "Miel":
                return new Miel(position, val1, 2, "ptVie");

            case "Eau":
                return new Eau(position, val1, 2, "ptVie");
            default:
                return null;
        }
    }

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

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public Jouer getJouer() {
        return jouer;
    }

    public void setJouer(Jouer jouer) {
        this.jouer = jouer;
    }

}
