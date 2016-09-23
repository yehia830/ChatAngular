package com.tiy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jessicatracy on 8/25/16.
 */
public class ConnectionHandler implements Runnable {
    Socket clientSocket;

    public ConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            handleIncomingConnection(clientSocket);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void handleIncomingConnection(Socket clientSocket) throws IOException {


        System.out.println("Now displaying info about who has connected to our server: ");
        System.out.println("Connection from " + clientSocket.getInetAddress().getHostAddress());

    
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while((inputLine = inputFromClient.readLine()) != null) {
            System.out.println("Received message \"" + inputLine + "\" from " + clientSocket.toString());
            outputToClient.println("Message received! :-)");
        }

    }
}