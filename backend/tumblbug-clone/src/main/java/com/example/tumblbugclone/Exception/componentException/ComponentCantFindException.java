package com.example.tumblbugclone.Exception.componentException;

import com.example.tumblbugclone.model.Component;

public class ComponentCantFindException extends Exception{
    public ComponentCantFindException() {
        super();
    }

    public ComponentCantFindException(String message) {
        super(message);
    }
}
