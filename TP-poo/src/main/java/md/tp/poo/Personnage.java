package md.tp.poo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui decrit un personnage basique
 * 
 * @author mathi
 */
public class Personnage extends Creature {
    
    private String perssonage_id;
    private int dMax;
    private ArrayList<Utilisable> effetsActifs;
    private Inventaire inventaire;

    /**
     * Constructeur avec paramètres
     * @param n Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax Distance maximale d'attaque
     * @param p Position
     */
    public Personnage(String n, int pv, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super(n, pv, dA, pPar, paAtt, paPar, p);
        this.dMax = dMax;
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
    }

    /**
     * Constructeur par copie
     * @param p Personnage à copier
     **/
    public Personnage(Personnage p) {
        super(p);
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
    }

     /**
     * Constructeur par défaut
     */
    
    public Personnage(){
        super();
        this.effetsActifs = new ArrayList<>();
        this.inventaire = new Inventaire();
    }


    public ArrayList<Utilisable> getEffetsActifs() {
        return effetsActifs;
    }

    public void setEffetsActifs(ArrayList<Utilisable> effetsActifs) {
        this.effetsActifs = effetsActifs;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        this.inventaire = inventaire;
    }
    
    
    
    /**
     * Déplacement du personnage.
     */
    @Override
    public void deplace() {
        Random rand = new Random();
        int dx, dy;
        World world = World.getInstance();
        Point2D newPos;

        do {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
            int newX = pos.getX() + dx;
            int newY = pos.getY() + dy;
            newPos = new Point2D(newX, newY);
        } while (world.outside(newPos) || world.creaEstOccupee(newPos) || (newPos.equals(world.getJouer().getPersonnage().getPos())));

        Objet obj = world.getObjetAtPosition(newPos);

        if (obj != null) {
            if (obj instanceof NuageToxique) {
                ((NuageToxique) obj).combattre(this);
            } else if (obj instanceof Utilisable) {
               
                Utilisable item = (Utilisable) obj;
                item.utiliser(this);
                ajouterEffetActif(item);
                System.out.println(this.nom + " a utilisé : " + obj.toString());
                world.removeObjet(obj);
            }
        }
        
        pos.translate(dx,dy);
      
    }

    public void ajouterEffetActif(Utilisable u) {
        effetsActifs.add(u);
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

    public void mettreAJourEffets() {
        ArrayList<Utilisable> effetsASupprimer = new ArrayList<>();
        for (Utilisable u : effetsActifs) {
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
                    System.out.println("L'effet de l'epee sur " + this.nom + " s'est terminé.");
                }
            }
           
        }
        effetsActifs.removeAll(effetsASupprimer);
    }
    
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
     * @param c La créature cible.
     */
    @Override
    public void combattre(Creature c) {
        Random alea = new Random();
        double distance = pos.distance(c.getPos());

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
                System.out.println(this.nom + "--->" + c.getNom() + " : " + damage + " damage ");
            } else {
                System.out.println(this.nom + "--->" + c.getNom() + " : missed attack");
            }
        } else if (distance > this.distAttMax) {
            System.out.println(this.nom + "--->" + c.getNom() + " : too far to attack");
        }
    }
    
    /*
    public void saveToDatabase(Connection conn) throws SQLException {
    String personnageSql = "INSERT INTO personnage (personnage_id, creature_id, pt_vie, page_att, pt_parade, dis_min, nb_fleche, pt_degat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(personnageSql)) {
        stmt.setString(1, this.getPersonnageId());
        stmt.setString(2, this.getCreatureId());
        stmt.setInt(3, this.getPtVie());
        stmt.setInt(4, this.getPageAtt());
        stmt.setInt(5, this.getPtPar());
        stmt.setInt(6, this.getDistAttMax());
        stmt.setInt(7, this.getNbFleche());
        stmt.setInt(8, this.getPtDegat());
        stmt.executeUpdate();
    }
    
    public static Personnage loadFromDatabase(Connection conn, String creatureId) throws SQLException {
    String sql = "SELECT p.* FROM personnage p WHERE p.creature_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, creatureId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Personnage personnage = new Personnage();
            personnage.setPersonnageId(rs.getString("personnage_id"));
            personnage.setCreatureId(rs.getString("creature_id"));
            personnage.setPtVie(rs.getInt("pt_vie"));
            personnage.setPageAtt(rs.getInt("page_att"));
            personnage.setPtPar(rs.getInt("pt_parade"));
            personnage.setDisMin(rs.getInt("dis_min"));
            personnage.setNbFleche(rs.getInt("nb_fleche"));
            personnage.setPtDegat(rs.getInt("pt_degat"));
           
            return personnage;
        }
    }
    return null;
}
    */
    
}