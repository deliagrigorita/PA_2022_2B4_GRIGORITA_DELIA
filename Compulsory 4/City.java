import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class City {
    private List<Street> streets;
    private Set<Intersection> intersections;

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
}
