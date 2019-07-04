package com.chatroom.demo.Controller;

import com.chatroom.demo.Handlers.UserHandler;
import com.chatroom.demo.InvalidPasswordConfirmation;
import com.chatroom.demo.Model.User;
//import com.chatroom.demo.Repos.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @RequestMapping("/User/login")
    public User userlogin(@RequestParam(value = "username") String username,
                     @RequestParam(value = "password") String password) throws IllegalAccessException {
        return UserHandler.loginUser(username, password);
    }

    @RequestMapping("/User/signup")
    public User usersignup(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "confirm") String confirm) throws IllegalAccessException, InvalidPasswordConfirmation {
        return UserHandler.signupUser(username, password, confirm);
    }
}
