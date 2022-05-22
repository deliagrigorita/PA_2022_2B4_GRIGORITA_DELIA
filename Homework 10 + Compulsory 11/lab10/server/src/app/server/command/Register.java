package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Register implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread thread;

    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Register(Socket socket, NetworkServer server, Thread thread) {
        this.socket = socket;
        this.server = server;
        this.thread = thread;
    }

    @Override
    public String execute(String[] command) {
        for(Client user : server.getClients())
            if(user.getThread() == this.thread && user.isConnected()){
                answer = "You are already logged-in!";
                checkConnected = true;
            }
        if(checkConnected)
            return answer;

        if(command.length == 2) {
            if (server.getClientName().contains(command[1])) { //check if name is already used
                answer = "Name " + command[1] + " is already registered! Please login or register with another name.";
            } else {
                server.getClients().add(new Client(command[1]));
                server.getClientName().add(command[1]);
                answer = "Client " + command[1] + " is registered successfully!";
            }
        }
        else{
            answer = "Wrong command... Type 'register user'";
        }
        return answer;
    }
}
