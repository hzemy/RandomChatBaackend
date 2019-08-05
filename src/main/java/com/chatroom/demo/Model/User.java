package com.chatroom.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Objects;

@Document(collection = "Users")
public class User {
    @Id
    @Indexed(direction = IndexDirection.ASCENDING)
    private String username;
    private String password;
    private ArrayList<User> friendList;

    public void setFriendList(ArrayList<User> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        this.friendList = new ArrayList<>();
    }

    public User() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
