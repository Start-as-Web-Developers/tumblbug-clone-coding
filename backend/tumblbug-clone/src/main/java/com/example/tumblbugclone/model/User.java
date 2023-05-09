package com.example.tumblbugclone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User{

    @Id
    private Long userIdx;
    private String userName;
    private String userId;
    private String userPassword;
    private String userEmail;
    private String greeting;
    private String userImg;
    private String lastLogin;
    private boolean isActive;

    public User(String userName, String userId, String userPassword, String userEmail){
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.isActive = true;
    }

    public User copy(){
        User copyUser = new User(this.userName, this.getUserId(), this.userPassword, this.getUserEmail());
        copyUser.setUserIdx(this.userIdx);
        copyUser.setGreeting(this.greeting);
        copyUser.setUserImg(this.userImg);
        copyUser.setLastLogin(this.lastLogin);
        copyUser.setActive(this.isActive);

        return copyUser;
    }

    public boolean equals(User user){
        if(this.userIdx == null || user.userIdx == null)
            return this.userId.equals(user.getUserId());
        return this.userIdx == user.getUserIdx();

    }

    public int hashCode(){
        return super.hashCode();
    }


}
