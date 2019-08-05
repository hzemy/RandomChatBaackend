package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.User;
import com.chatroom.demo.Repos.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class FriendHandler {
    private UserRepo userRepo;

    public FriendHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //add, list, addrandom
    public List<User> addRandom(String username) throws IllegalAccessException {
        User user = this.userRepo.findUserByUsername(username);
        List<User> all = this.userRepo.findAll();
        List<User> notFriends = new ArrayList<>();
        for (User u : all) {
            if (!user.getFriendList().contains(u) && !user.getUsername().equals(u.getUsername())) {
                notFriends.add(u);
            }
        }
        if (notFriends.size() == 0) {
            throw new IllegalAccessException("Wow, you are already friends with everybody using the app!");
        }
        int r = (int) (Math.random() * notFriends.size() - 1);
        User friend = notFriends.get(r);
        addFriend(username, friend.getUsername());
        addFriend(friend.getUsername(), username);
        return new ArrayList<User>() {
            {
                add(friend);
            }
        };
    }

    public List<User> addFriend(String username, String friendName) throws IllegalArgumentException, Error {
        User user = this.userRepo.findUserByUsername(username);
        User friend = this.userRepo.findUserByUsername(friendName);
        if (user.equals(friend)) {
            throw new IllegalArgumentException("You can't be friends with yourself :/");
        }
        if (user.getFriendList().contains(friend)) {
            throw new IllegalArgumentException("You are already friends with " + friendName + "!");
        }

        User newUser = new User(username, user.getPassword());
        ArrayList<User> friendsOfUser = user.getFriendList();
        friendsOfUser.add(friend);
        newUser.setFriendList(friendsOfUser);
        this.userRepo.save(newUser);
        return this.userRepo.findAll();
    }

    public List<User> getFriends(String username) {
        return this.userRepo.findUserByUsername(username).getFriendList();
    }

    // add exception if a user in f is not friends with user

    public void add(String username, String password) {
        User user = new User(username, password);
        ArrayList<User> friends = new ArrayList<User>() {
            {
                add(new User("hello", "hello"));
            }
        };
        user.setFriendList(friends);
        this.userRepo.save(user);
    }

}