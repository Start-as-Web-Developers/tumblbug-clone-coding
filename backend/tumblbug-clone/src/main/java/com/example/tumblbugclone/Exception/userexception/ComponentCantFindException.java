package com.example.tumblbugclone.Exception.userexception;

import com.example.tumblbugclone.model.Component;

public class ComponentCantFindException extends Exception{
    public ComponentCantFindException() {
        super();
    }

    public ComponentCantFindException(String message) {
        super(message);
    }
}
