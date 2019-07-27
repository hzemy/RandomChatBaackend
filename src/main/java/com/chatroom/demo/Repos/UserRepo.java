package com.chatroom.demo.Repos;

import com.chatroom.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String>{
    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);
    void deleteByUsername(String username);
}
