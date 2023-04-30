package com.example.tumblbugclone.Exception;

public class UserCantFindException extends Exception{
    public UserCantFindException() {
        super();
    }

    public UserCantFindException(String message) {
        super(message);
    }
}
