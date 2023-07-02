package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UserIdDuplicatedException extends TumblbugException {
    public UserIdDuplicatedException() {
        super(ExceptionConst.UserIdDuplicatedStatus);
    }

    public UserIdDuplicatedException(String message) {
        super(message);
    }
}