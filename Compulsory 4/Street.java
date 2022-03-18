public class Street {
    private String name;
    private int length;
    private Intersection lhIntersection, rhIntersection;

    public Street(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public Street(String name, int length, Intersection lhIntersection, Intersection rhIntersection) {
        this.name = name;
        this.length = length;
        this.lhIntersection = lhIntersection;
        this.rhIntersection = rhIntersection;
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
