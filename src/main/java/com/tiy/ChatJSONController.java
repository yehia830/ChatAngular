package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yehia830 on 9/23/16.
 */
@RestController
public class ChatJSONController {
    @Autowired
    MessageRepository messages;
    @RequestMapping(path = "/chat.json", method = RequestMethod.GET)
    public List<Message> getAllMessages(String Text){
        Message newMessage = new Message(Text);
        messages.save(newMessage);
        Iterable<Message> listOfMessages = messages.findAll();
        List<Message> allMessages = new ArrayList<>();
        for(Message message : listOfMessages){
            allMessages.add(message);
        }
        return allMessages;
    }
}
