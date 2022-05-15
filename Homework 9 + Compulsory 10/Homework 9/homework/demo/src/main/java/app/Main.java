package app;
import app.entities.CitiesEntity;
import app.entities.ContinentsEntity;
import app.entities.CountriesEntity;
import app.repository.CityJPARepository;
import app.repository.ContinentRepo;
import app.repository.CountryRepo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    public static void main(final String[] args) throws FileNotFoundException {
        long start=System.currentTimeMillis();
        readData();
        long end=System.currentTimeMillis();
        System.out.println(end-start + " time");

       /** Properties property=new Properties();
        property.setProperty("population","newValue");
        System.out.println(property.getProperty("population"));*/

    }

    public static void readData() throws FileNotFoundException {
        Path pathToFile = Paths.get("src/main/resources/concap.csv");
        try(BufferedReader br= Files.newBufferedReader(pathToFile, StandardCharsets.ISO_8859_1)){
            String line =br.readLine();
            while((line= br.readLine()) != null){
                String[] txt= line.split(",");
                String id= addContinent(txt);
                addCountry(txt,id);
                addCities(txt,id);
                line=br.readLine();
            }
        }
        catch (IOException v){
            v.printStackTrace();
        }
        catch (SQLException t){
            t.printStackTrace();
        }
    }

    private static void addCities(String[] data, String id) {
        CityJPARepository cityRepo= new CityJPARepository();
        CitiesEntity city=new CitiesEntity();
        //city.setName(data[1]);
        //city.setId(data[0]);
        //cityRepo.save(city);
    }

    private static void addCountry(String[] data, String id) throws  SQLException{
        CountriesEntity countries=new CountriesEntity();
        //countries.setName(data[4]);
        //countries.setId(data[0]);
        CountryRepo countryRepo= new CountryRepo();
        //countryRepo.save(countries);

        CityJPARepository cityRepo= new CityJPARepository();
        CitiesEntity city=new CitiesEntity();
        city.setName(data[1]);
        city.setId(String.valueOf(countryRepo.findByName(data[0])));

    }

    private static String addContinent(String[] data) throws  SQLException{
        ContinentsEntity continent=new ContinentsEntity();
        //continent.setId(data[3]);
        //continent.setName(data[5]);
        ContinentRepo continentRepo= new ContinentRepo();
        //continentRepo.save(continent);
        return String.valueOf(continentRepo.findByName(data[5]));
    }

}
