package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UserCantModifyIdException extends TumblbugException {
    public UserCantModifyIdException() {
        super(ExceptionConst.UserCantModifyIdStatus);
    }

    public UserCantModifyIdException(String message) {
        super(message);
    }
}
