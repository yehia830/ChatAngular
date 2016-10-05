package com.tiy;

import jodd.json.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class ConnectionHandler implements Runnable {
    Socket clientSocket;
    Server server;


    public ConnectionHandler(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        try {
            handleIncomingConnection(clientSocket);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //Instead I'm going to make it receive json message, deserialize it, (save it to db), and send back a list of all messages (from arraylist now, db later?)
    public void handleIncomingConnection(Socket clientSocket) throws IOException {

        System.out.println("Now displaying info about who has connected to our server: ");
        System.out.println("Connection from " + clientSocket.getInetAddress().getHostAddress());

        //Read in info from client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //Print output to client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
//        while((inputLine = inputFromClient.readLine()) != null) {
//            if (!inputLine.equals("history")) {
//                System.out.println("Received message \"" + inputLine + "\" from " + clientSocket.toString());
//                outputToClient.println(": " + inputLine);
//                server.addToAllMessages(inputLine);
//            } else {
//                outputToClient.println("Now printing history!");
//                for (String message : server.allMessages) {
////                    System.out.println("Now printing history!");
//                    outputToClient.println(message);
//                    System.out.println(message);
//                }
//                outputToClient.println("HISTORY::END.");
//
//            }
//        }

        while((inputLine = inputFromClient.readLine()) != null) {
            System.out.println("Received message \"" + inputLine + "\" from " + clientSocket.toString());
            Message currentMessage = jsonRestore(inputLine);
            //Saving to db not working!!! :-(
//            server.saveMessageToDB(currentMessage);
            String messageContent = currentMessage.getName() + " said: " + currentMessage.getText();
            server.addToAllMessages(messageContent);
            for (String message : server.allMessages) {
                outputToClient.println(message);
            }
            outputToClient.println("TXT::DONE");
        }
    }

    public Message jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        Message message = toDoItemParser.parse(jsonTD, Message.class);

        return message;
    }

}
