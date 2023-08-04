package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UserDTOConvertException extends TumblbugException {
    public UserDTOConvertException() {
        super(ExceptionConst.UserDTOConvertStatus);

    }

    public UserDTOConvertException(String message) {
        super(message);
    }
}
