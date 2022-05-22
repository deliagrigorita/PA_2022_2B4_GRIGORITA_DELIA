package app.server.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {
    private final String name;
    private Set<Client> friends = new HashSet<>();
    private Thread t;
    private List<String> waitingMessages = new ArrayList<>();
    private boolean connected;

    public Client(String name, Thread t){
        this.name = name;
        this.t = t;
    }

    public Client(String name){
        this.name = name;
        this.connected = false;
        this.t = null;
    }
    public String getName() {
        return name;
    }
    public void setT(Thread t) {
        this.t = t;
    }
    public Set<Client> getFriends() {
        return friends;
    }
    public void setFriends(Set<Client> friends) {
        this.friends = friends;
    }
    public Thread getThread() {
        return t;
    }
    public List<String> getWaitingMessages() {
        return waitingMessages;
    }
    public boolean isConnected() {
        return connected;
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                ", t=" + t +
                ", waitingMessages=" + waitingMessages +
                ", connected=" + connected +
                '}';
    }
}
