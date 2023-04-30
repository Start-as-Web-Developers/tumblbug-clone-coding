package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    private UserRepository userRepository = UserRepository.getUserRepository();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void 기존_회원_저장() throws Exception {
        User user1 = new User("userName1", "userId1", "userPassword1", "userEmail1");
        userRepository.save(user1);
    }

    @After
    public void clear(){

        userRepository.clear();
    }

    @Test
    public void mockMvc_동작_테스트() throws Exception{
        //given
        this.mockMvc.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    public void 회원가입_동작_테스트() throws Exception{
        User user2 = new User("userName2", "userId2", "userPassword2", "userEmail2");

        mockMvc.perform(post(HttpConst.USER_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user2))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void 중복_Id_테스트_inWeb() throws Exception{
        //given

        User user2 = new User("userName2", "userId1", "userPassword2", "userEmail2");

        mockMvc.perform(post(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(user2))
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE));
    }

    @Test
    public void 중복_Email_테스트_inWeb() throws Exception{
        //given

        User user2 = new User("userName2", "userId2", "userPassword2", "userEmail1");

        mockMvc.perform(post(HttpConst.USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2))
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE));
    }

    //== TODO ==//

    @Test
    public void 회원정보_수정_inWeb() throws Exception{

        //given

        User modifiedUser = new User("userName1", "userId1", "userPassword1", "userEmail1");

        //when
        modifiedUser.setUserIdx(1l);
        modifiedUser.setUserName("새 이름");
        modifiedUser.setGreeting("인삿말");
        modifiedUser.setUserImg("새로운 이미지 주소");
        modifiedUser.setUserPassword("변경된 비밀번호");

        //then
        mockMvc.perform(patch("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifiedUser)))
                .andExpect(status().isOk());
    }

    @Test
    public void 회원Id는_변경할수_없습니다() throws Exception{
        //given
        User modifyIdUser = new User("userName1", "userId1", "userPassword1", "userEmail");
        modifyIdUser.setUserIdx(1l);

        //when
        modifyIdUser.setUserId("newUserId");

        //then
        mockMvc.perform(patch("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyIdUser)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.CANT_MODIFY_USER_ID_MESSAGE));
    }

    @Test
    public void 존재하지_않는_회원_변경() throws Exception{
        //given
        User modifyIdUser = new User("userName1", "userId1", "userPassword1", "userEmail");
        modifyIdUser.setUserIdx(2l);

        //when

        //then
        mockMvc.perform(patch("/user/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyIdUser)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.NO_USER_FIND_MESSAGE));
    }
    //1. 회원 조회
    //2. 회원 삭제

}