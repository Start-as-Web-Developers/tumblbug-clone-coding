package com.example.tumblbugclone.service;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class LikeServiceTest {
    @Autowired
    LikeService likeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    @Transactional
    public void 좋아요_in_service() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        //when
        likeService.like(userIdx, projectId);

        //then
        Assertions.assertThat(likeService.isLike(userIdx, projectId)).isTrue();
    }

    @Test
    @Transactional
    public void 좋아요취소_in_service() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        //when
        likeService.like(userIdx, projectId);
        likeService.like(userIdx, projectId);

        //then
        Assertions.assertThat(likeService.isLike(userIdx, projectId)).isFalse();
    }

    @Test
    @Transactional
    public void 다시좋아요_in_service() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        //when
        likeService.like(userIdx, projectId);
        likeService.like(userIdx, projectId);
        likeService.like(userIdx, projectId);

        //then
        Assertions.assertThat(likeService.isLike(userIdx, projectId)).isTrue();
    }

    @Test
    @Transactional
    public void 여러명_좋아요_in_service() throws Exception{
        //given
        User user1 = makeNthUser(1);
        User user2 = makeNthUser(2);
        User user3 = makeNthUser(3);
        long user1Idx = userRepository.save(user1);
        long user2Idx = userRepository.save(user2);
        long user3Idx = userRepository.save(user3);
        Project project = makeProject(user1);
        long projectId = projectRepository.save(project);

        //when
        likeService.like(user1Idx, projectId);
        likeService.like(user2Idx, projectId);
        likeService.like(user3Idx, projectId);

        //then
        Assertions.assertThat(likeService.countProjectLike(projectId)).isEqualTo(3);
    }


    private User makeNthUser(int i){
        User user = new User();
        user.setUserId("user" + i + "Id");
        user.setUserName("user" + i + "Name");
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
}