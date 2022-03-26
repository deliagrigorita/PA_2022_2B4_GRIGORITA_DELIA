public class Intersection {
    private String name;
    private City oras;

    public Intersection(String name, City city) {
        this.name = name;
        this.oras = city;
    } // constructor--se apeleaza in momentul in care se instantiaza obiectul (trebuie sa aiba aacelasi nume ca si clasa si sa nu returneze nimic

    public String getName() {
        return name;
    }

    public int getNumberOfIntersections(){

        return oras.getIntersectingStreets (this);
    }
}
