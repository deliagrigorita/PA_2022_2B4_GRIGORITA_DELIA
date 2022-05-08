package repository;

import java.sql.*;

public class CountryDAO {

    public void create(String name, int continentId) throws SQLException {
        Connection con = Database.getConnection();
        try{
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into countries (name, code, continent) values (?, ?, ?)");
            pstmt.setString(1, name.replaceAll("'", "''"));
            pstmt.setString(2, name.substring(0, 2));
            pstmt.setInt(3, continentId);
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Integer findByName(String name) throws SQLException{
        Connection con = Database.getConnection();
        try(
                Statement stmt = con.createStatement();
                ResultSet rs =
                        stmt.executeQuery("select id from countries where name='" + name.replaceAll("'", "''") + "'")
        ) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (
                Statement stmt = con.createStatement();
                ResultSet rs =
                        stmt.executeQuery("select id from countries where id='" + id + "'")
        ) {
            return rs.next() ? rs.getString(2) : null;
        }
    }
}
