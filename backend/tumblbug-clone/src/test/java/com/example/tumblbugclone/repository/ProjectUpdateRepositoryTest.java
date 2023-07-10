package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ProjectUpdateRepositoryTest {
    @Autowired
    ProjectUpdateRepository projectUpdateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    @Transactional
    public void Update_저장() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);

        //when
        ProjectUpdate projectUpdate = makeUpdate(user, project);
        long updateId = projectUpdateRepository.save(project, projectUpdate);

        //then
        Assertions.assertThat(projectRepository.findProjectById(projectId)
                        .getUpdate().size())
                .isEqualTo(1);
        Assertions.assertThat(projectUpdateRepository.findById(updateId)
                        .getProject().getProjectId())
                .isEqualTo(projectId);
        Assertions.assertThat(projectUpdateRepository.findById(updateId)
                        .getCreater().getUserIdx())
                .isEqualTo(userIdx);
    }


    @Test
    @Transactional
    public void Update_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdate projectUpdate = makeUpdate(user, project);
        long updateId = projectUpdateRepository.save(project, projectUpdate);

        //when

        String modifyString = "modifiedContent";
        projectUpdate.setContent(modifyString);
        projectUpdate.setModified(true);
        projectUpdateRepository.update(projectUpdate);

        //then
        Assertions.assertThat(projectUpdateRepository.findById(updateId)
                        .getContent())
                .isEqualTo(modifyString);
        Assertions.assertThat(projectUpdateRepository.findById(updateId)
                        .isModified())
                .isTrue();
    }
    
    @Test
    @Transactional
    public void update_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate projectUpdate = makeUpdate(user, project);
        long updateId = projectUpdateRepository.save(project, projectUpdate);

        //when
        projectUpdateRepository.remove(projectUpdate);
        
        //then
        Assertions.assertThat(projectUpdateRepository.findById(updateId)).isNull();
    }

    @Test
    @Transactional
    public void updateList_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate projectUpdate1 = makeUpdate(user, project);
        projectUpdateRepository.save(project, projectUpdate1);
        ProjectUpdate projectUpdate2 = makeUpdate(user, project);
        projectUpdateRepository.save(project, projectUpdate2);
        ProjectUpdate projectUpdate3 = makeUpdate(user, project);
        projectUpdateRepository.save(project, projectUpdate3);
        ProjectUpdate projectUpdate4 = makeUpdate(user, project);
        projectUpdateRepository.save(project, projectUpdate4);

        //when
        List<ProjectUpdate> updates = projectUpdateRepository.findUpdateAboutProject(project);

        //then
        Assertions.assertThat(updates.size()).isEqualTo(4);
    }


    private User makeNthUser(int i){
        User user = new User();
        user.setUserId("user" + i + "Id");
        user.setUserName("user" +
                i + "Name");
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

    private ProjectUpdate makeUpdate(User user, Project project){
        ProjectUpdate update = new ProjectUpdate();
        update.setProject(project);
        update.setUpdateDate(LocalDate.now());
        update.setModified(false);
        update.setCreater(user);
        update.setContent("");

        return update;
    }

}