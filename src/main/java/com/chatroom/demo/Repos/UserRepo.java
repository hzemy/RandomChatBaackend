package com.chatroom.demo.Repos;

import com.chatroom.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);
}
