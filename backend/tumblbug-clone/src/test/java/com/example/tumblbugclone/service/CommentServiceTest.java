package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.userexception.UnauthorizedUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.CommentDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.ProjectUpdateRepository;
import com.example.tumblbugclone.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectUpdateRepository updateRepository;

    String basicString = "comment";
    String modifyString = "modifyString";
    long authUserIdx;
    long unauthUserIdx;
    long updateId;
    long commentIdx;

    @Test
    @Transactional
    public void 댓글_작성() throws Exception{
        //given
        init();


        //when
        long commentId = commentService.saveComment(authUserIdx, updateId, basicString);

        //then
        List<CommentDTO> comments = commentService.getComments(updateId);
        Assertions.assertThat(comments.size()).isEqualTo(1);
        CommentDTO commentDTO = comments.get(0);
        Assertions.assertThat(commentDTO.getComment()).isEqualTo(basicString);
        Assertions.assertThat(commentDTO.getUser().getUserIdx()).isEqualTo(authUserIdx);
        Assertions.assertThat(commentDTO.getId()).isEqualTo(commentId);
    }

    @Test(expected = UserCantFindException.class)
    @Transactional
    public void 없는_회원_댓글작성() throws Exception{
        //given
        init();
        commentService.saveComment(0, updateId, basicString);
        //when

        //then
    }
    
    @Test
    @Transactional
    public void 댓글_여러개_작성() throws Exception{
        //given
        init();

        //when
        commentService.saveComment(authUserIdx, updateId, basicString);
        commentService.saveComment(authUserIdx, updateId, basicString);
        commentService.saveComment(authUserIdx, updateId, basicString);
        commentService.saveComment(authUserIdx, updateId, basicString);
        commentService.saveComment(authUserIdx, updateId, basicString);

        //then
        List<CommentDTO> comments = commentService.getComments(updateId);
        Assertions.assertThat(comments.size()).isEqualTo(5);

        for (CommentDTO comment : comments) {
            Assertions.assertThat(comment.getComment()).isEqualTo(basicString);
            Assertions.assertThat(comment.getUser().getUserIdx()).isEqualTo(authUserIdx);
        }
    }

    @Test
    @Transactional
    public void 댓글_수정() throws Exception{
        //given
        init();
        long commentId = commentService.saveComment(authUserIdx, updateId, basicString);

        //when
        commentService.modifyComment(authUserIdx, commentId, modifyString);
        
        //then
        List<CommentDTO> comments = commentService.getComments(updateId);
        Assertions.assertThat(comments.size()).isEqualTo(1);
        CommentDTO commentDTO = comments.get(0);
        Assertions.assertThat(commentDTO.getComment()).isEqualTo(modifyString);
        Assertions.assertThat(commentDTO.getUser().getUserIdx()).isEqualTo(authUserIdx);
        Assertions.assertThat(commentDTO.getId()).isEqualTo(commentId);
    }
    
    @Test(expected = UnauthorizedUserException.class)
    @Transactional
    public void 권한없는_댓글_수정() throws Exception{
        //given
        init();
        long commentId = commentService.saveComment(authUserIdx, updateId, basicString);

        //when
        commentService.modifyComment(unauthUserIdx, commentId, modifyString);
        //then
    }
    
    @Test
    @Transactional
    public void 댓글_삭제() throws Exception{
        //given
        init();
        long commentId = commentService.saveComment(authUserIdx, updateId, basicString);

        //when
        commentService.deleteComment(authUserIdx, commentId);

        //then
        List<CommentDTO> comments = commentService.getComments(updateId);
        Assertions.assertThat(comments.isEmpty()).isTrue();
    }

    @Test(expected = UnauthorizedUserException.class)
    @Transactional
    public void 권한_없는_댓글삭제() throws Exception{
        //given
        init();
        long commentId = commentService.saveComment(authUserIdx, updateId, basicString);

        //when
        commentService.deleteComment(unauthUserIdx, commentId);

        //then
    }

    private void init(){
        User user1 = makeNthUser(1);
        this.authUserIdx = userRepository.save(user1);
        User user2 = makeNthUser(2);
        this.authUserIdx = userRepository.save(user2);
        Project project = makeProject(user1);
        projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user1, project);
        this.updateId = updateRepository.save(project, update);
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

    private ProjectUpdate makeUpdate(User user, Project project){
        ProjectUpdate update = new ProjectUpdate();
        update.setProject(project);
        update.setUpdateDate(new Date());
        update.setModified(false);
        update.setCreater(user);
        update.setContent("");

        return update;
    }
}