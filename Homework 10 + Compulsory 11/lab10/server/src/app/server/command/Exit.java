package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Exit implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread t;

    private Client thisClient = null;
    private boolean verifConn = false;
    private String answer;

    public Exit(Socket socket, NetworkServer server, Thread t) {
        this.socket = socket;
        this.server = server;
        this.t = t;
    }

    @Override
    public String execute(String[] command) {
        answer = "Client stopped!";
        for(Client user : server.getClients()) //verific conexiunea
            if(user.getThread() == this.t && user.isConnected()){
                user.setConnected(false);
                break;
            }
        return answer;
    }
}
