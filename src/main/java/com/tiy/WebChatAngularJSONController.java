package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;


import jodd.json.JsonSerializer;
import jodd.json.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class WebChatAngularJSONController {
    @Autowired
    MessageRepository messages;

    Client myClient = new Client();


    @RequestMapping(path = "/sendMessage.json", method = RequestMethod.GET)
    public ArrayList<String> sendMessageJson(String username, String messageText) {
        ArrayList<String> listOfMessages = new ArrayList<>();
        String serverResponse = null;
        try {
            myClient.createNewClientSocket();
            Message newMessage = new Message(username, messageText);
            //save message to db
            messages.save(newMessage);
            //serialize message
            String serializedMessage = jsonSave(newMessage);
            //send to server -- method
            listOfMessages = myClient.sendUserMessage(serializedMessage);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfMessages;
    }

    @RequestMapping(path = "/history.json", method = RequestMethod.GET)
    public ArrayList<Message> historyJson(String username) {
        Iterable<Message> myMessages = messages.findByName(username);
        ArrayList<Message> listOfUserMessages = new ArrayList<>();
        for (Message message : myMessages) {
            listOfUserMessages.add(message);
        }
        return listOfUserMessages;
    }

    public String jsonSave(Message messageToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(messageToSave);

        return jsonString;
    }

}
