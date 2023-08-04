package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UserEmailDuplicatedException extends TumblbugException {
    public UserEmailDuplicatedException() {
        super(ExceptionConst.UserEmailDuplicatedStatus);
    }

    public UserEmailDuplicatedException(String message) {
        super(message);
    }
}