package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.service.ProjectCardService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ProjectRepositoryListTest {


    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
            private UserRepository userRepository;
    /**
     * author : 장혁수
     */
    java.sql.Date today = new java.sql.Date(2023, 05, 23);

    @Test
    @Transactional
    public void onGoing_처음부터_프로젝트_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findOngoingList(1, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(project.getEndDate()).isAfter(today);
        }
    }

    @Test
    @Transactional
    public void onGoing_중간부터_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findOngoingList(6, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(project.getEndDate()).isAfter(today);
        }
    }


    @Test
    @Transactional
    public void preLaunching_처음부터_프로젝트_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findPrelaunchingList(1, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Date date = project.getStartDate();
            System.out.println("project.getStartDate() = " + date.getYear() + " "+ date.getMonth() + " "+date.getDate());
            Assertions.assertThat(project.getStartDate()).isAfter(today);
        }
    }

    @Test
    @Transactional
    public void preLaunching_중간부터_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findPrelaunchingList(6, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isAfter(today);
        }
    }

    /*
    @Test
    @Transactional
    public void onGoing_섞여있는_상태에서_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findOngoingList((int) startIndex + 50, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(project.getEndDate()).isAfter(today);
        }
    }

    @Test
    @Transactional
    public void preLaunching_섞여있는_상태에서_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findPrelaunchingList((int) startIndex + 50, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isEqualTo(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isAfter(today);
        }
    }
    */

    @Test
    @Transactional
    public void onGoing_끝까지_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findOngoingList(40, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isLessThan(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isBeforeOrEqualTo(today);
            Assertions.assertThat(project.getEndDate()).isAfter(today);
        }
    }


    @Test
    @Transactional
    public void preLaunching_끝까지_조회() throws Exception{
        //given
        User user = make_Nth_User(1);
        userRepository.save(user);
        long startIndex = makeBasicProject(user);

        //when
        List<Project> ongoingList = projectRepository.findPrelaunchingList(40, "", today);

        //then
        Assertions.assertThat(ongoingList.size()).isLessThan(20);
        for (Project project : ongoingList) {
            Assertions.assertThat(project.getStartDate()).isAfter(today);
        }
    }

    public long makeBasicProject(User user) throws Exception {

        long startIdx = 0l;

        for(int i = 0; i<25; i++){
            Project project = makeOngoingProject(i, user);
            long savedId = projectRepository.save(project);
            if(i == 0)
                startIdx = savedId;
        }
        for(int i = 25; i<50; i++){
            Project project = makePrelaunchingProject(i, user);
            projectRepository.save(project);
        }
        for(int i = 50; i<100; i++){
            Project project;
            if(i%2 == 0)
                project = makeOngoingProject(i, user);
            else
                project = makePrelaunchingProject(i, user);

            projectRepository.save(project);
        }

        return startIdx;
    }

    public Project makeOngoingProject(int i, User user){

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title" + Integer.toString(i));
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

    public Project  makePrelaunchingProject(int i, User user){
        Project project = makeOngoingProject(i, user);
        project.setStartDate(new Date(2025, 2, 5));

        return project;
    }

    private User make_Nth_User(int n){
        User user = new User();

        String nString = Integer.toString(n);
        user.setUserId("user" + nString + "Id");
        user.setUserName("user" + nString + "Name");
        user.setUserPassword("user" + nString + "Password");
        user.setUserEmail("user" + nString + "Email");

        return user;
    }
}