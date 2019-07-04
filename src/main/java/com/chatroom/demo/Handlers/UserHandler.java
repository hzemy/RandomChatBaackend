package com.chatroom.demo.Handlers;

import com.chatroom.demo.InvalidPasswordConfirmation;
import com.chatroom.demo.Model.User;
import com.chatroom.demo.Repos.UserRepo;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.Optional;

public class UserHandler {
    private UserRepo userRepo;

    public UserHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User loginUser(String username, String password) throws IllegalAccessException {
        Optional<User> user = this.userRepo.findByUsername(username);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            return new User(username, password);
        } else {
            throw new IllegalAccessException("Username and password entered are incorrect.");

        }

    }

    public User signupUser(String username, String password) throws IllegalAccessException, InvalidPasswordConfirmation {
        Optional<User> user = this.userRepo.findByUsername(username);
        if(user.isPresent() && user.get().getPassword().equals(password)){
            throw new IllegalAccessException("Username and password entered are incorrect.");

        } else {
            return this.userRepo.save(new User(username, password));
        }
    }
}
