
package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.userexception.*;


import com.example.tumblbugclone.dto.UserLoginDTO;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}


    public long join(UserReceivingDTO userDTO) throws TumblbugException {
        User user = convertDTO2User(userDTO);
 
        try {
            userRepository.checkDuplication(user);
        } catch (UserEmailDuplicatedException | UserIdDuplicatedException e) {
            throw e;
        }

        long savedIndex = userRepository.save(user);

        return savedIndex;
    }


    public UserSendingDTO findSendingUserByIndex(long userIdx) throws TumblbugException {

        User findUser = null;
        try{
            findUser  = userRepository.findUserByIndex(userIdx);
        } catch (EmptyResultDataAccessException e){
            throw new UserCantFindException();
        }
        return convertUser2DTO(findUser);
    }

    public User findUserByIndex(long userIdx) throws TumblbugException {
        User user;
        try{
            user = userRepository.findUserByIndex(userIdx);
        }catch (EmptyResultDataAccessException e) {
            throw new UserCantFindException();
        }
        return user;
    }

    public UserSendingDTO findUserById(String userId) throws TumblbugException {

        User findUser = userRepository.findUserById(userId);
        if(findUser == null)
            throw new UserCantFindException();

        return convertUser2DTO(findUser);
    }


    public void unregiste(UserReceivingDTO userDTO) throws TumblbugException {
        User user = convertDTO2User(userDTO);
        user.setActive(false);
        userRepository.modify(user);
    }


    public void modify(UserReceivingDTO userDTO) throws TumblbugException {
        User user = convertDTO2User(userDTO);

        User findUser = userRepository.findUserByIndex(user.getUserIdx());

        if(findUser.getUserId().equals(user.getUserId()) == false){
            throw new UserCantModifyIdException();
        }

        Field[] declaredFields = user.getClass().getDeclaredFields();
        for(Field field : declaredFields){
            Object value;
            field.setAccessible(true);
            try {
                value = field.get(user);
            } catch (IllegalAccessException e) {
                continue;
            }

            if(value == null)
                continue;

            try {
                field.set(findUser, value);
            } catch (IllegalAccessException e) {
                continue;
            }
        }

        userRepository.modify(findUser);
    }

    public long login(UserLoginDTO user) throws TumblbugException {
        User userById;
        try {
            userById = userRepository.findUserById(user.getUserId());
        }catch (EmptyResultDataAccessException e){
            throw new UserCantFindException();
        }

        if(!userById.getUserPassword().equals(user.getUserPassword())){
            throw new WrongPasswordException();
        }

        return userById.getUserIdx();
    }

    public UserSendingDTO convertUser2DTO(User user){
        UserSendingDTO userDTO = new UserSendingDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserIdx(user.getUserIdx());
        userDTO.setUserImg(user.getUserImg());
        userDTO.setActive(user.isActive());
        userDTO.setGreeting(user.getGreeting());
        userDTO.setLastLogin(user.getLastLogin());
        return userDTO;
    }

    public User convertDTO2User(UserReceivingDTO newUserDTO) throws UserDTOConvertException {
        User user = new User();
        user.setUserIdx(newUserDTO.getUserIdx());

        if(newUserDTO.getUserName() == null){
            throw new UserDTOConvertException(HttpConst.USERNAME_IS_NULL);
        }
        user.setUserName(newUserDTO.getUserName());

        if(newUserDTO.getUserId() == null){
            throw new UserDTOConvertException(HttpConst.USERID_IS_NULL);
        }
        user.setUserId(newUserDTO.getUserId());

        if(newUserDTO.getUserPassword() == null){
            throw new UserDTOConvertException(HttpConst.USERPASSWORD_IS_NULL);
        }
        user.setUserPassword(newUserDTO.getUserPassword());

        if(newUserDTO.getUserEmail() == null){
            throw new UserDTOConvertException(HttpConst.USEREMAIL_IS_NULL);
        }
        user.setUserEmail(newUserDTO.getUserEmail());

        user.setUserImg(newUserDTO.getUserImg());
        user.setGreeting(newUserDTO.getGreeting());
        user.setLastLogin(newUserDTO.getLastLogin());
        user.setActive(newUserDTO.isActive());
        return user;
    }

}

