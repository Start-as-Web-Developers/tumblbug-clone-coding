package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.User;

import java.util.HashMap;

public class UserRepository {
    static Long id = 0l;
    static UserRepository userRepository;
    static HashMap<Long, User> userDB = new HashMap<Long, User>();

    public UserRepository(){}

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public void save(User user){

    }

    public boolean checkDuplicatedId(String userId){
        for(long checkId = 1l; checkId<=id; checkId++){
            User checkUser = userDB.get(checkId);
            if(checkUser.getUserId().equals(userId))
                return false;
        }
        return true;
    }

    public boolean checkDuplicatedEmail(String userEmail){
        for(long checkId = 1l; checkId<=id; checkId++){
            User checkUser = userDB.get(checkId);
            if(checkUser.getUserEmail().equals(userEmail))
                return false;
        }
        return true;
    }


}
