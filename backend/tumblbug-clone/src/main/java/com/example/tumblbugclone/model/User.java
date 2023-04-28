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

    public User(String userName, String UserId, String userPassword, String userEmail){

    }

    //userIdx를 어떻게 주입시키지? -> 일단 setter 사용
}
