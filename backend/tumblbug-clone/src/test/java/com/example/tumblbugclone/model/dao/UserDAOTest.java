package com.example.tumblbugclone.model.dao;

import com.example.tumblbugclone.AppConfig;


import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
//@SpringBootTest
//@Transactional
public class UserDAOTest {
    @Autowired
    EntityManager em;
    UserDAO existingUser = new UserDAO();


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
        UserDAO newUser = em.find(UserDAO.class, existingUser.getUserIdx());

        //then
        Assertions.assertThat(newUser.getUserId()).isEqualTo(existingUser.getUserId());
    }

    @Test
    @Transactional
    public void 새로운_UserDAO_저장() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUser");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");

        em.persist(newUserDAO);

        //then
        Assertions.assertThat(newUserDAO.getUserIdx()).isGreaterThan(1);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_이름은_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        //newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");


        em.persist(newUserDAO);
    }


    @Test(expected = Exception.class)
    @Transactional
    public void 회원_Id는_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        //newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");


        em.persist(newUserDAO);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        //newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");


        em.persist(newUserDAO);
    }

    @Test(expected = Exception.class)
    @Transactional
    public void 회원_Email은_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        //newUserDAO.setUserEmail("newUserEmail");

        //when
        em.persist(newUserDAO);

    }
}