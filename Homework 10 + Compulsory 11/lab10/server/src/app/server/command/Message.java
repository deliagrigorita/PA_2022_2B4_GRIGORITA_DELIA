package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Message implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread t;

    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Message(Socket socket, NetworkServer server, Thread t) {
        this.socket = socket;
        this.server = server;
        this.t = t;
    }

    @Override
    public String execute(String[] command) {
        if(server.getClients().isEmpty()){
            answer = "You are not registered! Please register and log-in!";
            return answer;
        }
        for(Client user : server.getClients())
            if(user.getThread() == this.t)
                if(!user.isConnected()){
                    answer = "You are not logged-in! Please log-in!";
                    thisUser = user;
                    System.out.println(answer);
                    checkConnected = true;
                }
                else{
                    thisUser = user;
                }
        if(thisUser == null){
            answer = "You are not logged-in! Please log-in!";
            System.out.println(answer);
            checkConnected = true;
        }

        if(checkConnected)
            return answer;

        if(command.length > 1)
            if(!thisUser.getFriends().isEmpty()){
                String message = thisUser.getName();
                for(int i = 1; i < command.length; i++)
                    message = message + " " + command[i];
                for(Client friendd : thisUser.getFriends())
                {
                    friendd.getWaitingMessages().add(message);
                }

                answer = "You sent a new message to your friends!";
            }
            else{
                answer = "You don't have friends! ";
            }
        else{
            answer = "Try with a message!";
        }
        return answer;
    }
}
