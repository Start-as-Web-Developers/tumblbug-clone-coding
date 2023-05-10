
package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.userexception.UserCantModifyIdException;
import com.example.tumblbugclone.Exception.userexception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.userexception.UserIdDuplicatedException;

import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @Transactional
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        userService.join(user);

        //then
        Assertions.assertThat(user.getUserIdx()).isNotEqualTo(0);
    }


    @Test(expected = UserIdDuplicatedException.class)
    @Transactional
    public void Id가_중복되면_Exception() throws UserEmailDuplicatedException, UserIdDuplicatedException {
        //given
        User user = new User();
        user.setUserId("sameId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        userService.join(user);

        //then
        User newUser = new User();
        user.setUserId("sameId");
        user.setUserName("user1Name");
        user.setUserPassword("user1Password");
        user.setUserEmail("user1Email");

        userService.join(newUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void Emial이_중복되면_Exception() throws Exception{
        //given
        User user = new User();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("sameEmail");

        //when
        userService.join(user);

        //then
        User newUser = new User();
        user.setUserId("user1Id");
        user.setUserName("user1Name");
        user.setUserPassword("user1Password");
        user.setUserEmail("sameEmail");

        userService.join(newUser);
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void 회원_이름은_필수() throws Exception{
        //given
        User user = make_Nth_User(1);
        user.setUserName(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void 회원_Id는_필수() throws Exception{
        //given
        User user = make_Nth_User(1);
        user.setUserId(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        User user = make_Nth_User(1);
        user.setUserPassword(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void 회원_Email은_필수() throws Exception{
        //given
        User user = make_Nth_User(1);
        user.setUserEmail(null);

        //when
        userService.join(user);

        //then
    }

    @Test
    @Transactional
    public void Id로_회원_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userService.join(user);

        //when
        User findByUserId = userService.findUserById(user.getUserId());

        //then
        Assertions.assertThat(findByUserId.getUserEmail()).isEqualTo(user.getUserEmail());

    }

    @Test
    @Transactional
    public void Index로_회원_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        long userIndex = userService.join(user);

        //when
        User findUserBuIndex = userService.findUserByIndex(userIndex);

        //then
        Assertions.assertThat(user.getUserId()).isEqualTo(findUserBuIndex.getUserId());
        Assertions.assertThat(user.getUserEmail()).isEqualTo(findUserBuIndex.getUserEmail());
    }

    @Test
    @Transactional
    public void 같은_회원_두번_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        long savedIndex = userService.join(user);

        //when
        User findByIndex = userService.findUserByIndex(savedIndex);
        User findById = userService.findUserById(user.getUserId());

        //then
        Assertions.assertThat(findById).isEqualTo(findByIndex);
    }

    @Test(expected = UserCantModifyIdException.class)
    @Transactional
    public void 회원_id는_수정불가() throws Exception{
        //given
        User user = make_Nth_User(1);
        userService.join(user);

        //when
        User modifyUser = make_Nth_User(1);
        modifyUser.setUserIdx(user.getUserIdx());
        modifyUser.setUserId("modifiedId");

        //then
        userService.modify(modifyUser);
    }

    @Test
    @Transactional
    public void 회원_정보_수정성공() throws Exception{
        //given
        User user = make_Nth_User(1);
        userService.join(user);

        //when
        User modifyUser = make_Nth_User(1);
        modifyUser.setUserIdx(user.getUserIdx());
        modifyUser.setUserImg("newImageURL");
        modifyUser.setUserPassword("newPassword");
        modifyUser.setGreeting("Hi");
        userService.modify(modifyUser);

        //then
        Assertions.assertThat(userService.findUserByIndex(user.getUserIdx())).isEqualTo(modifyUser);
    }

    @Test
    @Transactional
    public void 회원_탈퇴() throws Exception{
        //given
        User user = make_Nth_User(1);
        long userIndex = userService.join(user);

        //when
        userService.unregiste(user);

        //then
        Assertions.assertThat(userService.findUserByIndex(userIndex).isActive()).isFalse();
    }

    private User make_Nth_User(int n){
        User user = new User();
        String nString = Integer.toString(n);
        user.setUserId("user" + nString + "Id");
        user.setUserName("user" + nString + "Name");
        user.setUserPassword("user" + nString + "Password");
        user.setUserEmail("user" + nString + "Email");

        return user;
    }


}
