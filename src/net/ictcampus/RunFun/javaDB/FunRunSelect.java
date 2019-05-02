package net.ictcampus.RunFun.javaDB;
//imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FunRunSelect {

    
 // Instanzvariablen
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<Player> selectPlayerDB() {
        List<Player> allPersonen = new ArrayList<>();

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
                String zeit = rs.getString("time");
                allPersonen.add(new Player(benutzername, zeit));
                
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
        List<Player> player = new ArrayList<>();
        FunRunSelect select = new FunRunSelect();
        player = select.selectPlayerDB();
        // Ausgeben der ausgelesenen Spieler
        for (Player p : player) {
            System.out
                    .println( p.getBenutzername() + p.getTime());
        }
    }
}
