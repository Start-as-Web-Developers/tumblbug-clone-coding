package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.updateException.CantFindUpdateException;
import com.example.tumblbugclone.Exception.userexception.UnauthorizedUserException;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
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
public class ProjectUpdateServiceTest {
    @Autowired
    ProjectUpdateService projectUpdateService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Test
    @Transactional
    public void 업데이트_저장() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO dto = makeUpdate();

        //when
        long updateId = projectUpdateService.save(userIndex, projectId, dto.getContent());
        ProjectUpdateDTO projectUpdateDTO = projectUpdateService.findProjectUpdateDTO(updateId);

        //then
        Assertions.assertThat(projectUpdateDTO.getContent()).isEqualTo(dto.getContent());
        Assertions.assertThat(projectUpdateDTO.getCreater().getUserName()).isEqualTo(user.getUserName());
        Assertions.assertThat(projectUpdateDTO.getProjectId()).isEqualTo(projectId);
    }

    @Test
    @Transactional
    public void 업데이트_조회() throws Exception{
        //given
        //given
        User user = makeNthUser(1);
        long userIndex = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO dto = makeUpdate();
        long updateId = projectUpdateService.save(userIndex, projectId, dto.getContent());

        //when
        ProjectUpdateDTO projectUpdateDTO = projectUpdateService.findProjectUpdateDTO(updateId);

        //then
        Assertions.assertThat(projectUpdateDTO
                        .getCreater().getUserName())
                .isEqualTo(user.getUserName());
        Assertions.assertThat(projectUpdateDTO
                        .getProjectId())
                .isEqualTo(projectId);
        Assertions.assertThat(projectUpdateDTO
                        .getContent())
                .isEqualTo(dto.getContent());
        Assertions.assertThat(projectUpdateDTO
                        .getId())
                .isEqualTo(updateId);
    }

    @Test
    @Transactional
    public void 업데이트_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO update = makeUpdate();
        long updateId = projectUpdateService.save(userIdx, projectId, update.getContent());

        //when
        String modifyString = "Modified Content";
        projectUpdateService.update(userIdx, updateId, modifyString);
        ProjectUpdateDTO modifiedDTO = projectUpdateService.findProjectUpdateDTO(updateId);

        //then
        Assertions.assertThat(modifiedDTO.isModified()).isTrue();
        Assertions.assertThat(modifiedDTO.getContent()).isEqualTo(modifyString);
    }

    @Test(expected = UnauthorizedUserException.class)
    @Transactional
    public void 잘못된_권한으로_업데이트_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO update = makeUpdate();
        long updateId = projectUpdateService.save(userIdx, projectId, update.getContent());

        //when
        String modifyString = "Modified Content";
        projectUpdateService.update(userIdx+1, updateId, modifyString);

        //then
    }

    @Test(expected = CantFindUpdateException.class)
    @Transactional
    public void 업데이트_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO update = makeUpdate();
        long updateId = projectUpdateService.save(userIdx, projectId, update.getContent());

        //when
        projectUpdateService.delete(userIdx, updateId);

        //then
        projectUpdateService.findProjectUpdateDTO(updateId);
    }

    @Test(expected = UnauthorizedUserException.class)
    @Transactional
    public void 잘못된_권한_업데이트_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO update = makeUpdate();
        long updateId = projectUpdateService.save(userIdx, projectId, update.getContent());

        //when
        projectUpdateService.delete(userIdx+1, updateId);
    }
    @Test
    @Transactional
    public void 업데이트_목록_조회() throws Exception{
        //given
        User user = makeNthUser(1);
        long userIdx = userRepository.save(user);
        Project project = makeProject(user);
        long projectId = projectRepository.save(project);
        ProjectUpdateDTO update1 = makeUpdate();
        long updateId1 = projectUpdateService.save(userIdx, projectId, update1.getContent());
        update1 = projectUpdateService.findProjectUpdateDTO(updateId1);
        ProjectUpdateDTO update2 = makeUpdate();
        long updateId2 = projectUpdateService.save(userIdx, projectId, update2.getContent());
        update2 = projectUpdateService.findProjectUpdateDTO(updateId2);
        ProjectUpdateDTO update3 = makeUpdate();
        long updateId3 = projectUpdateService.save(userIdx, projectId, update3.getContent());
        update3 = projectUpdateService.findProjectUpdateDTO(updateId3);
        ProjectUpdateDTO update4 = makeUpdate();
        long updateId4 = projectUpdateService.save(userIdx, projectId, update4.getContent());
        update4 = projectUpdateService.findProjectUpdateDTO(updateId4);

        //when
        List<ProjectUpdateDTO> updateList = projectUpdateService.findUpdateList(projectId);

        //then
        Assertions.assertThat(updateList.size()).isEqualTo(4);
        Assertions.assertThat(updateList.contains(update1)).isTrue();
        Assertions.assertThat(updateList.contains(update2)).isTrue();
        Assertions.assertThat(updateList.contains(update3)).isTrue();
        Assertions.assertThat(updateList.contains(update4)).isTrue();
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

    private ProjectUpdateDTO makeUpdate(){
        ProjectUpdateDTO update = new ProjectUpdateDTO();
        update.setUpdateDate(LocalDate.now());
        update.setModified(false);
        update.setContent("");

        return update;
    }
}