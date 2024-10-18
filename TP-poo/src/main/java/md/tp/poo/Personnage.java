package md.tp.poo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui decrit un personnage basique
 * @author mathi & woota
 */

public class Personnage extends Creature implements Combattant {
    
    //attributs
    protected String nom;
    protected int distAttMax;
    protected ArrayList<Utilisable> effetsActifs;
    protected Inventaire inventaire;
    
    //méthodes
    /**
     * Constructeur avec paramètres
     *
     * @param n Nom de type
     * @param nom Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax Distance maximale d'attaque
     * @param p Position
     */
    public Personnage(String n, String nom, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super(n, pv, dA, pPar, paAtt, paPar, p);
        this.nom = nom;
        this.distAttMax = dMax;
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
    }

    /**
     * Constructeur par copie
     * @param p Personnage à copier
     *
     */
    public Personnage(Personnage p) {
        super(p);
        this.nom = p.nom;
        this.effetsActifs =  p.getEffetsActifs();
        this.inventaire = p.getInventaire();
    }

    /**
     * Constructeur par défaut avec nom
     * @param n
     */
    public Personnage(String n) {
        super(n);
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
    }
    
    /**
     * Constructeur par défaut
     */
    public Personnage() { 
        super();
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
        this.nom = "default";
    }
    
    /**
     * 
     * @return 
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * 
     * @return 
     */
    public int getDistAttMax() {
        return distAttMax;
    }
    
    /**
     * 
     * @param distAttMax 
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Utilisable> getEffetsActifs() {
        return effetsActifs;
    }
    
    /**
     * 
     * @param effetsActifs 
     */
    public void setEffetsActifs(ArrayList<Utilisable> effetsActifs) {
        this.effetsActifs = effetsActifs;
    }
    
    /**
     * 
     * @return 
     */
    public Inventaire getInventaire() {
        return inventaire;
    }
    
    /**
     * 
     * @param inventaire 
     */
    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
    
    /**
     * 
     */
    @Override
    public void affiche() {
        System.out.println(this.nom + ',' + this.ptVie + ',' + this.degAtt + ','
                + this.ptPar + ',' + this.pageAtt + ',' + this.pagePar + ',' + this.distAttMax);
        this.getPosition().affiche();
    }

    /**
     * fonction de déplacement du personnage.
     *
     * @param world
     */
    @Override
    public void deplace(World world) {
        //déplacement aléatoire
        Random rand = new Random();
        int dx = 0; int dy = 0;
        Point2D newPos;

        do {
            //pas de déplacement en diagonale
            if (rand.nextInt(2) == 1){
                dx = rand.nextInt(3) - 1;
            } else {
                dy = rand.nextInt(3) - 1;
            }
            int newX = this.getPosition().getX() + dx;
            int newY = this.getPosition().getY() + dy;
            newPos = new Point2D(newX, newY);
        } while (world.outside(newPos) || world.creaEstOccupee(newPos) || (newPos.equals(world.getJouer().getPersonnage().getPosition())));

        this.getPosition().translate(dx, dy);
        Objet obj = world.getObjetAtPosition(newPos);

        if (obj != null) {
            if (obj instanceof NuageToxique) {
                ((NuageToxique) obj).combattre(this, world);
            } else if (obj instanceof Utilisable) {

                Utilisable item = (Utilisable) obj;
                item.utiliser(this);
                ajouterEffetActif(item);
                System.out.println(this.nom + " a utilise : " + obj.toString());
                obj.removeObjet(world);
            }
        }

        this.mettreAJourEffets();

    }
    
