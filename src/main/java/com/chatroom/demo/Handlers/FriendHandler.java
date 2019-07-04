package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.chatroom.demo.Handlers.UserLoader.filePath;

public class FriendHandler {
    //add, list, addrandom
    public static String addRandom(User user) throws IllegalStateException {
        FriendLoader.loadFriends(user);
        File file = new File(filePath + "UserPass");
        List<User> friends = user.getFriendList();
        List<User> notFriends = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] details;
            while ((line = br.readLine()) != null) {
                details = line.split(",");
                if (details[0].equals(user.getUsername())) {
                    continue;
                }
                if (!friends.contains(new User(details[0]))) {
                    notFriends.add(new User(details[0]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file!");
        }
        if (notFriends.size() == 0 && friends.size() == 0) { // No other users
            throw new IllegalStateException("There are no other users!");
        }
        if (notFriends.size() == 0) { // friends with all users
            throw new IllegalStateException("You are friends with all users!");
        }
        Random random = new Random();
        User r = notFriends.get(random.nextInt(notFriends.size()));
        addFriend(user, r);
        addFriend(r, user);
        //FriendLoader.loadFriends(r);
        return r.getUsername();

    }

    public static void addFriend(User u, User f) throws IllegalArgumentException {
        FriendLoader.loadFriends(u);
        if (u.getFriendList().contains(f)) {
            throw new IllegalArgumentException("You are already friends with " + f.getUsername());
        }
        File file = new File(filePath + u.getUsername() + "/" + f.getUsername());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
        try {
            u.getFriendList().add(f);
            //FriendLoader.loadFriends(f);
            File friendList = new File(filePath + u.getUsername() + "/FriendsList");
            BufferedWriter bw = new BufferedWriter(new FileWriter(friendList, true));
            bw.write(f.getUsername());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("Error adding a friend!");
        }
    }

    public static ArrayList<String> getFriends(User user) {
        ArrayList<String> friends = new ArrayList<>();
        for (User u : user.getFriendList()) {
            friends.add(u.getUsername());
        }
        return friends;
    }

    // add exception if a user in f is not friends with user
    public static Chat createGroup(User user, ArrayList<String> f) {
        ArrayList<User> friends = new ArrayList<>();
        for (String fr: f) {
            friends.add(new User(fr));
        }
        Chat newChat = new Chat(user, friends);
        friends.add(user);
        for (User u : friends) {
            //FriendLoader.loadFriends(u);
            File file = new File(filePath + u.getUsername() + "/" + newChat.getId());
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file");
            }
        }
        friends.remove(user);
        return newChat;
    }

}
