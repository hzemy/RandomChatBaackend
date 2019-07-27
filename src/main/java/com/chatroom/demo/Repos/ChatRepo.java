package com.chatroom.demo.Repos;

import com.chatroom.demo.Model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends MongoRepository<Chat, String> {
    List<Chat> findAllByRecIsInOrderByDate(List<String> ids);
    List<Chat> findAllByMessageIsNotNullAndRecIsOrderByDate(String gName);
    Chat findByMessageIsNullAndRecIs(String gName);




}
