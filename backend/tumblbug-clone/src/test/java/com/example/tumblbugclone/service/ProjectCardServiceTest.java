package com.example.tumblbugclone.service;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProjectCardServiceTest {

    private UserRepository userRepository = UserRepository.getUserRepository();
    private ProjectRepository projectRepository = ProjectRepository.getProjectRepository();
    private ProjectCardService projectCardService = new ProjectCardService();

    @Before
    public void 테스트용_데이터_생성() throws Exception {
        for(int i = 0; i<25; i++) {
            Project ongoingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);
        }

        for(int i = 0; i<25; i++) {
            Project endProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2023-0-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(endProject);
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
    public void 첫_20개_프로젝트_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findFirstOngoingProject();

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(20);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void 종료_프로젝트_첫_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findFirstEndProject();

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(20);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isTrue();
        }
    }
}