package com.example.tumblbugclone.Exception;

public class UserEmailDuplicatedException extends Exception{
    public UserEmailDuplicatedException() {
        super();
    }

    public UserEmailDuplicatedException(String message) {
        super(message);
    }
}