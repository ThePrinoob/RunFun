package javaDB;
//imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import application.Person;


public class FunRunSelect {

    
 // Instanzvariablen
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Person all;

    public boolean insertPlayerDB(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean updatePlayerDB(String name) {
        // TODO Auto-generated method stub
        return false;
    }
    
    public static void main1(String[] args) {
        
       
        
    }

    public List<Person> selectPlayerDB() {
        List<Person> allPersonen = new ArrayList<>();

        // SQL Querie, welches die
        String sql = "SELECT * FROM highscore ORDER BY time ASC limit 3;";
        try {
            // Verbindung aufbauen
            con = openConnection();

            // Statement vorbereiten, sodass es dann ausgeführt werden kann
            ps = con.prepareStatement(sql);

            // Ausführen des Queries
            rs = ps.executeQuery();

            // Für jede Zeile, welche zurückgegeben wurde wird die ID, der Name, Vorname,
            // sowie der Credit und das Gerburtsdatum
            while (rs.next()) {
                String benutzername = rs.getString("username");
                Time zeit = rs.getTime("time");
                allPersonen.add(new Person(benutzername, zeit));
                
            }
            // Verbindugn schliessen
            closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        return allPersonen;
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

    public static void main(String[] args) {
        List<Person> player = new ArrayList<>();
        FunRunSelect select = new FunRunSelect();
        player = select.selectPlayerDB();
        // Ausgeben der ausgelesenen Spieler
        for (Person p : player) {
            System.out
                    .println( p.getBenutzername() + p.getTime());
        }
    }
}
