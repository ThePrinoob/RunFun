package javaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import application.Main;
import application.Person;
import javaDB.ConnectionFactory;
import view.GameViewManager;


public class RunFunInsert implements insertPlayer {

    // Instanzvariablen
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
   
    
    @Override
    public boolean insertPlayerDB(String name) {
        // SQL Querie
        String insert = "Insert into highscore (username, time, map_id) values (?,?,?);";
        
        try {
            // Verbindung aufbauen
            con = openConnection();

            // Statement ausführbar machen
            ps = con.prepareStatement(insert);

            // Werte anbinden
            ps.setString(1, name);
            ps.setString(2, (GameViewManager.getZeit() / 100 / 60) + ":" + ((GameViewManager.getZeit() / 100) % 60) + "."
                    + (GameViewManager.getZeit() % 100) / 10);
            ps.setInt(3, 1);

            // Ausführen des Queries
            ps.executeUpdate();

            // Verbindugn schliessen
            closeConnection();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    // Methode, welche die Connection zur Datenbank aufbaut
    private Connection openConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    // Methode, welche die Datenbankverbindung schliesst
    private void closeConnection() {
        try {

            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error in " + getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public boolean updatePlayerDB(String name, int maxPunkte) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Person> selectPlayerDB() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public boolean updatePlayerDB(String name, int maxPunkte) {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public List<Person> selectPlayerDB() {
//        return null;
//        // TODO Auto-generated method stub
//    }

}