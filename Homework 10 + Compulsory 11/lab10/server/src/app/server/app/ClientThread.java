package app.server.app;

import app.server.command.*;
import com.server.command.*;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    private  Socket socket = null ;
    private final NetworkServer server;
    private boolean isRunning;
    private Client client = null;
    private boolean verifConn = false;
    private String[] wordsOfRequest = null;
    private String answer;

    public ClientThread (Socket socket, NetworkServer server) { this.socket = socket; this.server = server;}

    public void run () {
        try {
            Command login = new Login(socket, server, this);
            Command register = new Register(socket, server, this);
            Command friend = new Friend(socket, server, this);
            Command message = new Message(socket, server, this);
            Command read = new Read(socket, server, this);
            Command exit = new Exit(socket, server, this);

            isRunning = true;
            do{
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                wordsOfRequest = null;
                if(request != null) {
                    wordsOfRequest = request.split(" ");
                }
                String command = wordsOfRequest[0];

                client = null;
                verifConn = false;
                answer = null;
                switch (command){
                    case "exit":
                        answer = exit.execute(wordsOfRequest);
                        break;
                    case "stop":
                        answer = "Server stopped!";
                        isRunning = false;
                        server.setStop(true);
                        this.socket.close();
                        break;
                    case "login":
                        answer = login.execute(wordsOfRequest);
                        System.out.println(answer);
                        break;
                    case "register" :
                        answer = register.execute(wordsOfRequest);
                        break;
                    case "friend":
                        answer = friend.execute(wordsOfRequest);
                        break;
                    case "message":
                        answer = message.execute(wordsOfRequest);
                        break;
                    case "read":
                        answer = read.execute(wordsOfRequest);
                        break;
                    default: answer = "Wrong command!";
                }
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(answer);
                out.flush();

            }while(isRunning);
            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            } finally {
                try {
                    socket.close(); // or use try-with-resources
                } catch (IOException e) { System.err.println (e); }
            }
    }
}
