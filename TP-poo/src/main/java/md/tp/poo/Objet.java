/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tp.poo;

/**
 *
 * @author woota
 */


/**
 * Classe abstraite qui représente un objet
 */

public abstract class Objet extends ElementDeJeu{
    
    private String objet_id;
    protected Point2D pos;
    
    /**
     * Constructeur avec position
     * @param p Position de l'objet
     */
    public Objet(Point2D p){
        this.pos = new Point2D(p);
    }
    /**
     * Constructeur par copie.
     * @param o Objet à copier.
     */
    public Objet(Objet o) {
        this.pos = new Point2D(o.pos);
    }
    /**
     * Constructeur par défaut
     */
    public Objet(){
        this.pos = new Point2D();
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    public void affiche(){
        
    }
    
    public String getObjet_id() {
        return objet_id;
    }

    public void setObjet_id(String objet_id) {
        this.objet_id = objet_id;
    }

    
    /**
     * Applique l'effet de l'objet sur un personnage
     * @param p Personnage cible
     */
    public abstract void utiliser(Personnage p);
    
    
    /*
    public void saveToDatabase(Connection conn, String sauvegardeId) throws SQLException {
    String objetId = UUID.randomUUID().toString();

    // `objet` テーブルにデータを保存
    String objetSql = "INSERT INTO objet (objet_id, sauvegarde_id, pos_x, pos_y) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(objetSql)) {
        stmt.setString(1, objetId);
        stmt.setString(2, sauvegardeId);
        stmt.setInt(3, this.getPos().getX());
        stmt.setInt(4, this.getPos().getY());
        stmt.executeUpdate();
    }
/*
    // `type_objet` テーブルにタイプを保存
    String typeObjetSql = "INSERT INTO type_objet (type_id, objet_id, type_nom) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(typeObjetSql)) {
        String typeId = UUID.randomUUID().toString();
        stmt.setString(1, typeId);
        stmt.setString(2, objetId);
        stmt.setString(3, this.getClass().getSimpleName());
        stmt.executeUpdate();
    }
}
    */

    
}
