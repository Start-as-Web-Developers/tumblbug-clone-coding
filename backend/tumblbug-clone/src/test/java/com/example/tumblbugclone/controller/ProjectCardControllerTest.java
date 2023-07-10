package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import com.example.tumblbugclone.service.ProjectCardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@WebMvcTest
@AutoConfigureMockMvc
public class ProjectCardControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    ProjectCardService projectCardService;
    @Autowired
    ProjectCardController projectCardController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    User user;


    @Before
    public void makeBasicProject() throws Exception {

        user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        userRepository.save(user);

        long startIdx = 0l;

        for(int i = 0; i<25; i++){
            Project project = makeOngoingProject(i);
            long savedId = projectRepository.save(project);
            if(i == 0)
                startIdx = savedId;
        }
        for(int i = 25; i<50; i++){
            Project project = makePrelaunchingProject(i);
            projectRepository.save(project);
        }
        for(int i = 50; i<100; i++){
            Project project;
            if(i%2 == 0)
                project = makeOngoingProject(i);
            else
                project = makePrelaunchingProject(i);

            projectRepository.save(project);
        }
    }

    @Test
    @Transactional
    public void 접근_테스트() throws Exception{
        //given
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI))
                .andExpect(status().isOk());

        //when

        //then
    }

    @Test
    @Transactional
    public void ongoing조회는_startIdx_생략가능() throws Exception{
        //given
         MvcResult mvcResult = mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING))
                .andExpect(status().isOk())
                .andReturn();

        //when
        List<ProjectCardDTO> findDTOs = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<ProjectCardDTO>>() {});



        //then
        Assertions.assertThat(findDTOs.size()).isEqualTo(20);
        for(ProjectCardDTO card : findDTOs){
            this.assertOngoingProject(card);
        }
    }

    @Test
    @Transactional
    public void ongoing조회는_startIdx_생략가능_1() throws Exception{
        //given
        ResponseEntity<List<ProjectCardDTO>> ongoingProject = projectCardController.getOngoingProject(null, null);

        //when
        List<ProjectCardDTO> resultBody = ongoingProject.getBody();

        //then
        Assertions.assertThat(resultBody.size()).isEqualTo(20);
        for(ProjectCardDTO card : resultBody){
            this.assertOngoingProject(card);
        }
    }

    @Test
    @Transactional
    public void ongoing조회_sort적용() throws Exception{
        //given
        MvcResult mvcResult = mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING + "?sort=new"))
                .andExpect(status().isOk())
                .andReturn();

        //when
        ProjectCardDTO[] findDTOs = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProjectCardDTO[].class);

        //then
        Assertions.assertThat(findDTOs.length).isEqualTo(20);
        for(ProjectCardDTO card : findDTOs){
            this.assertOngoingProject(card);
        }
    }

    @Test
    @Transactional
    public void onGoing_20번째부터_조회() throws Exception{

        //given
        MvcResult mvcResult = mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING + "?start-idx=20"))
                .andExpect(status().isOk())
                .andReturn();

        //when
        ProjectCardDTO[] findDTOs = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProjectCardDTO[].class);

        //then
        Assertions.assertThat(findDTOs.length).isEqualTo(20);
        for(ProjectCardDTO card : findDTOs){
            this.assertOngoingProject(card);
        }
    }

    @Test
    @Transactional
    public void onGoing_끝까지_조회() throws Exception{
        //given
        MvcResult mvcResult = mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING + "?start-idx=40"))
                .andExpect(status().isOk())
                .andReturn();

        //when
        ProjectCardDTO[] findDTOs = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProjectCardDTO[].class);

        //then
        Assertions.assertThat(findDTOs.length).isNotEqualTo(20);
        for(ProjectCardDTO card : findDTOs){
            this.assertOngoingProject(card);
        }
    }

    /*@Test

    public void onGoing_80번째부터_끝까지_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(80l);

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING)
                        .param("start-idx", "80"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }
    @Test
    public void preLaunching_처음부터_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromStart();

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.PRE_LAUNCHING))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }

    @Test
    public void preLaunching_20번째부터_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromIdx(20l);

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.PRE_LAUNCHING)
                        .param("start-idx", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }

    @Test
    public void preLaunching_80번째부터_끝까지_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromIdx(80l);

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.PRE_LAUNCHING)
                        .param("start-idx", "80"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }

    @Test
    public void preLaunching_startIdx는_20의_배수여야_합니다() throws Exception{
        //given
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.PRE_LAUNCHING)
                        .param("start-idx", "35"))
                .andExpect(status().isBadRequest());
        //when

        //then
    }

    @Test
    public void onGoing_startIdx는_20의_배수여야_합니다() throws Exception{
        //given
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING)
                        .param("start-idx", "35"))
                .andExpect(status().isBadRequest());
        //when

        //then
    }*/

    private Project makeOngoingProject(int i){

        Project project = new Project();
        project.setUser(user);
        project.setTitle("ongoing_project title " + Integer.toString(i));
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(new Date(2023, 2, 5));
        project.setEndDate(new Date(2032, 3, 5));
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        return project;
    }

    private Project  makePrelaunchingProject(int i){
        Project project = makeOngoingProject(i);

        project.setTitle("preLaunching title " + Integer.toString(i));
        project.setStartDate(new Date(2025, 2, 5));

        return project;
    }

    private void assertOngoingProject(ProjectCardDTO projectCardDTO){
        Assertions.assertThat(projectCardDTO.getTitle().contains("ongoing"));
    }
}
