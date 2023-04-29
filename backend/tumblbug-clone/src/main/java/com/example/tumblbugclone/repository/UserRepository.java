package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.managedconst.UserConst;
import com.example.tumblbugclone.model.User;

import java.util.HashMap;

public class UserRepository {
    private static Long id = 0l;
    private static final UserRepository userRepository = new UserRepository();
    private static HashMap<Long, User> userDB = new HashMap<Long, User>();

    private UserRepository(){}

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public long save(User user){
        //System.out.println(user.getUserName());
        if(userDB.isEmpty() == false) {
            if (isDuplicatedId(user.getUserId()))
                return UserConst.DUPLICATED_ID;
            if (isDuplicatedEmail(user.getUserEmail()))
                return UserConst.DUPLICATED_EMAIL;
        }
        id++;
        user.setUserIdx(id);
        userDB.put(id, user);

        return id;
    }

    private boolean isDuplicatedId(String userId){
        for(long checkId = 1l; checkId<=id; checkId++){
            try {
                User checkUser = userDB.get(checkId);
                if(checkUser.getUserId().equals(userId))
                    return true;
            }
            catch (Exception e){
                System.out.println("checkId = " + checkId);
                throw e;
            }
        }
        return false;
    }

    private boolean isDuplicatedEmail(String userEmail){
        for(long checkId = 1l; checkId<=id; checkId++){
            User checkUser = userDB.get(checkId);
            if(checkUser.getUserEmail().equals(userEmail))
                return true;
        }
        return false;
    }

    public User findUserByIdx(long idx){
        //user가 존재하지 않으면 null 반환??
        return userDB.get(idx);
    }
}
