package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.UserIdDuplicatedException;
import com.example.tumblbugclone.managedconst.UserConst;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    UserRepository userRepository = UserRepository.getUserRepository();

    @Before
    public void 유저1_저장(){
        userRepository.clear();
        User user1 = new User("userName1", "userId1", "userPassword1","userEmail1");
        try {
            userRepository.save(user1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(expected = UserIdDuplicatedException.class)
    public void 중복ID_테스트() throws Exception{
        //given
        User user2 = new User("userName2", "userId1", "userPassword2","userEmail2");

        //when
        userRepository.save(user2);
        //then
    }

    @Test(expected = UserEmailDuplicatedException.class)
    public void 중복Email_테스트() throws Exception{
        //given
        User user3 = new User("userName3", "userId3", "userPassword3","userEmail1");

        //when
        userRepository.save(user3);
        //then
    }

    @Test
    public void 정상_회원가입_테스트() throws Exception{
        //given
        User user4 = new User("userName4", "userId4", "userPassword4","userEmail4");
        //when
        userRepository.save(user4);
        //then
    }
}