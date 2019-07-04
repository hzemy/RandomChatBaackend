package com.chatroom.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

//@Document(collection = "Chats")
public class Chat {
    private String message;
    private ArrayList<User> recipient;
    private User sender;
    private String id;

    public Chat(User sender, ArrayList<User> recipient) {
        this.recipient = recipient;
        this.sender = sender;
        if (recipient.size() == 1) {
            this.id = recipient.get(0).getUsername();
        } else {
            this.id = UUID.randomUUID().toString();
        }
    }

    public Chat(String message, User sender, String id) {
        this.message = message;
        this.sender = sender;
        this.id = id;
    }

//    public Chat(String message, ArrayList<User> recipient, User sender) {
//        this.message = message;
//        this.recipient = recipient;
//        this.sender = sender;
//    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipient(ArrayList<User> recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<User> getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public String getId() {
        return id;
    }
}
