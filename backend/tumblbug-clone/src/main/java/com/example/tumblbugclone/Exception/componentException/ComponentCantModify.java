package com.example.tumblbugclone.Exception.componentException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class ComponentCantModify extends TumblbugException {
    public ComponentCantModify() {
        super(ExceptionConst.ComponentCantModify);
    }
}
