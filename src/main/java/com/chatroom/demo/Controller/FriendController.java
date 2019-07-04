package com.chatroom.demo.Controller;

import com.chatroom.demo.Handlers.FriendHandler;
import com.chatroom.demo.Handlers.FriendLoader;
import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {
    @RequestMapping("/Friends/add")
    public ArrayList<String> addfriend(@RequestParam(value = "user") String user,
                                       @RequestParam(value = "friend") String friend) throws IllegalArgumentException {
        User u = new User(user);
        User f = new User(friend);
        FriendHandler.addFriend(u, f);
        FriendHandler.addFriend(f, u);
        return FriendHandler.getFriends(u);
    }

    @RequestMapping("/Friends/list")
    public List<String> listfriends(@RequestParam(value = "user") String user) {
        User u = new User(user);
        FriendLoader.loadFriends(u);
        return FriendHandler.getFriends(u);
    }

    @RequestMapping("/Friends/addrandom")
    public String addrandom(@RequestParam(value = "user") String user) {
        return FriendHandler.addRandom(new User(user));
    }

    @RequestMapping("/Friends/creategroup")
    public Chat createGroup(@RequestParam(value = "user") String user,
                            @RequestParam(value = "friends") ArrayList<String> friends) {
        return FriendHandler.createGroup(new User(user), friends);
    }


}
