package net.ictcampus.RunFun.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ictcampus.RunFun.Player;
import net.ictcampus.RunFun.domain.ConnectionFactory;

public class RunFunJBDCDao implements RunFunDao{

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    
    public Player findPlayerByName(String name) {
        return null;
    }
    

    public List<Player> findAllPlayers() {
        List<Player> all = new ArrayList<>();
        
        String sql = "Select * from Person";
        try {
            con = openConnection();
        
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("ID_Person");
            all.add(new Player(rs.getString("name"), rs.getInt("geschwindigkeit"), rs.getString("item"), rs.getInt("score")));
        }
        closeConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return all;
        
    }
    private Connection openConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }
    
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
          // TODO Replace by logger
          System.err.println("Error in " + getClass().getName() + ": "
              + e.getMessage());
        }
    }
}