    /**
     * fonction d'ajout d'effet sur le personnage
     * @param u 
     */
    public void ajouterEffetActif(Utilisable u) {
        this.effetsActifs.add(u);
        if (u instanceof Nourriture) {
            Nourriture n = (Nourriture) u;
            System.out.println(this.nom + " a maintenant un effet actif sur " + n.getCaract() + " pour " + n.getDuree() + " tours.");
        } else if (u instanceof Epee) {
            Epee e = (Epee) u;
            System.out.println(this.nom + " a equipe une epee augmentant " + e.getValDam() + " degats pour " + e.getDuree() + " tours.");
        } else if (u instanceof PotionSoin) {

        } else {
            System.out.println(this.nom + " a utilise ");
        }
    }
    
    /**
     * fonction de mise à jour des effets deja présents sur le personnage
     */
    public void mettreAJourEffets() {
        ArrayList<Utilisable> effetsASupprimer = new ArrayList<>();
        for (Utilisable u : this.effetsActifs) {
            if (u instanceof Nourriture) {
                Nourriture n = (Nourriture) u;
                n.setDuree(n.getDuree() - 1);
                if (n.getDuree() <= 0) {
                    effetsASupprimer.add(n);
                    System.out.println("L'effet de " + n.getCaract() + " sur " + this.nom + " s'est termine.");
                }
            } else if (u instanceof Epee) {

                Epee e = (Epee) u;
                e.setDuree(e.getDuree() - 1);

                if (e.getDuree() <= 0) {
                    effetsASupprimer.add(e);
                    System.out.println("L'effet de l'epee sur " + this.nom + " s'est termine.");
                }
            }

        }
        effetsActifs.removeAll(effetsASupprimer);
    }
    
    /**
     * fonction permettant d'obtenir les degats d'attaque totaux
     * @return 
     */
    public int getEffectiveAtt() {
        int totalDegAtt = degAtt;
        for (Utilisable u : effetsActifs) {
            if (u instanceof Nourriture) {
                Nourriture n = (Nourriture) u;
                if (n.getCaract().equals("degAtt")) {
                    totalDegAtt += n.getEffet();
                }
            } else if (u instanceof Epee) {
                Epee e = (Epee) u;
                totalDegAtt += e.getValDam();
            }
        }
        return totalDegAtt;
    }

    /**
     * Combat contre une autre créature.
     *
     * @param c La créature cible.
     * @param world
     */
    public void combattre(Creature c, World world) {
        Random alea = new Random();
        double distance = this.getPosition().distance(c.getPosition());

        String n = null;
        if (c instanceof Personnage) {
            Personnage p = (Personnage) c;
            n = p.getNom();
        } else if (c instanceof Monstre) {
            n = c.getTypeNom();
        }
        
        if (distance <= 1) {
            int randAtt = alea.nextInt(100) + 1;
            if (randAtt <= this.pageAtt) {
                int randDef = alea.nextInt(100) + 1;
                int damage;
                if (randDef > c.getPagePar()) {
                    damage = this.degAtt;
                } else {
                    damage = this.degAtt - c.getPtPar();
                }
                c.setPtVie(c.getPtVie() - damage);
                if (c.getPtVie() <= 0) {
                    int i = world.getCreatures().indexOf(c);
                    System.out.println("vous avez vaincu " + c.getTypeNom() + " !");
                    world.getCreatures().remove(i);
                }
                System.out.println(this.nom + "--->" + n + " : " + damage + "damage ");
                System.out.println(n + " a " + c.getPtVie() + " PV restants");
            } else {
                System.out.println(this.nom + "--->" + n + " : missed attack");
            }
        } else if (distance > this.distAttMax) {
            System.out.println(this.nom + "--->" + n + " : too far to attack");
        }
    }
    
    /**
     * fonction de retour d'une chaine de caractères décrivant le type et les attributs.
     * @return 
     */
    public String getTexteSauvegarde() {
        return  this.typeNom + " " + this.nom + " " + this.ptVie + " " + this.degAtt + " " + this.ptPar + " " + this.pageAtt
                + " " + this.pagePar + " " + this.distAttMax + " " + this.position.getX() + " " + this.position.getY();
    }
}
