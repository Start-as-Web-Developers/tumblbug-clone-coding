package com.example.tumblbugclone.Exception.projectlistexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class StartIndexException extends TumblbugException {
    public StartIndexException() {
        super(ExceptionConst.StartIndexStatus);
    }

    public StartIndexException(String message) {
        super(message);
    }
}
