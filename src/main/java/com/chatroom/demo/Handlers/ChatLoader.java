package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.chatroom.demo.Handlers.ChatHandler.list;

public class ChatLoader {
    final static String filePath = "/Users/hzemy/IdeaProjects/src/";

    public static boolean isGroup(String username, String friend) {
        User user = new User(username);
        FriendLoader.loadFriends(user);
        boolean isGroup = true;
        for (User f : user.getFriendList()) {
            if (f.getUsername().equals(friend)) {
                isGroup = false;
                break;
            }
        }
        return isGroup;
    }

    public static ArrayList<User> getGroupMembers(String id) {
        List<File> groupFile = new ArrayList<>();
        list(filePath, groupFile);
        ArrayList<User> friends = new ArrayList<>();
        for (File f : groupFile) {
            if (f.getName().equals(id)) {
//                User newFriend = new User(f.getParentFile().getName());
//                FriendLoader.loadFriends(newFriend);
                friends.add(new User(f.getParentFile().getName()));
            }
        }
        return friends;
    }

    public static List<Chat> loadChat(User user, String id) {
        //check if friend exists
        //FriendLoader.loadFriends(user);
        ArrayList<Chat> chat = new ArrayList<>();
        File file = new File(filePath + user.getUsername() + "/" + id);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Chat c = new Chat(line, user, id);
                ArrayList<User> friends = new ArrayList<>();
                if (isGroup(user.getUsername(), id)) {
                    c.setRecipient(getGroupMembers(id));
                } else {
//                    User newFriend = new User(id);
//                    FriendLoader.loadFriends(newFriend);
                    friends.add(new User(id));
                    c.setRecipient(friends);
                }
                chat.add(c);
            }
        } catch (IOException e) {
            System.out.println("Error loading chat.");
        }
        return chat;
    }

}
