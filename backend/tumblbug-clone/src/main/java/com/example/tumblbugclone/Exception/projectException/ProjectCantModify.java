package com.example.tumblbugclone.Exception.projectException;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;

public class ProjectCantModify extends TumblbugException {
    public ProjectCantModify() {
        super(ExceptionConst.ProjectCantModify);
    }
}
