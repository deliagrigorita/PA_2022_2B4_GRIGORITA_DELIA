import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {//static=poate fi apelat fara obiect
        Main program = new Main(); //am instantiat main ul(clasa), program=obiectul
        program.homework(); //am apelat functia homework
        //o functie este o bucata de cod care poate fi executata
        //o clasa are mai multe functii si mai multe date membre
        //o clasa e o schema pt un obiect

    }

    public void homework() { //o functie, exista si functii care pot fi apelate si fara obiecte
        Faker faker = new Faker();
        City city = new City();
        String streetName1 = faker.address().streetName();
        String streetName2 = faker.address().streetName();

        Intersection[] nodes = IntStream.rangeClosed(0, 8).mapToObj(i -> new Intersection("v" + i, city))//am creat un array(vector) de intersectii
                .toArray(Intersection[]::new);
        Street s1 = new Street(streetName1, 2,nodes [0], nodes[1]);
        Street s2 = new Street(streetName2, 2,nodes [1], nodes[2]);
        city.addStreet(s1);
        city.addStreet(s2);

        System.out.println(nodes[1].getNumberOfIntersections());
    }

}


    
    


    


