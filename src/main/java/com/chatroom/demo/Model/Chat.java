package com.chatroom.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Document(collection = "Chats")
public class Chat {
    private String message;
    private ArrayList<User> recipient;
    private User sender;
    private String rec;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Indexed(direction = IndexDirection.ASCENDING)
    private String date;

    //store chats for individuals and groups
    public Chat(String message, String id, User sender, ArrayList<User> recipient) {
        this.message = message;
        this.rec = id;
        this.sender = sender;
        this.recipient = recipient;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.date = formatter.format(date);
    }

    //store the created group info
    public Chat(String gName, ArrayList<User> members) {
        this.rec = gName;
        this.recipient = members;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.date = formatter.format(date);    }

    public Chat() {

    }

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
        this.rec = id;
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
        return rec;
    }
}