package com.example.tumblbugclone.Exception.updateException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class CantFindUpdateException extends TumblbugException {
    public CantFindUpdateException() {
        super(ExceptionConst.CantFindUpdate);
    }
}
