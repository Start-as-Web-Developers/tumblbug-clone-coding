package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.managedconst.UserConst;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    UserRepository userRepository = UserRepository.getUserRepository();

    @BeforeEach
    public void 유저1_저장(){
        User user1 = new User("userName1", "userId1", "userPassword1","userEmail1");
        userRepository.save(user1);
    }


    @Test
    public void 중복ID_테스트() throws Exception{
        //given
        User user2 = new User("userName2", "userId1", "userPassword2","userEmail2");

        //when
        //then
        Assertions.assertThat( userRepository.save(user2)).isEqualTo(UserConst.DUPLICATED_ID);
    }

    @Test
    public void 중복Email_테스트() throws Exception{
        //given
        User user2 = new User("userName2", "userId2", "userPassword2","userEmail1");

        //when
        //then
        Assertions.assertThat(userRepository.save(user2)).isEqualTo(UserConst.DUPLICATED_EMAIL);
    }

    public void isDuplicatedIdTest(){

    }
}