package com.example.tumblbugclone.model.dao;


import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
//@SpringBootTest
//@Transactional
public class UserTest {
    @Autowired
    EntityManager em;
    User existingUser = new User();


    @Before
    @Transactional
    public void 기존_데이터_생성() throws Exception{
        existingUser.setUserId("userId1");
        existingUser.setUserName("user1");
        existingUser.setUserPassword("userPassword1");
        existingUser.setUserEmail("userEmail1");

        em.persist(existingUser);
        //System.out.println("existingUser.getUserIdx() = " + existingUser.getUserIdx());
    }


    @Test
    @Transactional
    public void 기존_회원_데이터_검증() throws Exception{
        //given
        //UserDAO existingUser = null;

        //when
        User newUser = em.find(User.class, existingUser.getUserIdx());

        //then
        Assertions.assertThat(newUser.getUserId()).isEqualTo(existingUser.getUserId());
    }

    @Test
    @Transactional
    public void 새로운_UserDAO_저장() throws Exception{
        //given
        User newUser = new User();
        newUser.setUserName("newUser");
        newUser.setUserId("newUserId");
        newUser.setUserPassword("newUserPassword");
        newUser.setUserEmail("newUserEmail");

        em.persist(newUser);

        //then
        Assertions.assertThat(newUser.getUserIdx()).isGreaterThan(1);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_이름은_필수() throws Exception{
        //given
        User newUser = new User();
        //newUserDAO.setUserName("newUserName");
        newUser.setUserId("newUserId");
        newUser.setUserPassword("newUserPassword");
        newUser.setUserEmail("newUserEmail");


        em.persist(newUser);
    }


    @Test(expected = Exception.class)
    @Transactional
    public void 회원_Id는_필수() throws Exception{
        //given
        User newUser = new User();
        newUser.setUserName("newUserName");
        //newUserDAO.setUserId("newUserId");
        newUser.setUserPassword("newUserPassword");
        newUser.setUserEmail("newUserEmail");


        em.persist(newUser);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        User newUser = new User();
        newUser.setUserName("newUserName");
        newUser.setUserId("newUserId");
        //newUserDAO.setUserPassword("newUserPassword");
        newUser.setUserEmail("newUserEmail");


        em.persist(newUser);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_Email은_필수() throws Exception{
        //given
        User newUser = new User();
        newUser.setUserName("newUserName");
        newUser.setUserId("newUserId");
        newUser.setUserPassword("newUserPassword");
        //newUserDAO.setUserEmail("newUserEmail");

        //when
        em.persist(newUser);

    }
}