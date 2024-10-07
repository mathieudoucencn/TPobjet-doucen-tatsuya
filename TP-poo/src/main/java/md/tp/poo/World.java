package md.tp.poo;

import java.util.Random;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/** 
 * Classe qui représente le monde 
 */
public class World {

    private static World instance = null;
    private static String[][] carte;
    private static int num;
    private String nom;
    private final int nbProtagoniste;
    private final int nbObjet;
    private Jouer jouer;
    private Guerrier guer;
    private Archer robin;
    private Paysan peon;
    private Lapin bugs1;
    private Loup bugs2;
    private boolean loaded = false;
    private final ArrayList<Creature> creatures;
    private final ArrayList<Personnage> personnages;
    private final ArrayList<Monstre> monstres;
    private final ArrayList<Objet> objets;
    private final ArrayList<Point2D> positions; 
    

    /**
     * Constructeur privé pour le singleton
     */
    private World() {
        World.num = 10;
        this.nbProtagoniste = 10;
        this.nbObjet = 20;
        this.creatures = new ArrayList<>();
        this.objets = new ArrayList<>();
        this.personnages = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.positions = new ArrayList<>();
    }

    /**
     * Méthode pour obtenir l'instance du monde
     * @return Instance de World
     */
    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }
        return instance;
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public ArrayList<Monstre> getMonstres() {
        return monstres;
    }

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    public ArrayList<Point2D> getPositions() {
        return positions;
    }

    public static String[][] getCarte() {
        return carte;
    }

    public boolean creaEstOccupee(Point2D p) {
        for (Creature c : creatures) {
            if (c.getPos().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public void updatePos() {
        positions.clear();

        if (jouer != null && jouer.getPersonnage() != null) {
            positions.add(new Point2D(jouer.getPersonnage().getPos()));
        }
        for (Creature c : creatures) {
            if (jouer == null || c != jouer.getPersonnage()) {
                positions.add(new Point2D(c.getPos()));
            }
        }
        for (Objet o : objets) {
            positions.add(new Point2D(o.getPos()));
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
            if (o.getPos().equals(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne l'objet présent à une position donnée
     * @param pos Position à vérifier
     * @return Objet à la position donnée, ou null si aucun objet
     */
    public Objet getObjetAtPosition(Point2D pos) {
        for (Objet o : objets) {
            if (o.getPos().equals(pos)) {
                return o;
            }
        }
        return null;
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
                int x = rand.nextInt(num);
                int y = rand.nextInt(num);
                pt = new Point2D(x, y);
            } while (posEstOccupee(pt));

            positions.add(new Point2D(pt)); 
            int type = rand.nextInt(5);

            switch (type) {
                case 0:
                    Archer archer = new Archer("R" + i, rand.nextInt(100) + 1, rand.nextInt(20) + 1,
                            rand.nextInt(10) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1,
                            rand.nextInt(5) + 1, pt, rand.nextInt(10) + 5);
                    personnages.add(archer);
                    creatures.add(archer);
                    break;
                case 1:
                    Paysan paysan = new Paysan("P" + i, rand.nextInt(100) + 1, rand.nextInt(10) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1, 1, pt);
                    personnages.add(paysan);
                    creatures.add(paysan);
                    break;
                case 2:
                    Guerrier guerrier = new Guerrier("G" + i, rand.nextInt(100) + 1, rand.nextInt(20) + 1,
                            rand.nextInt(10) + 1, rand.nextInt(100) + 1, rand.nextInt(100) + 1,1, pt);
                    personnages.add(guerrier);
                    creatures.add(guerrier);
                    break;
                case 3:
                    Lapin lapin = new Lapin("L" + i, rand.nextInt(50) + 1, rand.nextInt(5) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, 1, pt);
                    monstres.add(lapin);
                    creatures.add(lapin);
                    break;
                case 4:
                    Loup loup = new Loup("L" + i, rand.nextInt(50) + 1, rand.nextInt(5) + 1,
                            rand.nextInt(5) + 1, rand.nextInt(100) + 1, 1, pt);
                    monstres.add(loup);
                    creatures.add(loup);
                    break;
            }
        }
        
        
        // Création aléatoire des objets
        for (int i = 0; i < nbObjet; i++) {
            do {
                int x = rand.nextInt(num);
                int y = rand.nextInt(num);
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
                        Epinard epi = new Epinard(pt, effet, dureeEpinard, "degAtt");// Bonus
                        objets.add(epi); 
                    } else {
                        Epinard epi = new Epinard(pt, -effet, dureeEpinard, "degAtt");//Malus
                        objets.add(epi);
                    }
                    break;
                case 3:
                    objets.add(new NuageToxique(pt, 20));
                    break;
            }
        }
    }

    public void removeObjet(Objet o) {
        objets.remove(o);
    }
    public void removeCreature(Creature c) {
        creatures.remove(c);
    }
    
  

    public boolean outside(Point2D p) {
        return p.getX() >= num || p.getY() >= num || p.getX() < 0 || p.getY() < 0;
    }

    public void afficheMonde() {
        carte = new String[num][num];
        
        
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                carte[i][j] = "-";
            }
        }
        
        
        for (Creature c : creatures) {
        if(c != jouer.getPersonnage()){
        int x = c.getPos().getX();
        int y = c.getPos().getY();

        if (c instanceof Archer) carte[x][y] = "A";
        else if (c instanceof Paysan) carte[x][y] = "P";
        else if (c instanceof Lapin) carte[x][y] = "B";
        else if (c instanceof Loup) carte[x][y] = "L";
        else if (c instanceof Guerrier) carte[x][y] = "G";
    }
    else {
        carte[jouer.getPersonnage().getPos().getX()][jouer.getPersonnage().getPos().getY()] = "J";
    }
}

        for (Objet o : objets) {
            int x = o.getPos().getX();
            int y = o.getPos().getY();

            if (o instanceof Epee) carte[x][y] = "E";
            else if (o instanceof PotionSoin) carte[x][y] = "S";
            else if (o instanceof Epinard) carte[x][y] = "E";
            else if (o instanceof NuageToxique) carte[x][y] = "%";
        }
       
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                System.out.print(carte[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void deplace() {
       
        
        for (Creature c1 : creatures) {
            if (jouer == null || c1 != jouer.getPersonnage()) {
                c1.deplace();
            }
        }
            
        for (Creature c2 : creatures) {
            if (c2 instanceof Personnage) {
               ((Personnage) c2).mettreAJourEffets();
           }
        }
        
        for (Objet o  : objets) {
            if (o instanceof NuageToxique) {
               ((NuageToxique)o).deplace();
           }
        }
        updatePos();
    }

    public static int getNum() {
        return num;
    }

    public static void setNum(int Num) {
        World.num = Num;
    }
    
     public Jouer getJouer() {
        return jouer;
    }

    public void setJouer(Jouer jouer) {
        this.jouer = jouer;
    }

    public Guerrier getGuer() {
        return guer;
    }

    public void setGuer(Guerrier guer) {
        this.guer = guer;
    }

    public Archer getRobin() {
        return robin;
    }

    public void setRobin(Archer robin) {
        this.robin = robin;
    }

    public Paysan getPeon() {
        return peon;
    }

    public void setPeon(Paysan peon) {
        this.peon = peon;
    }

    public Lapin getBugs1() {
        return bugs1;
    }

    public void setBugs1(Lapin bugs1) {
        this.bugs1 = bugs1;
    }

    public Loup getBugs2() {
        return bugs2;
    }

    public void setBugs2(Loup bugs2) {
        this.bugs2 = bugs2;
    }

    public Guerrier getGerrier() {
        return guer;
    }

    public void setGuerrier(Guerrier guerrier) {
        this.guer = guerrier;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /*
    public boolean isLoaded() {
        return loaded; 
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public void copyFrom(World other) {
        this.creatures.clear();
        this.creatures.addAll(other.creatures);
        this.personnages.clear();
        this.personnages.addAll(other.personnages);
        this.monstres.clear();
        this.monstres.addAll(other.monstres);
        this.objets.clear();
        this.objets.addAll(other.objets);
        this.positions.clear();
        this.positions.addAll(other.positions);
        this.jouer = other.jouer;
        // Copiez les autres champs si nécessaire
    }
   */
 
}
