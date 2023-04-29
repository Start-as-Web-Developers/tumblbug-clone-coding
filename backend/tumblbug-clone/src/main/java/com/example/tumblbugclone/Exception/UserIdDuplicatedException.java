package com.example.tumblbugclone.Exception;

public class UserIdDuplicatedException extends Exception{
    public UserIdDuplicatedException() {
        super();
    }

    public UserIdDuplicatedException(String message) {
        super(message);
    }
}