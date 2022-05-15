import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClientThread {
    private Socket socket = null;
    private List<Client> users = new ArrayList<>();
    private int serverStopped = 0;
    private List<ClientThread> clients = new ArrayList();
    private int idClient;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            populateClients();
            while (!socket.isClosed()) {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if (serverStopped == 0) {
                    this.clients = Server.getClients();
                    idClient = clients.size();
                    clients.add(this);
                } else if (serverStopped == 1 && clients.size() == 0) {
                    this.socket.close();
                }

                String request = input.readLine();
                String response = null;

                String[] tokens = null;
                if(request != null) {
                    tokens = request.split(" ");
                    request = tokens[0];
                }
                int okLogin = 0;

                System.out.println("Request from client: " + request);

                if (request == null) {
                    response = "Server stopped";
                    out.println(response); // trimite raspunsul
                    out.flush(); // goleste bufferul
                    serverStopped = 1;
                    this.socket.close(); //inchide socketul
                    System.exit(1);
                } else if (request.equals("stop")) {
                    response = "Server stopped";
                    out.println(response); // trimite raspunsul
                    out.flush(); // goleste bufferul
                    serverStopped = 1;
                } else if (request.equals("exit")) {
                    users.get(okLogin).setLogat(0);
                    response = "Disconnected";
                    out.println(response); // trimite raspunsul
                    out.flush(); // goleste bufferul
                    this.socket.close(); //inchide socketul
                    clients.remove(idClient);
                } else if (request.equals("login")) {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getName().equals(tokens[1])) {
                            okLogin = i;
                        }
                    }
                    if (okLogin == 0) {
                        response = "This user does not exist.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    } else if (okLogin > 0 && users.get(okLogin).getLogat() == 0) {
                        users.get(okLogin).setLogat(1);
                        response = "You just loged in.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    } else if (okLogin > 0 && users.get(okLogin).getLogat() == 1) {
                        response = "You're already logged.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    }
                } else if (request.equals("register")) {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getName().equals(tokens[1])) {
                            okLogin = 1;
                        }
                    }
                    if (okLogin == 0) {
                        Client userNou = new Client(tokens[1]);
                        users.add(userNou);
                        response = "Your account was created.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    } else {
                        response = "This user already exists.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    }
                } else if (request.equals("friends")) {
//                    if (okLogin == 0) {
//                        response = "You need to login";
//                        out.println(response); // trimite raspunsul
//                        out.flush(); // goleste bufferul
//                    } else {
                    int noNewFriends = 0;
                    for (int i = 1; i < tokens.length; i++) {
                        int okUserExists = 0;
                        for (int j = 0; j < users.size(); j++) {
                            if (users.get(j).getName().equals(tokens[i])) {
                                okUserExists = j;
                            }
                        }
                        int okFriendExists = 0;
                        for (int j = 0; j < users.get(okLogin).friends.size(); j++) {
                            if (users.get(okLogin).friends.get(j).equals(tokens[i])) {
                                okFriendExists = 1;
                            }
                        }
                        if (okFriendExists == 0 && okUserExists > 0) {
                            noNewFriends++;
                            users.get(okLogin).friends.add(tokens[i]);
                        }
                    }
                    if (noNewFriends == 0) {
                        response = "The users are already your friends or they aren't registered.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    } else {
                        response = "You have " + noNewFriends + " new friends.";
                        out.println(response); // trimite raspunsul
                        out.flush(); // goleste bufferul
                    }
//                    }
                } else {
                    response = "Server received an invalid request ... ";
                    out.println(response); // trimite raspunsul
                    out.flush(); // goleste bufferul
                }
            }
        } catch (IOException exception) {
            System.err.println("Uf error..." + exception);
        }
    }

    private void populateClients() {
        Client user = new Client("-");
        users.add(user);
        user = new Client("delia");
        users.add(user);
        user = new Client("alex");
        users.add(user);
        user = new Client("cristina");
        users.add(user);
        user = new Client("cosmin");
        users.add(user);
        user = new Client("malina");
        users.add(user);
        user = new Client("sergiu");
        users.add(user);
        user = new Client("andra");
        users.add(user);
        user = new Client("daniel");
        users.add(user);
    }

    public void start() {
    }
}
