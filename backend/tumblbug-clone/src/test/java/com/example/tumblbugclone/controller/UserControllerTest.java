package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    public void clear() throws Exception {
        userRepository.clear();
        User user1 = new User("userName1", "userId1", "userPassword1", "userEmail1");
        userRepository.save(user1);
    }

    @Test
    public void mockMvc_동작_테스트() throws Exception{
        //given
        this.mockMvc.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    public void 회원가입_동작_테스트() throws Exception{
        User user2 = new User("userName2", "userId2", "userPassword2", "userEmail2");

        mockMvc.perform(post("/user/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(user2))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void 중복_Id_테스트_inWeb() throws Exception{
        //given

        User user2 = new User("userName2", "userId1", "userPassword2", "userEmail2");

        mockMvc.perform(post("/user/signup")
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

        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2))
                )
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE));
    }

    //== TODO ==//

    @Test
    public void 회원Idx로_회원_조회_inWeb() throws Exception{
        //given
        //when

        //then
    }
    //1. 회원 조회
    //2. 회원 삭제
    //3. 회원 수정
}