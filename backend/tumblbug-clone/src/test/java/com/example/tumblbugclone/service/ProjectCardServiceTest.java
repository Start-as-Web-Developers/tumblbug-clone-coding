package com.example.tumblbugclone.service;

import com.example.tumblbugclone.managedconst.ProjectConst;
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
        for(int i = 0; i<25; i++) { //1~25
            Project ongoingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);
        }

        for(int i = 0; i<25; i++) { //26 ~ 50
            Project endProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2023-0-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(endProject);
        }

        for(int i = 0; i<20; i++){ //51~ 홀수 : 진행중, 짝수 : 종료
            Project ongoingProject = new Project(1l, "", "카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);

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
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromStart();

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
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
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isTrue();
        }
    }

    @Test
    public void onGoing_6번부터_20개_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(6);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void end_31번부터_20개_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findEndFromIdx(31);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isTrue();
        }
    }

    @Test
    public void 섞여있는_상태에서_onGoing_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(51);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void 섞여있는_상태에서_end_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(51);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void onGoing_60번부터_끝까지_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(60);

        //then
        Assertions.assertThat(projectCards.size()).isNotEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void end_60번부터_끝까지_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(60);

        //then
        Assertions.assertThat(projectCards.size()).isNotEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }
}