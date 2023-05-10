package com.example.tumblbugclone.repository;


import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ProjectRepositoryTest {

    @Autowired ProjectRepository projectRepository;

    @Test
    @Transactional
    public void save() throws Exception{

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

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

        // when
        long projectId = projectRepository.save(project);

    }

    @Test
    @Transactional
    public void findProjectById() throws Exception{
        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

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

        //when
        long savedProjectIndex = projectRepository.save(project);
        Project findedByIndex = projectRepository.findProjectById(savedProjectIndex);
        Project findedById = projectRepository.findProjectById(project.getProjectId());

        //then
        Assertions.assertThat(findedByIndex.getProjectId()).isEqualTo(findedById.getProjectId());

    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void findProjectById_EX() throws Exception{

        // given

        // when
        Project findProject = projectRepository.findProjectById(1L);

    }

    @Test
    @Transactional
    public void modify() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

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

        //when
        projectRepository.save(project);
        project.setTitle("newTitle");

        long projectId = projectRepository.modify(project);
        Project savedProject = projectRepository.findProjectById(projectId);

        //then
        Assertions.assertThat(project.getProjectId()).isEqualTo(savedProject.getProjectId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void delete() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

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

        //when
        long projectId = projectRepository.save(project);
        
        //then
        projectRepository.delete(projectId);
        projectRepository.findProjectById(projectId);

    }


}
