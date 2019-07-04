package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.chatroom.demo.Handlers.UserLoader.filePath;

public class FriendLoader {
    public static void loadFriends(User user) {
        String line;
        try {
            File friendList = new File(filePath + user.getUsername() +"/FriendsList");
            BufferedReader br = new BufferedReader(new FileReader(friendList));
            while ((line = br.readLine()) != null) {
                User f = new User(line);
                user.getFriendList().add(f);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading file!");
        }
    }
}
