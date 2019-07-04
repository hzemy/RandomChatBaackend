package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.chatroom.demo.Handlers.UserLoader.filePath;

public class ChatHandler {
    //read and send msg

    public static void list(String dir, List<File> fileList) {
        File directory = new File(dir);
        File[] list = directory.listFiles();
        for (File files : list) {
            if (files.isDirectory()) {
                list(files.getAbsolutePath(), fileList);
            } else if (files.isFile()) {
                fileList.add(files);
            }
        }
    }

    public static Chat saveChat(User user, String id, String message) {
        //FriendLoader.loadFriends(user);
        Chat c = new Chat(message, user, id);
        ArrayList<User> friends = new ArrayList<>();
//        User friend = new User(id);
//        FriendLoader.loadFriends(friend);
        friends.add(new User(id));
        c.setRecipient(friends);
        File newFriend = new File(filePath + user.getUsername() + "/" + id);
        File saveFriend = new File(filePath + id + "/" + user.getUsername());
        if (!newFriend.exists()) {
            try {
                newFriend.createNewFile();
            } catch (IOException e) {
                System.out.println("Error saving new chat - user(creation)");
            }
        }
        if (!saveFriend.exists()) {
            try {
                newFriend.createNewFile();
            } catch (IOException e) {
                System.out.println("Error saving new chat - friend(creation)");
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFriend, true))) {
            bw.write( "Me: " + message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving new chat - user(writing)");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveFriend, true))) {
            bw.write(user.getUsername() + ": " + message);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving new chat - friend(writing)");
        }
        return c;
    }

    public static Chat saveChatGroup(User user, String id, String message) {
        //FriendLoader.loadFriends(user);
        Chat c = new Chat(message, user, id);
        ArrayList<User> recipients = new ArrayList<>();
        List<File> groupFile = new ArrayList<>();
        list(filePath, groupFile);
        for (File f : groupFile) {
            if (f.getName().equals(id)) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
                    if (f.getParentFile().getName().equals(user.getUsername())) {
                        bw.write("Me: " + message);
                    } else {
//                        User friend = new User(f.getParentFile().getName());
//                        FriendLoader.loadFriends(friend);
                        recipients.add(new User(f.getParentFile().getName()));
                        bw.write(user.getUsername() + ": " + message);
                    }
                    bw.newLine();
                } catch (IOException e) {
                    System.out.println("Error saving new chat(writing)");
                }
            }
        }
        c.setRecipient(recipients);
        return c;
    }
}
