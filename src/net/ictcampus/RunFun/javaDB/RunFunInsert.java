package net.ictcampus.RunFun.javaDB;

//~~~ Imports ~~~
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RunFunInsert implements InsertPlayer {

    // ~~~ Instanzvariablen ~~~
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    // ~~~ Methoden ~~~
    @Override
    public boolean insertPlayerDB(String name, String zeit) {
        // SQL Querie
        String insert = "Insert into highscore (username, time, map_id) values (?,?,?);";

        try {
            // Verbindung aufbauen
            con = openConnection();

            // Statement ausführbar machen
            ps = con.prepareStatement(insert);

            // Werte anbinden
            ps.setString(1, name);
            ps.setString(2, zeit);
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
        return false;
    }

    @Override
    public List<Player> selectPlayerDB() {
        return null;
    }
}