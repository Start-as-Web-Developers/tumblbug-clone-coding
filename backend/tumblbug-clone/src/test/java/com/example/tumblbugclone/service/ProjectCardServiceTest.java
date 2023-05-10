/*
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
        for(int i = 0; i<25; i++) { //1~25 : onGoing
            Project ongoingProject = new Project(1l, "", "Imgsource","카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);
        }

        for(int i = 0; i<25; i++) { //26 ~ 50 : preLaunching
            Project preLaunchingProject = new Project(1l, "", "Imgsource","카테고리", "코멘트", 35000000l, 240000l, "2023-06-03", "2024-01-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(preLaunchingProject);
        }

        for(int i = 0; i<20; i++){ //51~ 홀수 : 진행중, 짝수 : 진행 예정
            Project ongoingProject = new Project(1l, "", "Imgsource","카테고리", "코멘트", 35000000l, 240000l, "2023-04-03", "2024-05-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
            projectRepository.save(ongoingProject);

            Project preLaunchingProject = new Project(1l, "", "Imgsource","카테고리", "코멘트", 35000000l, 240000l, "2023-06-03", "2023-01-02", "String planIntro", "String planBudget", "String planSchedule", "String planTeam", "String planExplain", "String planGuide");
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
    public void onGoing_처음부터_프로젝트_조회() throws Exception{
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
    public void onGoing_6번부터_20개_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(6l);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }


    @Test
    public void onGoing_섞여있는_상태에서_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(51l);

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
        ArrayList<ProjectCard> projectCards = projectCardService.findOngoingFromIdx(60l);

        //then
        Assertions.assertThat(projectCards.size()).isNotEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.after(today, projectCard.getEndDate())).isFalse();
        }
    }

    @Test
    public void preLaunching_처음부터_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromStart();

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.before(today, projectCard.getStartDate())).isTrue();
        }
    }

    @Test
    public void preLaunching_31번부터_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromIdx(31l);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.before(today, projectCard.getStartDate())).isTrue();
        }
    }

    @Test
    public void preLaunching_섞여있는_상태에서_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromIdx(51l);

        //then
        Assertions.assertThat(projectCards.size()).isEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.before(today, projectCard.getStartDate())).isTrue();
        }
    }

    @Test
    public void preLaunching_60번부터_끝까지_조회() throws Exception{
        //given
        String today = Callendar.getTodayString();

        //when
        ArrayList<ProjectCard> projectCards = projectCardService.findPreLaunchingFromIdx(60l);

        //then
        Assertions.assertThat(projectCards.size()).isNotEqualTo(ProjectConst.PROJECT_CARDS_MAX_SIZE);
        for (ProjectCard projectCard : projectCards) {
            Assertions.assertThat(Callendar.before(today, projectCard.getStartDate())).isTrue();
        }
    }
}*/
