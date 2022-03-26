import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
//clasa care descrie orasul
public class City {
    private List<Street> streets; //o lista de strazi
    private Set<Intersection> intersections;//o multime/set de intersectii  -- o multime are elemente unice

    public City() {
        streets = new LinkedList<>();
        intersections = new HashSet<>();
    }

    public void addStreet(Street street)
    {
        streets.add(street);
        intersections.add(street.getLhIntersection());
        intersections.add(street.getRhIntersection());
    }


    public int getIntersectingStreets(Intersection intersection) {//functia asta cauta din toate strazile, strazile care au intersecti dr sau stg egale cu parametrul 'intersection'
        int number = 0; //am declarat variabila 'number' egala cu 0, adica nu am nicio strada care se intersecteaza cu intersectia mea
        for (var street : this.streets) // un foreach="pentru toate din ceva"
        {
            if (street.getLhIntersection().equals(intersection) || (street.getRhIntersection().equals(intersection))){
                number = number + 1; //incrementeaza numarul de strazi care se intersecteaza cu "intersection"
            }

        }
        return number;

    }
}
