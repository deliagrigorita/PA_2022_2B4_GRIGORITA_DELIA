package factories;

import com.mysql.cj.log.NullLogger;
import models.City;
import models.Continent;
import models.Country;
import repository.CityDAO;
import repository.ContinentDAO;
import repository.CountryDAO;
import repository.Database;
import serialization.CsvDeserializer;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CityFactory {
    public static List<City> buildFromCsv() {
        var cities = new ArrayList<City>();

        CityDAO cityDAO = new CityDAO();
        CountryDAO countryDAO = new CountryDAO();
        ContinentDAO continentDAO = new ContinentDAO();

        try {
            var csvContent = CsvDeserializer.deserializerCoordinates(new File(
                    "C:\\Users\\40736\\OneDrive\\Desktop\\PA_anul2\\PA_anul2\\PA_2022_2B4_GRIGORITA_DELIA\\Homework 7 + Compulsory 8\\Lab8\\src\\main\\resources\\capital_coordonates.csv"));

            for (var rowContent : csvContent) {
                Continent continent = null;
                Country country = null;
                City city = null;

                if (continentDAO.findByName(rowContent[5]) == null) {
                    continent = new Continent(rowContent[5]);
                    continent.saveContinent();
                } else {
                    continent = Continent.getContinent(continentDAO.findByName(rowContent[5]));
                }


                if (countryDAO.findByName(rowContent[0]) == null) {
                    country = new Country(rowContent[0], rowContent[4], continent);
                    country.saveCountry();
                } else {
                    country = Country.getCountry(countryDAO.findByName(rowContent[0]));
                }

                if (cityDAO.findByName(rowContent[1]) == null) {
                    city = new City(country, rowContent[1], 1,
                            Double.parseDouble(rowContent[2]),
                            Double.parseDouble(rowContent[3]));
                    city.saveCity();
                }

                cities.add(city);
                Database.getConnection().commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

}
