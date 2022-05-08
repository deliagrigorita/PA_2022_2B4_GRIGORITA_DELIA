package repository;

import java.sql.*;

public class CityDAO {
    public void create(String name, int country, int capital, double latitude, double longitude) throws SQLException {
        Connection con = Database.getConnection();
        try{
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into cities (country, name, capital, latitude, longitude ) values (?, ?, ?, ?, ?)");
            pstmt.setInt(1, country);
            pstmt.setString(2, name.replaceAll("'", "''"));
            pstmt.setInt(3, capital);
            pstmt.setDouble(4, latitude);
            pstmt.setDouble(5, longitude);

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
                        stmt.executeQuery("select id from cities where name='" + name.replaceAll("'", "''") + "'")
        ) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException{
        Connection con = Database.getConnection();
        try(
                Statement stmt = con.createStatement();
                ResultSet rs =
                        stmt.executeQuery("select id from cities where id='" + id + "'")
        ) {
            return rs.next() ? rs.getString(2) : null;
        }
    }
}
