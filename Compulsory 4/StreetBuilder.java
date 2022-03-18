public class StreetBuilder {
    private String lhIntersection, rhIntersection;
    private Street buildingStreet;

    public StreetBuilder(String name, int length, String lhIntersection, String rhIntersection) {
        this.lhIntersection = lhIntersection;
        this.rhIntersection = rhIntersection;
        this.buildingStreet = new Street(name, length);
    }

    public String getLhIntersection() {
        return lhIntersection;
    }

    public String getRhIntersection() {
        return rhIntersection;
    }

    Street buildStreet(Intersection lhIntersection, Intersection rhIntersection)
    {
        buildingStreet.setLhIntersection(lhIntersection);
        buildingStreet.setLhIntersection(rhIntersection);
        return buildingStreet;
    }
}
