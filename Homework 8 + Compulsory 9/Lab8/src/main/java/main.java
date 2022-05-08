import factories.CityFactory;
import math.Distance;
import models.City;
import models.Continent;
import repository.*;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            Database.getConnection().commit();
             var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            countries.create("Romania", europeId);
            countries.create("Ukraine", europeId);
            countries.create("Germany", europeId);

            Database.getConnection().commit();

            var cities = new CityDAO();
            cities.create("Berlin",countries.findByName("Germany"), 1,51.1657, 10.4515 );


            Database.getConnection().commit();
            System.out.println(cities.findByName("Berlin"));
            City orasulMeu = City.getCity(cities.findByName("Berlin"));
            System.out.println(orasulMeu.getName());
            System.out.println(orasulMeu.getLatitude());
            System.out.println(orasulMeu.getLongitude());
            System.out.println(orasulMeu.getCountry().getName());
            //System.out.println(orasulMeu.getCountry().getContinent().getName());

            CityFactory.buildFromCsv();

            var dist = Distance.getDistanceFromLatLonInKm(City.getCity(cities.findByName("Hargeisa")).getLatitude(),
                    City.getCity(cities.findByName("Hargeisa")).getLongitude(),
                    City.getCity(cities.findByName("Jerusalem")).getLatitude(),
                    City.getCity(cities.findByName("Jerusalem")).getLongitude());

            System.out.println(dist);


//TODO: print all the countries in Europe

            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}