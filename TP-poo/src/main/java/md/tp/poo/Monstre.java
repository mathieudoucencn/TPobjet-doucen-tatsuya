package md.tp.poo;

import java.util.Random;

public class Monstre extends Creature {
    
    private String monstre_id;
    
    //Méthodes
    
    /**
     * Constructeur avec paramètres
     * @param n Nom de la créature
     * @param pv Points de vie
     * @param dA Dégâts d'attaque
     * @param pPar Points de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position
     */
    
    
    
    public Monstre(String n, int pv, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(n, pv, dA, pPar, paAtt, paPar, p);
    }
    
    /**
     * Constructeur par copie
     * @param m Monstre a copier
     **/
    public Monstre(Monstre m){
        super(m);
    }
    
     /**
     * Constructeur par defaut
     * 
     **/
    public Monstre(){
    
    }

    public String getMonstre_id() {
        return monstre_id;
    }

    public void setMonstre_id(String monstre_id) {
        this.monstre_id = monstre_id;
    }
    
    
    
    
    @Override
    public void deplace(){
        
        Random rand = new Random();
        
        int dx,dy;
        
        World world = World.getInstance();
        Point2D newPos;

        do{
            
            dx = rand.nextInt(3)-1;
            dy = rand.nextInt(3)-1;
            
            int newX = pos.getX() + dx;
            int newY = pos.getY() + dy;
             
            newPos = new Point2D(newX,newY);  
            
        
        }while(world.outside(newPos) || world.creaEstOccupee(newPos)||world.objEstOccupee(newPos)||(newPos == world.getJouer().getPt()));
        
      
        pos.translate(dx,dy);   
      
    }
    
    @Override
    public void combattre(Creature c) {
        
        Random alea = new Random();
        double distance = pos.distance(c.getPos());
        
        if(distance <= 1){
            
            int randAtt = alea.nextInt(100) + 1;
        
            if(randAtt <= this.pageAtt){
            
                int randDef = alea.nextInt(100) + 1;
                int damage;
                if(randDef >  c.getPagePar()){
                    damage = this.degAtt;     
                }
                else{
                    damage = this.degAtt - c.getPtPar();
                }
                
            c.setPtVie(c.getPtVie()- damage);
            System.out.println(this.nom + "--->" + c.getNom() + " : " + damage + "damage ");
            }
            
            else{
                System.out.println(this.nom + "--->" + c.getNom()+ " : missed attack");
            }
          
        }
        else if (distance > this.distAttMax){
            System.out.println(this.nom + "--->" + c.getNom()+ " : too far to attack");
        }
        
        
        
        
    }
/*
    public void saveToDatabase(Connection conn) throws SQLException {
    // `monstre` 
    String monstreSql = "INSERT INTO monstre (monstre_id, creature_id, pt_vie, page_att, pt_degat, page_parade) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(monstreSql)) {
        stmt.setString(1, this.getMonstreId());
        stmt.setString(2, this.getCreatureId());
        stmt.setInt(3, this.getPtVie());
        stmt.setInt(4, this.getPageAtt());
        stmt.setInt(5, this.getPtDegat());
        stmt.setInt(6, this.getPagePar());
        stmt.executeUpdate();
    }
}
    public static Monstre loadFromDatabase(Connection conn, String creatureId) throws SQLException {
    String sql = "SELECT m.* FROM monstre m WHERE m.creature_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, creatureId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Monstre monstre = new Monstre();
            monstre.setMonstreId(rs.getString("monstre_id"));
            monstre.setCreatureId(rs.getString("creature_id"));
            monstre.setPtVie(rs.getInt("pt_vie"));
            monstre.setPageAtt(rs.getInt("page_att"));
            monstre.setPtDegat(rs.getInt("pt_degat"));
            monstre.setPagePar(rs.getInt("page_parade"));
            return monstre;
        }
    }
    return null;
}

*/

}
