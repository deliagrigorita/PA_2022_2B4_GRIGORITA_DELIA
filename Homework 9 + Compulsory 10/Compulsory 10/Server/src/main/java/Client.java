import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private int logat;
    public List<String> friends = new ArrayList<>();

    public Client() {
        this.logat = 0;
    }

    public Client(String name) {
        this.name = name;
        this.logat = 0;
    }

    public int getLogat() {
        return logat;
    }

    public void setLogat(int logat) {
        this.logat = logat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

