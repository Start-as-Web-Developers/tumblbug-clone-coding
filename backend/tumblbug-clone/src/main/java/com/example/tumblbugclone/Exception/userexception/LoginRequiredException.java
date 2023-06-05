package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class LoginRequiredException extends TumblbugException {
    public LoginRequiredException(int errorStatus) {
        super(ExceptionConst.LoginRequiredStatus);
    }
}
