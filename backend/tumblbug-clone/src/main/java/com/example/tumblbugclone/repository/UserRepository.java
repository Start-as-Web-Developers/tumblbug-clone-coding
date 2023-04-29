package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.UserIdDuplicatedException;
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

    public long save(User user) throws Exception{
        if(userDB.isEmpty() == false) {
            try {
                isDuplicatedId(user.getUserId());
                isDuplicatedEmail(user.getUserEmail());
            }catch (Exception e){
                throw e;
            }
        }
        id++;
        user.setUserIdx(id);
        userDB.put(id, user);

        return id;
    }

    private boolean isDuplicatedId(String userId) throws UserIdDuplicatedException {
        for(long checkId = 1l; checkId<=id; checkId++){
            User checkUser = userDB.get(checkId);
            if(checkUser.getUserId().equals(userId)){
                throw new UserIdDuplicatedException();
            }
        }
        return false;
    }

    private boolean isDuplicatedEmail(String userEmail) throws UserEmailDuplicatedException {
        for(long checkId = 1l; checkId<=id; checkId++){
            User checkUser = userDB.get(checkId);
            if(checkUser.getUserEmail().equals(userEmail))
                throw new UserEmailDuplicatedException();
        }
        return false;
    }

    public User findUserByIdx(long idx){
        return userDB.get(idx);
    }
}