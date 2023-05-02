package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class ProjectCardControllerTest {
    private UserRepository userRepository = UserRepository.getUserRepository();
    private ProjectRepository projectRepository = ProjectRepository.getProjectRepository();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    public void 테스트용_프로젝트_생성() throws Exception {
        for(int i = 0; i<25; i++) {
            Project project = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2023-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(project);
        }
    }

    @AfterAll
    public void 테스트용_프로젝트_삭제(){
        projectRepository.clear();
    }

    @Test
    public void 첫_20개_조회() throws Exception{
        //given

        //when

        //then
    }
}