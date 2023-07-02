package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UnauthorizedUserException extends TumblbugException {
    public UnauthorizedUserException() {
        super(ExceptionConst.UnauthorizedUser);
    }
}
