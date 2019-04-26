package javaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ictcampus.RunFun.domain.highscore;

public class RunFunJBDCDao implements RunFunDao {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

//    public List<highscore> findhighestscore() {
//        List<highscore> all = new ArrayList<>();
//
//        String sql = "SELECT username, time FROM highscore AS h JOIN maps AS m ON m.id_map=h.ID_highscore ORDER BY time;";
//        try {
//            con = openConnection();
//
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                all.add(new highscore(rs.getString("username"), rs.getTime("time"),
//                        rs.getString("name")));
//            }
//            closeConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return all;
//
//    }

    public void inserthighscore() {
        List<highscore> all = new ArrayList<>();

        String sql = "SELECT username, time, name FROM highscore AS h JOIN maps AS m ON m.id_map=h.ID_highscore ORDER BY time;";
        try {
            con = openConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                all.add(new highscore(rs.getString("username"), rs.getTime("time"),
                        rs.getString("name")));
            }
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        return all;
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
            System.err.println("Error in " + getClass().getName() + ": " + e.getMessage());
        }
    }

}
