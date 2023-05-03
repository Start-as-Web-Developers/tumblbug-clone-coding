package com.example.tumblbugclone.Exception.userexception;

public class UserEmailDuplicatedException extends Exception{
    public UserEmailDuplicatedException() {
        super();
    }

    public UserEmailDuplicatedException(String message) {
        super(message);
    }
}