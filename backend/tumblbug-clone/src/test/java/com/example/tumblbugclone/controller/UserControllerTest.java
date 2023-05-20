package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.UserReceivingDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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
    public void mockMvc_동작_테스트() throws Exception{
        //given
        this.mockMvc.perform(get(HttpConst.USER_URI)).andExpect(status().isOk());
    }
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
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE));
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
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE));
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
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.CANT_MODIFY_USER_ID_MESSAGE));
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

        //then

        mockMvc.perform(delete(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteUser)))
                .andExpect(status().isOk());
        Assertions.assertThat(userService.findUserByIndex(savedIdx)
                        .isActive())
                .isFalse();
    }


   /* @Test
    public void 존재하지_않는_회원_변경() throws Exception{
        //given
        User modifyIdUser = new User("userName1", "userId1", "userPassword1", "userEmail");
        modifyIdUser.setUserIdx(2l);

        //when

        //then
        mockMvc.perform(patch(HttpConst.USER_URI + "/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyIdUser)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.NO_USER_FIND_MESSAGE));
    }*/


    
    /*@Test
    public void 이미_탈퇴한_회원_탈퇴_inWeb() throws Exception{
        //given
        User deleteUser = new User("userName1", "userId1", "userPassword1", "userEmail1");
        deleteUser.setUserIdx(1l);

        //when
        mockMvc.perform(delete(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteUser)))
                .andExpect(status().isOk());

        //then
        mockMvc.perform(delete(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deleteUser)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.UNREGISTER_USER_MESSAGE));
    }*/

    private UserReceivingDTO make_Nth_User(int N){
        UserReceivingDTO user = new UserReceivingDTO();
        user.setUserName("user" + N + "name");
        user.setUserId("user" + N + "Id");
        user.setUserEmail("user" + N + "Email");
        user.setUserPassword("user" + N + "Password");

        return user;
    }

}
