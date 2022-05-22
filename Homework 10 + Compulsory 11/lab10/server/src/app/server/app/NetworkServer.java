package app.server.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class NetworkServer {
    public static final int PORT = 8100;
    private Set<String> clientName = new HashSet<>();
    private Set<Client> clients = new HashSet<>();
    private boolean stop = false;
    private boolean stillConnected = true;

    public NetworkServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(60000);//1 min
            do{
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, this);
                client.start();
                for(Client user : clients)
                    System.out.println(user);
            } while (!stop);

            while(stillConnected){
                stillConnected = false;
                for(Client user : clients)
                    if(user.isConnected())
                        stillConnected = true;
            }

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            serverSocket.close();
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    public Set<Client> getClients() {
        return clients;
    }
    public Set<String> getClientName() {
        return clientName;
    }

    @Override
    public String toString() {
        return "NetworkServer{" +
                "clientName=" + clientName +
                ", clients=" + clients +
                ", stop=" + stop +
                ", stillConnected=" + stillConnected +
                '}';
    }
}
