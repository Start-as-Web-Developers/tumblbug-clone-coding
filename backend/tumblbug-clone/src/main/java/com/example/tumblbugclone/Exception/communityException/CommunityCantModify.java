package com.example.tumblbugclone.Exception.communityException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class CommunityCantModify extends TumblbugException {

    public CommunityCantModify() {
        super(ExceptionConst.CommunityCantModify);
    }
}
