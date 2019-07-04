package com.chatroom.demo.Controller;

import com.chatroom.demo.Handlers.ChatHandler;
import com.chatroom.demo.Handlers.ChatLoader;
import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @RequestMapping("/Chat/chat") //send chat
    public Chat chat(@RequestParam(value = "username") String username,
                     @RequestParam(value = "friend") String friend, //id
                     @RequestParam(value = "message") String message) {
        boolean isGroup = ChatLoader.isGroup(username, friend);
        User user = new User(username);
        Chat chat;
        if (isGroup) {
            chat = ChatHandler.saveChatGroup(user, friend, message);
        } else {
            chat = ChatHandler.saveChat(user, friend, message);
        }
        return chat;
    }

    @RequestMapping("/Chat/read") //read
    public List<Chat> read(@RequestParam(value = "username") String username,
                           @RequestParam(value = "friend") String friend) { //id
        return ChatLoader.loadChat(new User(username), friend);
    }

//    @RequestMapping("/Chat/view") //view friends
//    public List<User> friends(@RequestParam(value = "username") String username) {
//        return ChatHandler.getFriends(username);
//    }

}


