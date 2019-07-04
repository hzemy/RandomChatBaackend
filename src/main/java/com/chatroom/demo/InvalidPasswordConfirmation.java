package com.chatroom.demo;

import java.awt.event.ActionEvent;

public class InvalidPasswordConfirmation extends Exception{
    public InvalidPasswordConfirmation(String message) {
        super(message);
    }

//    public static void handle(ActionEvent event){
//        try {
//            throw new Exception("The passwords do not match");
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
