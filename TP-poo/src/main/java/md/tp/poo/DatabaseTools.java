package md.tp.poo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class DatabaseTools {
/*
    public static Connection connect() {
        Properties props = new Properties();
        try {
            String currentDir = new File(".").getCanonicalPath();
            String propertiesFilePath = currentDir + File.separator + "database.properties";

            File propertiesFile = new File(propertiesFilePath);
            if (!propertiesFile.exists()) {
                System.out.println("there is no file: " + propertiesFilePath);
                return null;
            }

            FileInputStream in = new FileInputStream(propertiesFile);
            props.load(in);
            in.close();

            String login = props.getProperty("login");
            String password = props.getProperty("password");
            String server = props.getProperty("server");
            String database = props.getProperty("database");

            String url = "jdbc:postgresql://" + server + "/" + database;
            Connection connection = DriverManager.getConnection(url, login, password);
            return connection;

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   public static void registerPlayer(String codename, String password) {
    Connection conn = connect();
    if (conn == null) {
        return;
    }

    String sql = "INSERT INTO jouer (codename, login, password) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, codename);
        stmt.setString(2, codename); 
        stmt.setString(3, password);

        stmt.executeUpdate();
        System.out.println("succeed making character");
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
    }
}

   public static boolean authenticatePlayer(String login, String password) {
        Connection conn = connect();
        if (conn == null) {
            return false;
        }

        String sql = "SELECT codename FROM jouer WHERE login = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.close();
                conn.close();
                return true;
            } else {
                rs.close();
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        }
    }
   
   public static void listSavedGames(String codename) {
        Connection conn = connect();
        if (conn == null) {
            return;
        }

        String sql = "SELECT sauvegarde_nom FROM sauvegarder WHERE codename = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codename);

            ResultSet rs = stmt.executeQuery();
            System.out.println("saved game:");
            while (rs.next()) {
                String saveName = rs.getString("sauvegarde_nom");
                System.out.println("- " + saveName);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    

    public static Integer getPlayerID(String login, String password) {
        Connection conn = connect();
        if (conn == null) {
            return null;
        }

        String sql = "SELECT codename FROM jouer WHERE login = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String codename = rs.getString("codename");
                rs.close();
                conn.close();
                return codename.hashCode();
            } else {
                rs.close();
                conn.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try { 
                conn.close(); 
            } catch (SQLException ex) { 
                ex.printStackTrace(); 
            }
            return null;
        }
    }
    
    
    
    
    
    /**
     * 
     * @param conn
     * @param sauvegardeId
     * @param monde
     * @throws SQLException 
     */
    
    /*
    public static void loadCreaturesFromDatabase(Connection conn, String sauvegardeId, World monde) throws SQLException {
    String sql = "SELECT sc.creature_id, sc.pos_x, sc.pos_y, c.type FROM sauvegarde sc JOIN creature c ON sc.creature_id = c.creature_id WHERE sc.sauvegarde_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, sauvegardeId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String creatureId = rs.getString("creature_id");
            int posX = rs.getInt("pos_x");
            int posY = rs.getInt("pos_y");
            String type = rs.getString("type_id"); 

            Creature creature = null;
            if ("Personnage".equals(type)) {
                creature = Personnage.loadFromDatabase(conn, creatureId);
            } else if ("Monstre".equals(type)) {
                creature = Monstre.loadFromDatabase(conn, creatureId);
            }

            if (creature != null) {
                creature.setPos(new Point2D(posX, posY));
                monde.getCreatures().add(creature);
            }
        }
    }
}

    
     public static void saveWorld(String codename,String nomPartie, String sauvegardeNom, World monde) {
    Connection conn = connect();
    if (conn == null) {
        return;
    }

    try {
        conn.setAutoCommit(false);

        String partieId = UUID.randomUUID().toString();

        String partieSql = "INSERT INTO partie (partie_id, largeur, longueur, partie_nom) VALUES (?, ?, ?, ?)";
        try (PreparedStatement partieStmt = conn.prepareStatement(partieSql)) {
            partieStmt.setString(1, partieId);
            partieStmt.setInt(2, World.getNum());
            partieStmt.setInt(3, World.getNum());
            partieStmt.setString(4, nomPartie);
            partieStmt.executeUpdate();
        }

        String sauvegardeId = UUID.randomUUID().toString();
        
        String sauvegarderSql = "INSERT INTO sauvegarder (sauvegarde_id, partie_id, codename, sauvegarde_nom, date, sauvegarde_rapide) VALUES (?, ?, ?, ?, CURRENT_DATE, ?)";
        try (PreparedStatement sauvegarderStmt = conn.prepareStatement(sauvegarderSql)) {
            sauvegarderStmt.setString(1, sauvegardeId);
            sauvegarderStmt.setString(2, partieId);
            sauvegarderStmt.setString(3, codename);
            sauvegarderStmt.setString(4, sauvegardeNom);
            sauvegarderStmt.setBoolean(5, false);
            sauvegarderStmt.executeUpdate();
        }
        
        for (Creature creature : monde.getCreatures()) {
            creature.saveToDatabase(conn, sauvegardeId, monde);
        }
        
        for (Personnage personnage : monde.getPersonnages()) {
            personnage.saveToDatabase(conn, sauvegardeId, monde);
        }

        for (Objet objet : monde.getObjets()) {
            objet.saveToDatabase(conn, sauvegardeId, monde);
        }

        conn.commit();
        System.out.println("succeed saving world");

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
    public static void readWorld(String codename, String sauvegardeNom, World monde) {
    Connection conn = connect();
    if (conn == null) {
        return;
    }

    try {
        conn.setAutoCommit(false);

        String sauvegardeId = null;
        String sauvegarderSql = "SELECT sauvegarde_id, partie_id FROM sauvegarder WHERE codename = ? AND sauvegarde_nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sauvegarderSql)) {
            stmt.setString(1, codename);
            stmt.setString(2, sauvegardeNom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                sauvegardeId = rs.getString("sauvegarde_id");
                String partieId = rs.getString("partie_id");

                String partieSql = "SELECT largeur, longueur, partie_nom FROM partie WHERE partie_id = ?";
                try (PreparedStatement partieStmt = conn.prepareStatement(partieSql)) {
                    partieStmt.setString(1, partieId);
                    ResultSet partieRs = partieStmt.executeQuery();
                    if (partieRs.next()) {
                        int width = partieRs.getInt("largeur");
                        int height = partieRs.getInt("longueur");
                        String partieNom = partieRs.getString("partie_nom");
                        monde.initializeWorld(width, height, partieNom);
                    }
                }

                Creature.loadCreaturesFromDatabase(conn, sauvegardeId, monde);

                Objet.loadObjetsFromDatabase(conn, sauvegardeId, monde);

                monde.setLoaded(true);
                System.out.println("succeed loading savedata.");
            } else {
                System.out.println("does not exist");
            }
        }

        conn.commit();

    } catch (SQLException e) {
        e.printStackTrace();
        try {
            conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
   
    

   public static void removeWorld(String codename, String sauvegardeNom) {
        Connection conn = connect();
        if (conn == null) {
            return;
        }

        String sql = "DELETE FROM sauvegarder WHERE codename = ? AND sauvegarde_nom = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codename);
            stmt.setString(2, sauvegardeNom);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("succeed deleting savedata");
            } else {
                System.out.println("there is no such savedata");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
   }
        
   private static String mondeToJson(World monde) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(monde);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
   
    private static void jsonToMonde(String mondeData, World monde) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            World loadedWorld = mapper.readValue(mondeData, World.class);
            monde.copyFrom(loadedWorld);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}


