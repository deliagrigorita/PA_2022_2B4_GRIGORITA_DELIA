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

        Intersection[] nodes = IntStream.rangeClosed(0, 8).mapToObj(i -> new Intersection("v" + i, city))//am creat un array(vector) de intersectii
                .toArray(Intersection[]::new);
        Street[] strazi = new Street[7];
        strazi [0] = new Street(faker.address().streetName(), 2,nodes [0], nodes[1]);
        strazi [1] = new Street(faker.address().streetName(), 2,nodes [1], nodes[2]);
        strazi [2] = new Street(faker.address().streetName(), 2,nodes [1], nodes[3]);
        strazi [3] = new Street(faker.address().streetName(), 2,nodes [3], nodes[4]);
        strazi [4] = new Street(faker.address().streetName(), 7,nodes [3], nodes[5]);
        strazi [5] = new Street(faker.address().streetName(), 8,nodes [3], nodes[6]);
        strazi [6] = new Street(faker.address().streetName(), 9,nodes [3], nodes[7]);

        for (var strada:strazi){
            city.addStreet(strada);
        }

       List<Street> straziFiltrate=
               Arrays.stream(strazi).filter(street -> street.getLenght() > 5 && street.getNumberOfIntersectingStreets() > 3 ).collect(Collectors.toList());//am transformat un Array intr-un stream

        for (var strada:straziFiltrate) {
            System.out.println(strada.getName()+" "+strada.getLenght());//am concatenat 3 siruri de caractere
        }
        //System.out.println(nodes[1].getNumberOfIntersections());
        //System.out.println(strazi[6].getNumberOfIntersectingStreets());
    }

}


    
    


    


