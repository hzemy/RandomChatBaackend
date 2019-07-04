package com.chatroom.demo.Handlers;

import com.chatroom.demo.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserLoader {
    final static String filePath = "/Users/hzemy/IdeaProjects/src/";


    public static List<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        File file = new File(filePath + "UserPass");
        String line;
        String[] details;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                details = line.split(",");
                User u = new User(details[0], details[1]);
                users.add(u);
            }
        } catch (IOException e) {
            System.out.println("Error checking if the user exists!");
        }
        return users;
    }

    public static void createUser(String username, String password) {
        //create user directory (whose name is username), and "FriendsList" file inside
        File userDir = new File(filePath + username);
        if (!userDir.mkdir()) {
            System.out.println("Couldn't create user directory");
            //return null;
        }
        File friendList = new File(filePath + username + "/FriendsList");
        try {
            friendList.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating friend list memory");
        }
        File saveUser = new File(filePath + "UserPass");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveUser, true))) {
            bw.write(username + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving new user");
        }
    }
}
