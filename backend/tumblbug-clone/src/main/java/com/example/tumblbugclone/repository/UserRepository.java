package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.UserCantFindException;
import com.example.tumblbugclone.Exception.UserCantModifyIdException;
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

    public void clear(){
        id = 0l;
        userDB.clear();
    }

    public long save(User user) throws Exception{
        System.out.println("try to save " + user.getUserName());
        System.out.println(id);
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
        System.out.println("save success : " + user.getUserName());

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

    public User findUserByIdx(long idx) throws UserCantFindException {
        if(idx > id)
            throw new UserCantFindException();
        return userDB.get(idx);
    }

    public long modify(User user) throws UserCantFindException,UserCantModifyIdException {

        Long userIdx = user.getUserIdx();
        if(userIdx == null)
            throw new UserCantFindException();
        if(userIdx > id)
            throw new UserCantFindException();

        User originalUser = userDB.get(userIdx);

        System.out.println("originalUser.getUserId().equals(user.getUserId()) = " + originalUser.getUserId().equals(user.getUserId()));
        System.out.println("originalUser.getUserId() = " + originalUser.getUserId());
        System.out.println(" = " +user.getUserId());
        if(originalUser.getUserId().equals(user.getUserId()) == false)
            throw new UserCantModifyIdException();

        userDB.put(userIdx, user);
        return userIdx;
    }
}