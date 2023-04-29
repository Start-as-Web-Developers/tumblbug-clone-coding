package com.example.tumblbugclone.model;

import lombok.Data;

@Data
public class User {
    Long userIdx;
    String userName;
    String userId;
    String userPassword;
    String userEmail;
    String greeting;
    String userImg;
    String lastLogin;

    public User(String userName, String userId, String userPassword, String userEmail){
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

}
