package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.net.Socket;

public class Read implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread thread;
    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Read(Socket socket, NetworkServer server, Thread thread) {
        this.socket = socket;
        this.server = server;
        this.thread = thread;
    }
    @Override
    public String execute(String[] command) {
        for(Client user : server.getClients()) //check if user is logged-in
            if(user.getThread() == this.thread)
                if(!user.isConnected()){
                    answer = "You are not logged-in! Please log-in first!";
                    thisUser = user;
                    System.out.println(answer);
                    checkConnected = true;
                }
                else{
                    thisUser = user;
                }
        if(thisUser == null){
            answer = "You are not logged-in! Please log-in first!";
            System.out.println(answer);
            checkConnected = true;
        }

        if(checkConnected)
            return answer;

        if(command.length > 1) {
            answer = "The command is just 'read' ";
        }
        else {
            if (!thisUser.getWaitingMessages().isEmpty()) {
                String aux;
                answer = "You have messages! : ";
                for (String message : thisUser.getWaitingMessages()) {
                    answer = answer + message + " ; ";
                    System.out.println(answer);
                }
            } else {
                answer = "You don't have any messages!";
            }
        }

        return answer;
    }
}
