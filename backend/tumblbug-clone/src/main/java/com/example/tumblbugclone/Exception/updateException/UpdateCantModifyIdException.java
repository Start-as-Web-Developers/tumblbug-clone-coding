package com.example.tumblbugclone.Exception.updateException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class UpdateCantModifyIdException extends TumblbugException {
    public UpdateCantModifyIdException() {
        super(ExceptionConst.UpdateCantModifyId);
    }
}
