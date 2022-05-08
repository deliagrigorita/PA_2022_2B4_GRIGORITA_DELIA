package models;

import repository.CountryDAO;
import repository.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Country {
    private static final CountryDAO countryDAO = new CountryDAO();
    private int id;
    private String name;
    private String code;
    private Continent continent;

    public Country() {
    }

    public Country(String name, String code, Continent continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    public Country(int id, String name, String code, Continent continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public static Country getCountry(int id) {
        Country country = new Country();
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from countries where id='" + id + "'");

            if (rs.next()) {
                country.setId(rs.getInt(1));
                country.setName(rs.getString(2));
                country.setCode(rs.getString(3));
                country.setContinent(Continent.getContinent(rs.getInt(4)));
            } else {
                System.out.println("Nu am gasit tara.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public void saveCountry() {
        try {
            countryDAO.create(this.name, this.continent.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

