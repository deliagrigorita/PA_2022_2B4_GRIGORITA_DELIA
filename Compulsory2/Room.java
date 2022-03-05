enum roomType {lab, LectureHall}

public class Room {
    private String Name;
    private int Capacity;
    private roomType type;



    public Room( String Name, int Capacity, String type) {
        this.Name=Name;
        this.Capacity=Capacity;
        this.type=roomType.valueOf(type);
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setCapacity(int capacity) {
        this.Capacity = capacity;
    }

    public void settype(String type) {
        this.type = roomType.valueOf(type);
    }

    String getName() {
        return this.Name;
    }
}
