package com.chatroom.demo.Handlers;

import com.chatroom.demo.IllegalCharacterException;
import com.chatroom.demo.Model.Chat;
import com.chatroom.demo.Model.User;
import com.chatroom.demo.Repos.ChatRepo;
import com.chatroom.demo.Repos.UserRepo;

import java.util.*;

public class ChatHandler {

    private ChatRepo chatRepo;
    private UserRepo userRepo;

    public ChatHandler(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    public List<Chat> saveChat(String username, String recipient, String message) {
        String originalId = username + "-" + recipient;
        String otherId = recipient + "-" + username;
        User user = this.userRepo.findUserByUsername(username);
        User friend = this.userRepo.findUserByUsername(recipient);
        ArrayList<User> recipients = new ArrayList<User>() {
            {
                add(friend);
            }
        };
        this.chatRepo.insert(new Chat(message, originalId, user, recipients));
        return this.chatRepo.findAllByRecIsInOrderByDateDesc(new ArrayList<String>() {
            {
                add(originalId);
                add(otherId);
            }
        });
    }

    public List<Chat> find(String id1, String id2) {
        List<String> ids = new ArrayList<String>() {

            {
                add(id1);
                add(id2);
            }
        };
        return this.chatRepo.findAllByRecIsInOrderByDateDesc(ids);

    }

    public List<Chat> saveGroupChat(String username, String gName, String message) {
        User user = this.userRepo.findUserByUsername(username);
        ArrayList<User> friends = this.chatRepo.findByMessageIsNullAndRecIs(gName).getRecipient();
        this.chatRepo.insert(new Chat(message, gName, user, friends));
        return this.chatRepo.findAllByMessageIsNotNullAndRecIsOrderByDateDesc(gName);
    }

    public Chat createGroup(String username, ArrayList<String> friendNames, String gName) throws IllegalCharacterException {
        if (gName.contains("-")) {
            throw new IllegalCharacterException("Group names cannot contain the character \"-\"!");
        }
        friendNames.add(username);
        ArrayList<User> members = new ArrayList<>();
        for (String name : friendNames) {
            User friend = this.userRepo.findUserByUsername(name);
            members.add(friend);
        }
        return this.chatRepo.save(new Chat(gName, members));
    }

    //user can only request for chats with friends in their friendlist and groupchats where a chat
    // exists, whose message field is null and the list of recipients contains the user

    public List<Chat> findGroup(String gName) throws IllegalArgumentException {

        Chat group = this.chatRepo.findByMessageIsNullAndRecIs(gName);
        /*if (!group.getRecipient().contains(this.userRepo.findUserByUsername(username))) {
            throw new IllegalArgumentException("This group does not exist!");
        }*/
        return this.chatRepo.findAllByMessageIsNotNullAndRecIsOrderByDateDesc(gName);
    }

    //checks if a chat belongs to a group
    public boolean isGroup(String id) {
        return !id.contains("-");
    }

    public List<Chat> getAll() {
        return this.chatRepo.findAll();
    }

    public void delAll() {
        this.chatRepo.deleteAll();
    }

    public List<Chat> getGroups(String username) {
        List<Chat> all = getAll();
        List<Chat> gNames = new ArrayList<>();
        for (Chat c : all) {
            if (c.getMessage() == null && c.getRecipient().contains(this.userRepo.findUserByUsername(username))) {
                gNames.add(c);
            }
        }
        return gNames;
    }

    public Chat getLastText(List<String> rec) {
        List<Chat> allChats = this.chatRepo.findAllByRecIsInOrderByDate(rec);
        if (!(allChats.size() == 0)) {
            return allChats.get(allChats.size() - 1);
        } else {
            return new Chat("", "", new User(), new ArrayList<>());
        }

    }

    public List<Chat> getChats(String username) {
        List<Chat> allChats = new ArrayList<>();
        List<User> friends = this.userRepo.findUserByUsername(username).getFriendList();
        List<Chat> groups = getGroups(username);
        for (User friend : friends) {
            List<String> ids = new ArrayList<String>() {
                {
                    add(username + "-" + friend.getUsername());
                    add(friend.getUsername() + "-" + username);
                }
            };
            allChats.add(new Chat(friend.getUsername() + "-" + username, getLastText(ids).getMessage(), getLastText(ids).getSender()));
        }
        for (Chat c : groups) {
            List<String> ids = new ArrayList<String>() {
                {
                    add(c.getId());
                }
            };
            allChats.add(new Chat(c.getId(), getLastText(ids).getMessage(), getLastText(ids).getSender()));
        }
        return allChats;

    }

}