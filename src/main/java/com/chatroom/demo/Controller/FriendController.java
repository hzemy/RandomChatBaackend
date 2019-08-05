package com.chatroom.demo.Controller;

import com.chatroom.demo.Handlers.FriendHandler;
import com.chatroom.demo.Model.User;
import com.chatroom.demo.Repos.UserRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {
    private FriendHandler friendHandler;

    public FriendController(UserRepo userRepo) {
        this.friendHandler = new FriendHandler(userRepo);
    }

    @RequestMapping("/Friends/add")
    public List<User> addfriend(@RequestParam(value = "username") String username,
                                @RequestParam(value = "friendName") String friendName) throws IllegalArgumentException {
        friendHandler.addFriend(username, friendName);
        return friendHandler.addFriend(friendName, username);
    }

    @RequestMapping("/Friends/list")
    public List<User> listfriends(@RequestParam(value = "username") String username) {
        return friendHandler.getFriends(username);
    }

    @RequestMapping("/Friends/addrandom")
    public List<User> addrandom(@RequestParam(value = "username") String username) throws IllegalAccessException {
        return friendHandler.addRandom(username);
    }
}