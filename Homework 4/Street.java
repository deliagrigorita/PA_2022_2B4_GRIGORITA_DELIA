//o strada e definita de 2 intersectii
public class Street {
    private String name; //string--sir de caracter
    private int length; //integer--numere
    private Intersection lhIntersection, rhIntersection;

    public Street(String name, int length) { //o functie constructor =se apeleaza prima data in momentul instantierii unui obiect
        this.name = name;
        this.length = length;
    }

    public Street(String name, int length, Intersection lhIntersection, Intersection rhIntersection) {
        this.name = name;
        this.length = length;
        this.lhIntersection = lhIntersection; //intersectia din stanga
        this.rhIntersection = rhIntersection; //intersectia din dreapta
    }

    public Intersection getLhIntersection() {
        return lhIntersection;
    }

    public void setLhIntersection(Intersection lhIntersection) {
        this.lhIntersection = lhIntersection;
    }

    public Intersection getRhIntersection() {
        return rhIntersection;
    }

    public void setRhIntersection(Intersection rhIntersection) {
        this.rhIntersection = rhIntersection;
    }
}
