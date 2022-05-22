package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Friend implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread t;
    private String[] request;

    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Friend(Socket socket, NetworkServer server, Thread t) {
        this.socket = socket;
        this.server = server;
        this.t = t;
    }

    @Override
    public String execute(String[] command) {
        if(server.getClients().isEmpty()){
            answer = "You are not registered! Please first register and then log-in!";
            return answer;
        }
        thisUser = null;
        for(Client user : server.getClients()) //verific daca este logat clientul
            if(user.getThread() == this.t) {
                if (!user.isConnected()) {
                    answer = "You are not logged-in! Please log-in first!";
                    System.out.println(answer);
                    checkConnected = true;
                } else {
                    thisUser = user;
                }
            }
        if(thisUser == null){
            answer = "You are not logged-in! Please log-in first!";
            System.out.println(answer);
            checkConnected = true;
        }

        if(checkConnected)
            return answer;
        if(command.length > 1) {
            answer = "Have you new friends? ";
            for (int i = 1; i < command.length; i++) {
                if (server.getClientName().contains(command[i])) {
                    for (Client user : server.getClients())
                        if (user.getName().compareTo(command[i]) == 0) {
                            thisUser.getFriends().add(user);
                            answer = answer.concat(command[i]);
                            answer = answer.concat(", ");
                        }
                } else {
                    answer = answer + command[1] + " isn't a registered user!\n";
                }
            }
        }
        else{
            answer = "Wrong command... Type 'friend user1, user2, ...'";
        }
        return answer;
    }
}
