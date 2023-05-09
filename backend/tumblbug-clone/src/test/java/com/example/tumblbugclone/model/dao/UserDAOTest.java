package com.example.tumblbugclone.model.dao;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@DataJpaTest
@Transactional
public class UserDAOTest {
    static EntityManagerFactory emf;
    static EntityManager em;

    @BeforeClass
    public static void EntityManager_생성() throws Exception{
        emf = Persistence.createEntityManagerFactory("tumblbug");
    }

    @BeforeClass
    public static void 기존_데이터_생성() throws Exception{
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        UserDAO existingUser = new UserDAO();
        existingUser.setUserId("userId1");
        existingUser.setUserName("user1");
        existingUser.setUserPassword("userPassword1");
        existingUser.setUserEmail("userEmail1");

        try {
            transaction.begin();
            em.persist(existingUser);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }finally {
            em.close();
        }
    }

    @AfterClass
    public static void 테이블_초기화() throws Exception{
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        em.clear();
    }

    @Test
    public void 기존_회원_데이터_검증() throws Exception{
        //given
        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        UserDAO existingUser = null;

        //when
        try {
            transaction.begin();
            existingUser = em.find(UserDAO.class, 1);
        } catch (Exception e) {

        }finally {
            em.close();
        }

        //then
        Assertions.assertThat(existingUser.getUserId()).isEqualTo("userId1");
    }

    @Test
    public void 새로운_UserDAO_저장() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUser");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");

        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        //when
        try{
            transaction.begin();
            em.persist(newUserDAO);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            em.close();
        }

        //then
        Assertions.assertThat(newUserDAO.getUserIdx()).isEqualTo(2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void 회원_이름은_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        //newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");

        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        //when
        try {
            transaction.begin();
            em.persist(newUserDAO);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }finally {
            em.close();
        }
    }


    @Test(expected = ConstraintViolationException.class)
    public void 회원_Id는_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        //newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");

        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        //when
        try {
            transaction.begin();
            em.persist(newUserDAO);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        //newUserDAO.setUserPassword("newUserPassword");
        newUserDAO.setUserEmail("newUserEmail");

        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        //when
        try {
            transaction.begin();
            em.persist(newUserDAO);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }finally {
            em.close();
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void 회원_Email은_필수() throws Exception{
        //given
        UserDAO newUserDAO = new UserDAO();
        newUserDAO.setUserName("newUserName");
        newUserDAO.setUserId("newUserId");
        newUserDAO.setUserPassword("newUserPassword");
        //newUserDAO.setUserEmail("newUserEmail");

        em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        //when
        try {
            transaction.begin();
            em.persist(newUserDAO);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }finally {
            em.close();
        }

    }
}