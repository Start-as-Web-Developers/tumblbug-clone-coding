package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.CommentDTO;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.ProjectUpdateRepository;
import com.example.tumblbugclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@WebMvcTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired UserRepository userRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired ProjectUpdateRepository updateRepository;

    String basicString = "comment";
    String modifyString = "modifyString";
    String commentURL;
    String updateURL;
    long authUserIdx;
    long unauthUserIdx;
    long updateId;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void 댓글_작성() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        //when
        MvcResult saveResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = saveResult.getResponse().getContentAsString();

        //then
        MvcResult mvcResult = mockMvc.perform(get(this.updateURL)).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ProjectUpdateDTO dto = objectMapper.readValue(contentAsString, ProjectUpdateDTO.class);
        Assertions.assertThat(dto.getComments().size()).isEqualTo(1);

        CommentDTO commentDTO = dto.getComments().get(0);
        Assertions.assertThat(commentDTO.getComment()).isEqualTo(basicString);
        Assertions.assertThat(commentDTO.getUser().getUserIdx()).isEqualTo(authUserIdx);
        Assertions.assertThat(commentDTO.getId()).isEqualTo(Long.parseLong(commentId));
    }

    @Test
    @Transactional
    public void 권한_없이_댓글_작성() throws Exception{
        //given
        init();

        //when
        mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void 댓글_수정() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);
        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //when
        mockMvc.perform(patch(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();

        //then
        MvcResult result = mockMvc.perform(get(this.updateURL)).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ProjectUpdateDTO updateDTO = objectMapper.readValue(contentAsString, ProjectUpdateDTO.class);

        Assertions.assertThat(updateDTO.getComments().size()).isEqualTo(1);
        CommentDTO comment = updateDTO.getComments().get(0);
        Assertions.assertThat(comment.getComment()).isEqualTo(modifyString);
        Assertions.assertThat(comment.getId()).isEqualTo(Long.parseLong(commentId));

    }

    @Test
    @Transactional
    public void 세션_없이_댓글_수정() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        //when
        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //then
        mockMvc.perform(patch(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void 잘못된_권한_댓글_수정() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        //when
        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //then
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.unauthUserIdx);
        mockMvc.perform(patch(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString)
                        .session(session))
                .andExpect(status().is(ExceptionConst.UnauthorizedUser));
    }

    @Test
    @Transactional
    public void 댓글_삭제() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //when
        mockMvc.perform(delete(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString)
                        .session(session))
                .andExpect(status().isOk());

        //then
        MvcResult result = mockMvc.perform(get(this.updateURL)).andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        ProjectUpdateDTO updateDTO = objectMapper.readValue(contentAsString, ProjectUpdateDTO.class);

        Assertions.assertThat(updateDTO.getComments().size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void 세션_없이_댓글_삭제() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        //when
        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //then
        mockMvc.perform(delete(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void 잘못된_권한_댓글_삭제() throws Exception{
        //given
        init();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.authUserIdx);

        //when
        MvcResult mvcResult = mockMvc.perform(post(this.commentURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(basicString)
                        .session(session))
                .andExpect(status().isOk())
                .andReturn();
        String commentId = mvcResult.getResponse().getContentAsString();

        //then
        session.setAttribute(HttpConst.SESSION_USER_INDEX, this.unauthUserIdx);
        mockMvc.perform(delete(this.commentURL + "/" + commentId)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyString)
                        .session(session))
                .andExpect(status().is(ExceptionConst.UnauthorizedUser));
    }

    private void init(){
        User user1 = makeNthUser(1);
        this.authUserIdx = userRepository.save(user1);
        User user2 = makeNthUser(2);
        this.authUserIdx = userRepository.save(user2);
        Project project = makeProject(user1);
        long projectIdx = projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user1, project);
        this.updateId = updateRepository.save(project, update);
        this.updateURL = (HttpConst.UPDATE_URI+HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectIdx))
                .replace("{update-id}", Long.toString(this.updateId));
        this.commentURL = HttpConst.UPDATE_COMMENT_URI
                .replace("{project-id}", Long.toString(projectIdx))
                .replace("{update-id}", Long.toString(this.updateId));
    }
    private User makeNthUser(int i){
        User user = new User();
        user.setUserId("user" + i + "Id");
        user.setUserName("user" +
                i + "Name");
        user.setUserEmail("user" + i + "Email");
        user.setUserPassword("user" + i + "Password");

        return user;
    }

    private Project makeProject(User user) {
        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        return project;
    }

    private ProjectUpdate makeUpdate(User user, Project project){
        ProjectUpdate update = new ProjectUpdate();
        update.setProject(project);
        update.setUpdateDate(new Date());
        update.setModified(false);
        update.setCreater(user);
        update.setContent("");

        return update;
    }
}