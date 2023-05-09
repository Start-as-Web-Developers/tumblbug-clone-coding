package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.userexception.*;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryTest {

    //UserRepository userRepository = UserRepository.getUserRepository();

    /*@Before
    public void 유저1_저장(){
        User user1 = new User("userName1", "userId1", "userPassword1","userEmail1");
        try {
            userRepository.save(user1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*@After
    public void DB_초기화(){
        userRepository.clear();
    }*/


    /*@Test(expected = UserIdDuplicatedException.class)
    public void 중복ID_테스트() throws Exception{
        //given
        User user2 = new User("userName2", "userId1", "userPassword2","userEmail2");

        //when
        userRepository.save(user2);
        //then
    }*/

    /*@Test(expected = UserEmailDuplicatedException.class)
    public void 중복Email_테스트() throws Exception{
        //given
        User user3 = new User("userName3", "userId3", "userPassword3","userEmail1");

        //when
        userRepository.save(user3);
        //then
    }*/

    /*@Test
    public void 정상_회원가입_테스트() throws Exception{
        //given
        User user4 = new User("userName4", "userId4", "userPassword4","userEmail4");
        //when
        userRepository.save(user4);
        //then
    }*/

   /* @Test
    public void 회원_조회_테스트() throws Exception{
        //given
        User user5 = new User("userName5", "userId5", "userPassword5", "userEmail5");
        //when
        long user5Idx = userRepository.save(user5);
        User findedUser = userRepository.findUserByIdx(user5Idx);
        //then

        Assertions.assertThat(findedUser).isEqualTo(user5);

    }*/

    /*@Test(expected = UserCantFindException.class)
    public void 없는_회원_조회_테스트() throws Exception{
        //given
        User user6 = new User("userName6", "userId6", "userPassword6", "userEmail6");
        long user6Idx = userRepository.save(user6);

        //when
        User userByIdx = userRepository.findUserByIdx(user6Idx + 1);

        //then
    }*/

    /*@Test
    public void 회원정보_수정_성공_테스트() throws Exception{
        //given
        User user7 = new User("userName7", "userId7", "userPassword7", "userEmail7");
        long user7Idx = userRepository.save(user7);

        //when
        user7.setGreeting("user7의 인삿말");
        long modifiedUserIdx = userRepository.modify(user7);
        User modifedUser = userRepository.findUserByIdx(modifiedUserIdx);

        //then
        Assertions.assertThat(user7).isEqualTo(modifedUser);
    }*/

    /*@Test(expected = UserCantModifyIdException.class)
    public void 회원의_Id는_수정할수_없습니다() throws Exception{
        //given
        User user8 = new User("userName8", "userId8", "userPasswork8", "userEmail8");
        long user8Idx = userRepository.save(user8);

        //when
        User modifiedUser = new User("userName8", "anotherId", "userPasswork8", "userEmail8");

        modifiedUser.setUserIdx(user8Idx);

        userRepository.modify(modifiedUser);
        //then
    }*/

    /*@Test(expected = UnregisterUserException.class)
    public void 회원_탈퇴_테스트() throws Exception{
        //given

        User user = new User("userName2", "userId2", "userPassword2", "userEmail2");
        long userIdx = userRepository.save(user);

        //when
        userRepository.unregister(userIdx);

        //then
        User unregisteredUser = userRepository.findUserByIdx(userIdx);
    }*/

    /*@Test(expected = UnregisterUserException.class)
    public void 이미_탈퇴한_회원_탈퇴_테스트() throws Exception{
        User user = new User("userName2", "userId2", "userPassword2", "userEmail2");
        long userIdx = userRepository.save(user);

        //when
        userRepository.unregister(userIdx);

        //then
        userRepository.unregister(userIdx);
    }*/

}