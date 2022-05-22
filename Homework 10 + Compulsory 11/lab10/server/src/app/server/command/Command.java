package app.server.command;

import java.io.IOException;
import java.net.Socket;

public interface Command {
    public  String execute(String[] command) throws IOException;
}
