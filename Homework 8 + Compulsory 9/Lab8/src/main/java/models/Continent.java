package models;

import repository.ContinentDAO;
import repository.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Continent {
    private static final ContinentDAO continentDAO = new ContinentDAO();
    private int id;
    private String name;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public Continent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Continent getContinent(int id) {
        Continent continent = new Continent();
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from continents where id='" + id + "'");

            if (rs.next()) {
                continent.setId(rs.getInt(1));
                continent.setName(rs.getString(2));
            } else {
                System.out.println("Nu am gasit continent.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return continent;
    }

    public void saveContinent() {
        try {
            continentDAO.create(this.name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
