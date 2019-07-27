package com.chatroom.demo;

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
