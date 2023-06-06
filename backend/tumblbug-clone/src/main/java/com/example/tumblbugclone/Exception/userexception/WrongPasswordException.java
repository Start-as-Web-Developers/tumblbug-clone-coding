package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class WrongPasswordException extends TumblbugException {
    public WrongPasswordException() {
        super(ExceptionConst.WrongPasswordStatus);
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
