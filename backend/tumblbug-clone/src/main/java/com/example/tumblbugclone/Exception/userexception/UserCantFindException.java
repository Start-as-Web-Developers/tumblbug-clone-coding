package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UserCantFindException extends TumblbugException {
    public UserCantFindException() {
        super(ExceptionConst.UserCantFindStatus);
    }

    public UserCantFindException(String message) {
        super(message);
    }
}
