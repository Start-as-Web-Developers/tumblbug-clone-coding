package com.example.tumblbugclone.Exception;

public class TumblbugException extends Exception{

    private int errorStatus;

    public TumblbugException(int errorStatus){
        this.errorStatus = errorStatus;
    }
    public int getErrorStatus(){
        return errorStatus;
    }

    public TumblbugException() {
        super();
    }

    public TumblbugException(String message) {
        super(message);
    }
}
