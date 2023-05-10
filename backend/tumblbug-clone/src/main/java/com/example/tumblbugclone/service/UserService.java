package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.userexception.UserCantModifyIdException;
import com.example.tumblbugclone.Exception.userexception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.userexception.UserIdDuplicatedException;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository = userRepository;}

    public long join(User user) throws UserEmailDuplicatedException, UserIdDuplicatedException {
        try {
            userRepository.checkDuplication(user);
        }catch (UserEmailDuplicatedException | UserIdDuplicatedException e){
            throw e;
        }
        long userIdx = userRepository.save(user);

        return userIdx;
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
