
package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.userexception.*;

import com.example.tumblbugclone.dto.UserLoginDTO;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import jakarta.servlet.http.HttpSession;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @Transactional
    public void 회원가입() throws Exception{
        //given

        UserReceivingDTO user = new UserReceivingDTO();

        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        long userIdx = userService.join(user);

        //then
        Assertions.assertThat(userService.findSendingUserByIndex(userIdx).getUserIdx()).isNotEqualTo(0);
    }


    @Test(expected = UserIdDuplicatedException.class)
    @Transactional
    public void Id가_중복되면_Exception() throws TumblbugException {
        //given

        UserReceivingDTO user = new UserReceivingDTO();

        user.setUserId("sameId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        userService.join(user);

        //then

        UserReceivingDTO newUser = new UserReceivingDTO();
        newUser.setUserId("sameId");
        newUser.setUserName("user1Name");
        newUser.setUserPassword("user1Password");
        newUser.setUserEmail("user1Email");

        userService.join(newUser);
    }

    @Test(expected = UserEmailDuplicatedException.class)
    @Transactional
    public void Emial이_중복되면_Exception() throws Exception{
        //given

        UserReceivingDTO user = new UserReceivingDTO();

        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("sameEmail");

        //when
        userService.join(user);

        //then

        UserReceivingDTO newUser = new UserReceivingDTO();
        newUser.setUserId("user1Id");
        newUser.setUserName("user1Name");
        newUser.setUserPassword("user1Password");
        newUser.setUserEmail("sameEmail");


        userService.join(newUser);
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_이름은_필수() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);

        user.setUserName(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_Id는_필수() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);

        user.setUserId(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);

        user.setUserPassword(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_Email은_필수() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);

        user.setUserEmail(null);

        //when
        userService.join(user);

        //then
    }

    @Test
    @Transactional
    public void Id로_회원_조회() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        userService.join(user);

        //when
        UserSendingDTO findByUserId = userService.findUserById(user.getUserId());


        //then
        Assertions.assertThat(findByUserId.getUserEmail()).isEqualTo(user.getUserEmail());

    }

    @Test
    @Transactional
    public void Index로_회원_조회() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long userIndex = userService.join(user);
        System.out.println(userIndex);

        //when
        UserSendingDTO findUserBuIndex = userService.findSendingUserByIndex(userIndex);


        //then
        Assertions.assertThat(user.getUserId()).isEqualTo(findUserBuIndex.getUserId());
        Assertions.assertThat(user.getUserEmail()).isEqualTo(findUserBuIndex.getUserEmail());
    }

    @Test
    @Transactional
    public void 같은_회원_두번_조회() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long savedIndex = userService.join(user);

        //when
        UserSendingDTO findByIndex = userService.findSendingUserByIndex(savedIndex);
        UserSendingDTO findById = userService.findUserById(user.getUserId());


        //then
        Assertions.assertThat(findById).isEqualTo(findByIndex);
    }

    @Test(expected = UserCantModifyIdException.class)
    @Transactional
    public void 회원_id는_수정불가() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long userIdx = userService.join(user);
        user.setUserIdx(userIdx);

        //when
        UserReceivingDTO modifyUser = make_Nth_User(1);

        modifyUser.setUserIdx(user.getUserIdx());
        modifyUser.setUserId("modifiedId");

        //then
        userService.modify(modifyUser);
    }

    @Test
    @Transactional
    public void 회원_정보_수정성공() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long userIdx = userService.join(user);
        user.setUserIdx(userIdx);

        //when
        UserReceivingDTO modifyUser = make_Nth_User(1);
        modifyUser.setUserIdx(userIdx);

        modifyUser.setUserImg("newImageURL");
        modifyUser.setUserPassword("newPassword");
        modifyUser.setGreeting("Hi");
        modifyUser.setLastLogin(LocalDate.now());
        userService.modify(modifyUser);

        //then
        UserSendingDTO findUser = userService.findSendingUserByIndex(user.getUserIdx());
        modifyUser.setLastLogin(findUser.getLastLogin());
            //new Date()와 DB에서 조회한 Date의 표현 포멧이 달라 임시로 구현
        Assertions.assertThat(findUser).isEqualTo(modifyUser);
    }

    @Test
    @Transactional
    public void 회원_탈퇴() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);

        long userIndex = userService.join(user);
        user.setUserIdx(userIndex);

        //when
        userService.unregiste(user);

        //then
        Assertions.assertThat(userService.findSendingUserByIndex(userIndex).isActive()).isFalse();
    }


    @Test
    @Transactional
    public void 정상_로그인() throws Exception{
        //given
        UserReceivingDTO savedUser = make_Nth_User(1);
        long savedIndex = userService.join(savedUser);
        savedUser.setUserIdx(savedIndex);

        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId(savedUser.getUserId());
        loginDTO.setUserPassword(savedUser.getUserPassword());

        //when
        long userIndex = userService.login(loginDTO);

        //then
        Assertions.assertThat(userIndex).isEqualTo(savedIndex);
    }

    @Test(expected = WrongPasswordException.class)
    @Transactional
    public void 잘못된_비밀번호_로그인() throws Exception{
        UserReceivingDTO savedUser = make_Nth_User(1);
        long savedIndex = userService.join(savedUser);
        savedUser.setUserIdx(savedIndex);

        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId(savedUser.getUserId());
        loginDTO.setUserPassword("WrongPassword");

        //when
        userService.login(loginDTO);

        //then
    }

    @Test(expected = UserCantFindException.class)
    @Transactional
    public void 없는_회원_로그인() throws Exception{
        //given

        //when
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId("wrongId");
        loginDTO.setUserPassword("WrongPassword");
        HttpSession session = new MockHttpSession();

        userService.login(loginDTO);
        //then
    }


    private UserReceivingDTO make_Nth_User(int n){
        UserReceivingDTO user = new UserReceivingDTO();

        String nString = Integer.toString(n);
        user.setUserId("user" + nString + "Id");
        user.setUserName("user" + nString + "Name");
        user.setUserPassword("user" + nString + "Password");
        user.setUserEmail("user" + nString + "Email");

        return user;
    }


}
