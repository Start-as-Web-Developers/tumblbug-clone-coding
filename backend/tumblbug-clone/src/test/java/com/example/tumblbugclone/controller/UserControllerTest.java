package com.example.tumblbugclone.controller;
import com.example.tumblbugclone.dto.UserLoginDTO;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Transactional
    public void 빈_DB_회원가입_동작_테스트() throws Exception{

        UserReceivingDTO user1 = make_Nth_User(1);

        mockMvc.perform(post(HttpConst.USER_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user1))
        )
                .andExpect(status().isOk());
    }


    @Test
    @Transactional
    public void 중복_Id_테스트_inWeb() throws Exception{

        //given

        UserReceivingDTO user1 = make_Nth_User(1);
        userService.join(user1);

        //when
        UserReceivingDTO user2 = make_Nth_User(2);
        user2.setUserId(user1.getUserId());

        //then
        mockMvc.perform(post(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(user2))
                )
                .andExpect(status().is(ExceptionConst.UserIdDuplicatedStatus));
    }

    @Test
    @Transactional
    public void 중복_Email_테스트_inWeb() throws Exception{

        //given

        UserReceivingDTO user1 = make_Nth_User(1);
        userService.join(user1);

        //when
        UserReceivingDTO user2 = make_Nth_User(2);
        user2.setUserEmail(user1.getUserEmail());

        //then
        mockMvc.perform(post(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(user2))
                )
                .andExpect(status().is(ExceptionConst.UserEmailDuplicatedStatus));
    }

    @Test
    @Transactional
    public void 일반DB_회원가입_테스트() throws Exception{
        //given

        UserReceivingDTO user1 = make_Nth_User(1);
        userService.join(user1);

        //when
        UserReceivingDTO user2 = make_Nth_User(2);

        //then
        mockMvc.perform(post(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void 회원정보_수정_inWeb() throws Exception{

        //given

        UserReceivingDTO user = make_Nth_User(1);
        long savedIndex = userService.join(user);

        //when
        UserReceivingDTO modifiedUser = make_Nth_User(1);

        modifiedUser.setUserIdx(savedIndex);
        modifiedUser.setUserEmail("newEmail");

        //then
        mockMvc.perform(patch(HttpConst.USER_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifiedUser)))
                .andExpect(status().isOk());
        Assertions.assertThat(userService.findUserById(user.getUserId())
                        .getUserEmail())
                .isEqualTo(modifiedUser.getUserEmail());
    }

    @Test
    @Transactional
    public void 회원Id는_변경할수_없습니다() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long savedIndex = userService.join(user);

        //when
        UserReceivingDTO modifiedUser = make_Nth_User(1);

        modifiedUser.setUserIdx(savedIndex);
        modifiedUser.setUserId("newId");

        //then
        mockMvc.perform(patch(HttpConst.USER_URI + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifiedUser)))
                .andExpect(status().is(ExceptionConst.UserCantModifyIdStatus));
    }

    @Test
    @Transactional
    public void 회원탈퇴_성공_테스트() throws Exception{
        //given

        UserReceivingDTO user = make_Nth_User(1);
        long savedIdx = userService.join(user);

        //when
        UserReceivingDTO deleteUser = make_Nth_User(1);

        deleteUser.setUserIdx(savedIdx);
        deleteUser.setActive(false);

        //then

        mockMvc.perform(delete(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteUser)))
                .andExpect(status().isOk());
        Assertions.assertThat(userService.findSendingUserByIndex(savedIdx)
                        .isActive())
                .isFalse();
    }

    @Test
    @Transactional
    public void 정상_로그인() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);
        long savedIdx = userService.join(user);

        //when
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId(user.getUserId());
        loginDTO.setUserPassword(user.getUserPassword());

        //then
        mockMvc.perform(post(HttpConst.USER_URI + HttpConst.USER_LOGIN_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
        // mock mvc 라서 그런지, 로그인 성공 상황에서 세션을 새로 생성해 주어도, 세션 관련 set-cookie를 확인 하지 못한다.
        // 해당 기능 PostMan으로 확인 완료
    }

    @Test
    public void 없는_회원_로그인() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);

        //when
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId(user.getUserId());
        loginDTO.setUserPassword(user.getUserPassword());

        //then
        mockMvc.perform(post(HttpConst.USER_URI + HttpConst.USER_LOGIN_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().is(ExceptionConst.UserCantFindStatus));
    }

    @Test
    @Transactional
    public void 잘못된_비밀번호() throws Exception{
        //given
        UserReceivingDTO user = make_Nth_User(1);
        userService.join(user);

        //when
        UserLoginDTO loginDTO = new UserLoginDTO();
        loginDTO.setUserId(user.getUserId());
        loginDTO.setUserPassword("WrongPassword");

        //then
        mockMvc.perform(post(HttpConst.USER_URI + HttpConst.USER_LOGIN_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().is(ExceptionConst.WrongPasswordStatus));
    }

    private UserReceivingDTO make_Nth_User(int N){
        UserReceivingDTO user = new UserReceivingDTO();

        user.setUserName("user" + N + "name");
        user.setUserId("user" + N + "Id");
        user.setUserEmail("user" + N + "Email");
        user.setUserPassword("user" + N + "Password");

        return user;
    }

}
