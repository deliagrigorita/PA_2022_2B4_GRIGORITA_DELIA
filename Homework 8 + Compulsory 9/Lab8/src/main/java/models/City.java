package models;

import repository.CityDAO;
import repository.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class City {
    private static final CityDAO cityDAO = new CityDAO();
    private int id;
    private Country country;
    private String name;
    private int capital;
    private double latitude;
    private double longitude;

    public City(){}

    public City(Country country, String name, int capital, double latitude, double longitude) {
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(int id, Country country, String name, int capital, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public static City getCity(int id){
        City city = new City();
        try {
            Connection con = Database.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cities where id='" + id + "'");

            if (rs.next()) {
                city.setId(rs.getInt(1));
                city.setCountry(Country.getCountry(rs.getInt(2)));
                city.setName(rs.getString(3));
                city.setCapital(rs.getInt(4));
                city.setLatitude(rs.getDouble(5));
                city.setLongitude(rs.getDouble(6));
            }
            else {
                System.out.println("Nu am gasit orasul.");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return city;
    }
    public void saveCity() {
        try {
            cityDAO.create(this.name, this.country.getId(), this.capital, this.latitude, this.longitude);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
