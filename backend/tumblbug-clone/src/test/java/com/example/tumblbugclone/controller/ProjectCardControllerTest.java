package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import com.example.tumblbugclone.service.ProjectCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ProjectCardControllerTest {
    private UserRepository userRepository = UserRepository.getUserRepository();
    private ProjectRepository projectRepository = ProjectRepository.getProjectRepository();
    ProjectCardService projectCardService = new ProjectCardService();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void 테스트용_데이터_생성() throws Exception {
        for(int i = 0; i<25; i++) { //1~25 : onGoing
            Project ongoingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);
        }

        for(int i = 0; i<25; i++) { //26 ~ 50 : preLaunching
            Project preLaunchingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-06-03", "2024-01-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(preLaunchingProject);
        }

        for(int i = 0; i<20; i++){ //51~90 홀수 : 진행중, 짝수 : 진행 예정
            Project ongoingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);

            Project preLaunchingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-06-03", "2023-01-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(preLaunchingProject);
        }

        User user1 = new User("userName1", "userId1", "userPassword1", "userEmail1");
        userRepository.save(user1);
    }

    @After
    public void 테스트용_프로젝트_삭제(){
        projectRepository.clear();
        userRepository.clear();
    }


    @Test
    public void 접근_테스트() throws Exception{
        //given
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI))
                .andExpect(status().isOk());
        //when

        //then
    }

    @Test
    public void onGoing_처음부터_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromStart();

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }

    @Test
    public void onGoing_20번째부터_조회() throws Exception{

        //given
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(20l);

        //when
        mockMvc.perform(get(HttpConst.PROJECT_LIST_URI + HttpConst.ON_GOING)
                        .param("start-idx", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(projectCards)));
        //then
    }

    @Test
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
    }
}