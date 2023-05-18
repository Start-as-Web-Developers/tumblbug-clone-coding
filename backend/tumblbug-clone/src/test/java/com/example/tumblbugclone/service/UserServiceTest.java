
package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.userexception.UserCantModifyIdException;
import com.example.tumblbugclone.Exception.userexception.UserDTOConvertException;
import com.example.tumblbugclone.Exception.userexception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.userexception.UserIdDuplicatedException;

import com.example.tumblbugclone.dto.UserDTO;
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
        UserDTO user = new UserDTO();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        long userIdx = userService.join(user);

        //then
        Assertions.assertThat(userService.findUserByIndex(userIdx).getUserIdx()).isNotEqualTo(0);
    }


    @Test(expected = UserIdDuplicatedException.class)
    @Transactional
    public void Id가_중복되면_Exception() throws UserEmailDuplicatedException, UserIdDuplicatedException, UserDTOConvertException {
        //given
        UserDTO user = new UserDTO();
        user.setUserId("sameId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("userEmail");

        //when
        userService.join(user);

        //then
        UserDTO newUser = new UserDTO();
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
        UserDTO user = new UserDTO();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setUserPassword("userPassword");
        user.setUserEmail("sameEmail");

        //when
        userService.join(user);

        //then
        UserDTO newUser = new UserDTO();
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
        UserDTO user = make_Nth_User(1);
        user.setUserName(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_Id는_필수() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        user.setUserId(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = DataIntegrityViolationException.class)
    @Transactional
    public void 회원_비밀번호는_필수() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        user.setUserPassword(null);

        //when
        userService.join(user);

        //then
    }

    @Test(expected = UserDTOConvertException.class)
    @Transactional
    public void 회원_Email은_필수() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        user.setUserEmail(null);

        //when
        userService.join(user);

        //then
    }

    @Test
    @Transactional
    public void Id로_회원_조회() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        userService.join(user);

        //when
        UserDTO findByUserId = userService.findUserById(user.getUserId());

        //then
        Assertions.assertThat(findByUserId.getUserEmail()).isEqualTo(user.getUserEmail());

    }

    @Test
    @Transactional
    public void Index로_회원_조회() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        long userIndex = userService.join(user);

        //when
        UserDTO findUserBuIndex = userService.findUserByIndex(userIndex);

        //then
        Assertions.assertThat(user.getUserId()).isEqualTo(findUserBuIndex.getUserId());
        Assertions.assertThat(user.getUserEmail()).isEqualTo(findUserBuIndex.getUserEmail());
    }

    @Test
    @Transactional
    public void 같은_회원_두번_조회() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        long savedIndex = userService.join(user);

        //when
        UserDTO findByIndex = userService.findUserByIndex(savedIndex);
        UserDTO findById = userService.findUserById(user.getUserId());

        //then
        Assertions.assertThat(findById).isEqualTo(findByIndex);
    }

    @Test(expected = UserCantModifyIdException.class)
    @Transactional
    public void 회원_id는_수정불가() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        long userIdx = userService.join(user);
        user.setUserIdx(userIdx);

        //when
        UserDTO modifyUser = make_Nth_User(1);
        modifyUser.setUserIdx(user.getUserIdx());
        modifyUser.setUserId("modifiedId");

        //then
        userService.modify(modifyUser);
    }

    @Test
    @Transactional
    public void 회원_정보_수정성공() throws Exception{
        //given
        UserDTO user = make_Nth_User(1);
        long userIdx = userService.join(user);
        user.setUserIdx(userIdx);

        //when
        UserDTO modifyUser = make_Nth_User(1);
        modifyUser.setUserIdx(userIdx);
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
        UserDTO user = make_Nth_User(1);
        long userIndex = userService.join(user);
        user.setUserIdx(userIndex);

        //when
        userService.unregiste(user);

        //then
        Assertions.assertThat(userService.findUserByIndex(userIndex).isActive()).isFalse();
    }

    private UserDTO make_Nth_User(int n){
        UserDTO user = new UserDTO();
        String nString = Integer.toString(n);
        user.setUserId("user" + nString + "Id");
        user.setUserName("user" + nString + "Name");
        user.setUserPassword("user" + nString + "Password");
        user.setUserEmail("user" + nString + "Email");

        return user;
    }


}
