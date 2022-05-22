package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Login implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread t;
    
    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Login(Socket socket, NetworkServer server, Thread t) {
        this.socket = socket;
        this.server = server;
        this.t = t;
    }

    @Override
    public String execute(String[] command) {
        System.out.println(server);
        for (Client user : server.getClients()) //check if user is already logged-in
            if (user.getThread() == this.t && user.isConnected() == true) {
                answer = "You are already logged-in!";
                checkConnected = true;
            }
        if (checkConnected)
            return answer;

        if (command.length == 2) {
            if (server.getClientName().contains(command[1])) {
                for (Client user : server.getClients()) {
                    if (user.getName().compareTo(command[1]) == 0) {
                        if (!user.isConnected())
                        {
                            user.setT(this.t);
                            user.setConnected(true);
                            answer = "Client " + command[1] + " connected succesfully!";
                        } else {
                            answer = "Client is already connected!";
                        }
                    }
                }
            } else {
                answer = "Please register first!";
            }
        } else {
            answer = "Wrong login command! Type 'login user'";
        }
        return answer;
    }
}
