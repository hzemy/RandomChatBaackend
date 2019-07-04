package com.chatroom.demo.Handlers;

import com.chatroom.demo.InvalidPasswordConfirmation;
import com.chatroom.demo.Model.User;

import java.net.PasswordAuthentication;
import java.util.List;

public class UserHandler {
    public static User loginUser(String username, String password) throws IllegalAccessException {
        List<User> users = UserLoader.loadUsers();
        User newUser = new User(username, password);
        for (User u: users) {
            if (u.equals(newUser)) {
                return u;
            }
        }
        throw new IllegalAccessException("Username and password entered are incorrect.");
    }

    public static User signupUser(String username, String password, String confirm) throws IllegalAccessException, InvalidPasswordConfirmation {
        List<User> users = UserLoader.loadUsers();
        User newUser = new User(username, password);
        for (User u: users) {
            if (u.equals(newUser)) {
                throw new IllegalAccessException("This account already exists.");
            }
        }
        if (!password.equals(confirm)) {
            throw new InvalidPasswordConfirmation("The passwords don't match.");
        }
        UserLoader.createUser(username, password);
        return newUser;

    }
}
