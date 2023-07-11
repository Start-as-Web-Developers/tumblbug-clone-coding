package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ProjectRepository projectRepository;
    
    @Test
    @Transactional
    public void 새로_좋아요(){
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);

        //when
        long likeId = likeRepository.like(user, project);

        //then
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                .getUser().getUserId())
                .isEqualTo(user.getUserId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                .getProject().getProjectId())
                .isEqualTo(project.getProjectId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .isActive())
                .isTrue();
    }

    @Test
    @Transactional
    public void 좋아요_취소(){
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        likeRepository.like(user, project);

        //when
        long likeId = likeRepository.like(user, project);

        //then
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .getUser().getUserId())
                .isEqualTo(user.getUserId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .getProject().getProjectId())
                .isEqualTo(project.getProjectId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                .isActive())
                .isFalse();
    }


    @Test
    @Transactional
    public void 다시_좋아요(){
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        long firstLikeId = likeRepository.like(user, project);
        likeRepository.like(user, project);

        //when
        long likeId = likeRepository.like(user, project);

        //then
        Assertions.assertThat(firstLikeId).isEqualTo(likeId);
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .getUser().getUserId())
                .isEqualTo(user.getUserId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .getProject().getProjectId())
                .isEqualTo(project.getProjectId());
        Assertions.assertThat(likeRepository.findLikeById(likeId)
                        .isActive())
                .isTrue();
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
}