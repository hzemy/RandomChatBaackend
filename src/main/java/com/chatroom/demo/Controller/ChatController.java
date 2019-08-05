package com.chatroom.demo.Controller;

import com.chatroom.demo.Handlers.ChatHandler;
import com.chatroom.demo.IllegalCharacterException;
import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Repos.ChatRepo;
import com.chatroom.demo.Repos.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {
    private ChatHandler chatHandler;

    public ChatController(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatHandler = new ChatHandler(chatRepo, userRepo);
    }

    @RequestMapping("/Chat/chat") //send chat
    public List<Chat> chat(@RequestParam(value = "username") String username,
                           @RequestParam(value = "friend") String friend,
                           @RequestParam(value = "message") String message) {
        return chatHandler.saveChat(username, friend, message);
    }

    @RequestMapping("/Chat/read") //load saved chats
    public List<Chat> read(@RequestParam(value = "username") String username,
                           @RequestParam(value = "friend") String friend) {
        return chatHandler.find(username + "-" + friend, friend + "-" + username);
    }

    //Group stuff begins here:

    @RequestMapping("/Chat/creategroup") //create a group
    public Chat createGroup(@RequestParam(value = "username") String username,
                            @RequestParam(value = "friends") ArrayList<String> friends,
                            @RequestParam(value = "gName") String gName) throws IllegalCharacterException {
        return chatHandler.createGroup(username, friends, gName);
    }

    @RequestMapping("/Chat/readgroup") //load saved chats for a group
    public List<Chat> readGroup(@RequestParam(value = "gName") String gName) throws IllegalArgumentException {
        return chatHandler.findGroup(gName);
    }

    @RequestMapping("/Chat/chatgroup") // send chat in group
    public List<Chat> chatGroup(@RequestParam(value = "username") String username,
                                @RequestParam(value = "gName") String gName,
                                @RequestParam(value = "message") String message) {
        return chatHandler.saveGroupChat(username, gName, message);
    }

    //returns the list of groupnames the user is in
    @RequestMapping("/Chat/getGroups")
    public List<Chat> getGroups(@RequestParam(value = "username") String username) {
        return chatHandler.getGroups(username);
    }

    // ----------------------------------

    @RequestMapping("/Chat/isGroup")
    public boolean isGroup(@RequestParam(value = "id") String id) {
        return chatHandler.isGroup(id);
    }


    @RequestMapping("/Chat/all") // send chat in group
    public List<Chat> getAll() {
        return chatHandler.getAll();
    }

    @RequestMapping("/Chat/delete")
    public void delAll() {
        chatHandler.delAll();
    }


    @RequestMapping("/Chat/getChats")
    public List<Chat> getChats(@RequestParam(value = "username") String username) {
        return chatHandler.getChats(username);
    }

    @RequestMapping("/Chat/getLastText")
    public Chat getLastText(@RequestParam(value = "recs") List<String> recs) {
        return chatHandler.getLastText(recs);
    }

}