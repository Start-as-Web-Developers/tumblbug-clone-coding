package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.projectlistexception.StartIndexException;
import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ProjectCardServiceTest {

    @Autowired
    private ProjectCardService projectCardService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    LocalDate today = LocalDate.now();

    User user;

   public long makeBasicProject() throws Exception {

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

        return startIdx;
    }


    @Test
    @Transactional
    public void onGoing_처음부터_프로젝트_조회() throws Exception{
        //given
        makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> ongoingFromStart = projectCardService.findOngoingFromIdx(0, null);

        //then
        Assertions.assertThat(ongoingFromStart.size()).isEqualTo(20);
        for (ProjectCardDTO projectCardDTO : ongoingFromStart) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(projectRepository.findProjectById(projectId).getEndDate()).isAfter(today);
        }
    }


    @Test
    @Transactional
    public void preLaunching_처음부터_프로젝트_조회() throws Exception{
        //given
        makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> preLaunchingFromIdx = projectCardService.findPreLaunchingFromIdx(0, null, today);

        //then
        Assertions.assertThat(preLaunchingFromIdx.size()).isEqualTo(20);
        for (ProjectCardDTO projectCardDTO : preLaunchingFromIdx) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isAfter(today);
        }
    }

    @Test
    @Transactional
    public void onGoing_중간부터_조회() throws Exception{
        //given
        makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> preLaunchingFromIdx = projectCardService.findOngoingFromIdx(20, null, today);

        //then
        Assertions.assertThat(preLaunchingFromIdx.size()).isEqualTo(20);
        for (ProjectCardDTO projectCardDTO : preLaunchingFromIdx) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(projectRepository.findProjectById(projectId).getEndDate()).isAfter(today);
        }
    }

    @Test
    @Transactional
    public void preLaunching_중간부터_조회() throws Exception{
        //given
        makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> preLaunchingFromIdx = projectCardService.findPreLaunchingFromIdx(20, null, today);

        //then
        Assertions.assertThat(preLaunchingFromIdx.size()).isEqualTo(20);
        for (ProjectCardDTO projectCardDTO : preLaunchingFromIdx) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isAfter(today);
        }
    }



    @Test
    @Transactional
    public void onGoing_끝까지_조회() throws Exception{
        //given
        long startIndex = makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> ongoingFromStart = projectCardService.findOngoingFromIdx(40, null, today);

        //then
        Assertions.assertThat(ongoingFromStart.size()).isLessThan(20);
        for (ProjectCardDTO projectCardDTO : ongoingFromStart) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(projectRepository.findProjectById(projectId).getEndDate()).isAfter(today);
        }
    }


    @Test
    @Transactional
    public void preLaunching_끝까지_조회() throws Exception{
        //given
        long startIndex = makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> preLaunchingFromIdx = projectCardService.findPreLaunchingFromIdx(40, null, today);

        //then
        Assertions.assertThat(preLaunchingFromIdx.size()).isLessThan(20);
        for (ProjectCardDTO projectCardDTO : preLaunchingFromIdx) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isAfter(today);
        }
    }

    @Test(expected = StartIndexException.class)
    @Transactional
    public void onGoin_startIdx는_20의_배수() throws Exception{
        //given
        long startIndex = makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> ongoingFromStart = projectCardService.findOngoingFromIdx(30, null, today);
    }

    @Test(expected = StartIndexException.class)
    @Transactional
    public void preLaunching_startIdx는_20의_배수() throws Exception{
        //given
        long startIndex = makeBasicProject();

        //when
        ArrayList<ProjectCardDTO> preLaunchingFromIdx = projectCardService.findPreLaunchingFromIdx(30, null, today);

        //then
        Assertions.assertThat(preLaunchingFromIdx.size()).isLessThan(20);
        for (ProjectCardDTO projectCardDTO : preLaunchingFromIdx) {
            Long projectId = projectCardDTO.getProjectId();
            Assertions.assertThat(projectRepository.findProjectById(projectId).getStartDate()).isAfter(today);
        }
    }
    public Project makeOngoingProject(int i){

        Project project = new Project();
        project.setUser(user);
        project.setTitle("ongoing_project title " + Integer.toString(i));
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.of(2023, 2, 5));
        project.setEndDate(LocalDate.of(2032, 3, 5));
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        return project;
    }

    public Project  makePrelaunchingProject(int i){
        Project project = makeOngoingProject(i);

        project.setTitle("preLaunching title " + Integer.toString(i));
        project.setStartDate(LocalDate.of(2025, 2, 5));

        return project;
    }
}
