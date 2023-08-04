package com.example.tumblbugclone.Exception.projectException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class ProjectCantFindException extends TumblbugException {

    public ProjectCantFindException() {
        super(ExceptionConst.ProjectCantFind);
    }

}
