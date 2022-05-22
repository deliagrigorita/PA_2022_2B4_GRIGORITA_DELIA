package app.server.command;

import app.server.app.Client;
import app.server.app.NetworkServer;

import java.io.IOException;
import java.net.Socket;

public class Stop implements Command {
    private Socket socket;
    private NetworkServer server;
    private Thread thread;

    private Client thisUser = null;
    private boolean checkConnected = false;
    private String answer;

    public Stop(Socket socket, NetworkServer server, Thread thread) {
        this.socket = socket;
        this.server = server;
        this.thread = thread;
    }

    @Override
    public String execute(String[] command) throws IOException {
        answer = "Server stopped!";
        server.setStop(true);
        this.socket.close();
        return answer;
    }
}
