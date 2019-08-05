package com.chatroom.demo.Handlers;

import com.chatroom.demo.InvalidPasswordConfirmation;
import com.chatroom.demo.Model.User;
import com.chatroom.demo.Repos.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserHandler {
    private UserRepo userRepo;

    public UserHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User loginUser(String username, String password) throws IllegalAccessException {
        Optional<User> user = this.userRepo.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return new User(username, password);
        } else if (user.isPresent()) {
            throw new IllegalAccessException("Username and password entered are incorrect.");
        } else {
            throw new IllegalAccessException("Account does not exist.");
        }

    }

    public User signupUser(String username, String password, String confirm) throws IllegalAccessException, InvalidPasswordConfirmation {
        Optional<User> user = this.userRepo.findByUsername(username);
        if (user.isPresent()) {
            throw new IllegalAccessException("This account already exists.");
        } else if (!password.equals(confirm)) {
            throw new InvalidPasswordConfirmation("The passwords do not match");
        } else {
            return this.userRepo.save(new User(username, password));
        }
    }

    public List<String> find() {
        List<User> all = this.userRepo.findAll();
        List<String> names = new ArrayList<>();
        for (User u : all) {
            names.add(u.getUsername() + " " + u.getPassword());
        }
        return names;

    }

    public List<User> deleteAccount(String username) {
        String password = this.userRepo.findUserByUsername(username).getPassword();
        this.userRepo.deleteByUsername(username);
        return new ArrayList<User>() {
            {
                add(new User(username, password));
            }
        };
    }

    public void del() {
        this.userRepo.deleteAll();
    }
}
