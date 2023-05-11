
package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.userexception.*;

import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}


    public long join(User user) throws UserEmailDuplicatedException, UserIdDuplicatedException {
        try {
            userRepository.checkDuplication(user);
        } catch (UserEmailDuplicatedException | UserIdDuplicatedException e) {
            throw e;
        }
        long savedIndex = userRepository.save(user);

        return savedIndex;
    }

    public long login(String userId, String userPassword) throws UserCantFindException, WrongPasswordException {
        User userById;
        try {
            userById = userRepository.findUserById(userId);
        }catch (EmptyResultDataAccessException e){
            throw new UserCantFindException();
        }

        if(userById.getUserPassword().equals(userPassword) == false){
            throw new WrongPasswordException();
        }

        return userById.getUserIdx();
    }


    public User findUserByIndex(long userIdx){

        User findUser = userRepository.findUserByIndex(userIdx);

        return findUser;
    }

    public User findUserById(String userId){
        User findUser = userRepository.findUserById(userId);

        return findUser;
    }

    public void unregiste(User user){
        User findUser = userRepository.findUserByIndex(user.getUserIdx());
        user.setActive(false);
        userRepository.modify(user);
    }

    public void modify(User user) throws UserCantModifyIdException {
        User findUser = userRepository.findUserByIndex(user.getUserIdx());

        if(findUser.getUserId().equals(user.getUserId()) == false){
            throw new UserCantModifyIdException();
        }

        userRepository.modify(user);
    }


}

