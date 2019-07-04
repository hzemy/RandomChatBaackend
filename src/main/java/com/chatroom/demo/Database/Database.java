//package com.chatroom.demo.Database;
//
//import com.chatroom.demo.Model.User;
//import com.chatroom.demo.Repos.UserRepo;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Component
//public class Database implements CommandLineRunner {
//    private UserRepo userRepo;
//
//    public Database(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        //create a bunch of user objs:
//        User user1 = new User("Hajera");
//        User user2 = new User("Aqueel");
//        User user3 = new User("Aquib");
//        User user4 = new User("Zehra");
//
//        //delete all objects:
//        this.userRepo.deleteAll();
//
//        //add these objs to database:
//        //List<User> users = Arrays.asList(user1, user2, user3, user4); //adding them to list
//        this.userRepo.save(user1);
//        this.userRepo.save(user2);
//        this.userRepo.save(user3);
//        this.userRepo.save(user4);
//
//    }
//}
