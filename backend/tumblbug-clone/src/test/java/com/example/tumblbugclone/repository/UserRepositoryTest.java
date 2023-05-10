package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    @Transactional
    public void 회원_저장() throws Exception{
        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        //when
        long userIdx = userRepository.save(user);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 중복ID_저장_실패_테스트() throws Exception{
        //given
        User user1 = new User();
        user1.setUserName("user1Name");
        user1.setUserId("user1Id");
        user1.setUserEmail("user1Email");
        user1.setUserPassword("user1Password");
        userRepository.save(user1);


        //when
        User user2 = new User();
        user2.setUserId(user1.getUserId());
        user2.setUserEmail("user2Email");
        user2.setUserName("user2Name");
        user2.setUserPassword("user2Password");

        userRepository.save(user2);
        //then
    }


    @Test(expected = Exception.class)
    @Transactional
    public void 중복Email_저장_실패_테스트() throws Exception{
        //given
        User user1 = new User();
        user1.setUserName("user1Name");
        user1.setUserId("user1Id");
        user1.setUserEmail("user1Email");
        user1.setUserPassword("user1Password");
        userRepository.save(user1);


        //when
        User user2 = new User();
        user2.setUserId("user2Id");
        user2.setUserEmail(user1.getUserEmail());
        user2.setUserName("user2Name");
        user2.setUserPassword("user2Password");

        userRepository.save(user2);
        //then
    }


    @Test
    @Transactional
    public void 회원_조회_테스트() throws Exception{
        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        //when
        long userIdx = userRepository.save(user);
        System.out.println(user.getUserIdx());
        User savedUser = userRepository.findUserByIdx(userIdx);

        //then
        Assertions.assertThat(userIdx).isEqualTo(savedUser.getUserIdx());

    }

    @Test
    @Transactional
    public void 없는_회원_조회_테스트() throws Exception{
        //given

        //when
        User findUser = userRepository.findUserByIdx(1000);

        //then
        Assertions.assertThat(findUser).isEqualTo(null);

    }

    @Test
    @Transactional
    public void 회원정보_수정_성공_테스트() throws Exception{
        ///given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        //when
        long userIdx = userRepository.save(user);

        user.setUserId("newUserId");

        User savedUser = userRepository.findUserByIdx(userIdx);

        //then
        Assertions.assertThat(user.getUserId()).isEqualTo(savedUser.getUserId());
    }

}