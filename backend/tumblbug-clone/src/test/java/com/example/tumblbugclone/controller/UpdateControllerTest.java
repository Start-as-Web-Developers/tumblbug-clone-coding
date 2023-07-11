package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoSession;
import org.mockito.internal.framework.DefaultMockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@WebMvcTest
@AutoConfigureMockMvc
public class UpdateControllerTest {

    @Autowired UpdateController updateController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void update_저장() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        String url = HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId));

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        //when

        //then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Project Update")
                        .session(session))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void 세션_없이_update_저장() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        String url = HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId));


        //when

        //then
        mockMvc.perform(post(url)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("ProjectUpdate"))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void 존재하지_않는_회원이_update_저장() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        String url = HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId));

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex+1);

        //when

        //then
        mockMvc.perform(post(url)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andExpect(status().is(ExceptionConst.UserCantFindStatus));
    }

    @Test
    @Transactional
    public void update_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);
        String content = "Content";

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(content)
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        mvcResult = mockMvc.perform(get((HttpConst.UPDATE_URI+HttpConst.UPDATE_ID)
                        .replace("{project-id}", Long.toString(projectId))
                        .replace("{update-id}", Long.toString(updateIndex))))
                .andExpect(status().isOk())
                .andReturn();

        ProjectUpdateDTO findDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProjectUpdateDTO.class);

        //then
        Assertions.assertThat(findDTO.getId()).isEqualTo(updateIndex);
        Assertions.assertThat(findDTO.getContent()).isEqualTo(content);
        Assertions.assertThat(findDTO.getCreater().getUserIdx()).isEqualTo(userIndex);
    }

    @Test
    @Transactional
    public void 없는_update_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        mockMvc.perform(get((HttpConst.UPDATE_URI+HttpConst.UPDATE_ID)
                        .replace("{project-id}", Long.toString(projectId))
                        .replace("{update-id}", Long.toString(updateIndex+1))))
                .andExpect(status().is(ExceptionConst.CantFindUpdate));
    }

    @Test
    @Transactional
    public void 잘못된_projectId로_update_단건조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        mockMvc.perform(get((HttpConst.UPDATE_URI+HttpConst.UPDATE_ID)
                        .replace("{project-id}", Long.toString(projectId+1))
                        .replace("{update-id}", Long.toString(updateIndex))))
                .andExpect(status().is(ExceptionConst.NotMatchProjectId));
    }

    @Test
    @Transactional
    public void update_수정성공() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String modifyContent = "Modify content";
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        MvcResult modifiedResult = mockMvc.perform(patch(updateURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyContent)
                        .session(session))
                .andExpect(status().isOk()).andReturn();
        ProjectUpdateDTO modifiedDTO = objectMapper.readValue(modifiedResult.getResponse().getContentAsString(), ProjectUpdateDTO.class);

        MvcResult findResult = mockMvc.perform(get(updateURL))
                .andExpect(status().isOk()).andReturn();
        ProjectUpdateDTO findDTO = objectMapper.readValue(findResult.getResponse().getContentAsString(), ProjectUpdateDTO.class);

        //then

        Assertions.assertThat(modifiedDTO.getContent()).isEqualTo(modifyContent);
        Assertions.assertThat(modifiedDTO).isEqualTo(findDTO);
    }

    @Test
    @Transactional
    public void 잘못된_권한_update_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String modifyContent = "Modify content";
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex+1);
        mockMvc.perform(patch(updateURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyContent)
                        .session(session))
                .andExpect(status().is(ExceptionConst.UnauthorizedUser));
    }

    @Test
    @Transactional
    public void 세션없이_update_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String modifyContent = "Modify content";
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        mockMvc.perform(patch(updateURL)
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(modifyContent))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void update_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);
        mockMvc.perform(delete(updateURL)
                        .session(session))
                .andExpect(status().isOk());

        mockMvc.perform(get(updateURL))
                .andExpect(status().is(ExceptionConst.CantFindUpdate));
    }

    @Test
    @Transactional
    public void 세션_없이_update_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);
        mockMvc.perform(delete(updateURL))
                .andExpect(status().is(ExceptionConst.LoginRequiredStatus));
    }

    @Test
    @Transactional
    public void 잘못된_권한_update_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        MvcResult mvcResult = mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(" ")
                        .session(session))
                .andReturn();
        long updateIndex = Long.parseLong(mvcResult.getResponse().getContentAsString());

        //when
        String updateURL = (HttpConst.UPDATE_URI + HttpConst.UPDATE_ID)
                .replace("{project-id}", Long.toString(projectId))
                .replace("{update-id}", Long.toString(updateIndex));

        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex+1);
        mockMvc.perform(delete(updateURL)
                        .session(session))
                .andExpect(status().is(ExceptionConst.UnauthorizedUser));
    }

    @Test
    @Transactional
    public void updateList_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);

        for(int i = 0; i<3; i++) {
            mockMvc.perform(post(HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId)))
                            .contentType(MediaType.TEXT_PLAIN)
                            .content(" ")
                            .session(session))
                    .andReturn();
        }

        //when
        String listUrl = HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId));

        MvcResult listResult = mockMvc.perform(get(listUrl))
                .andExpect(status().isOk())
                .andReturn();

        List<ProjectUpdateDTO> updateDTOS = objectMapper.readValue(listResult.getResponse().getContentAsString(), new TypeReference<List<ProjectUpdateDTO>>() {});

        //then
        Assertions.assertThat(updateDTOS.size()).isEqualTo(3);
        for (int i = 0; i < 3; i++) {
            Assertions.assertThat(updateDTOS.get(i).getProjectId()).isEqualTo(projectId);
        }
    }

    @Test
    @Transactional
    public void 빈_updateList_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);


        //when
        String listUrl = HttpConst.UPDATE_URI.replace("{project-id}", Long.toString(projectId));

        mockMvc.perform(get(listUrl))
                .andExpect(status().is(ExceptionConst.CantFindUpdate));
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
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        return project;
    }

    private ProjectUpdateDTO makeUpdate(){
        ProjectUpdateDTO update = new ProjectUpdateDTO();
        update.setUpdateDate(LocalDate.now());
        update.setModified(false);
        update.setContent("");

        return update;
    }
}