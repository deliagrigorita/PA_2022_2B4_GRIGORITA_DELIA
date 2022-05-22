package com.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        boolean isActive = true;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out = new PrintWriter
                        (socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader
                        (new InputStreamReader(socket.getInputStream()))) {
            do {
                Scanner keyboard = new Scanner(System.in);
                System.out.println("Add command: ");
                String command = keyboard.nextLine();
                out.println(command);
                String response = in.readLine();
                System.out.println(response);
                if (response.compareTo("Server stopped!") == 0) {
                    isActive = false;
                }
                if(response.compareTo("Client stopped!") == 0){
                    isActive = false;
                }
            } while (isActive);
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
