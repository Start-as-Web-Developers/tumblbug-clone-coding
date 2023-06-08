package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.UpdateComment;
import com.example.tumblbugclone.model.User;
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
public class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired UserRepository userRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired ProjectUpdateRepository updateRepository;

    @Test
    @Transactional
    public void 댓글_작성() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user, project);
        updateRepository.save(project, update);

        //when
        UpdateComment comment = makeComment(user, update, "comment");
        commentRepository.save(comment);

        //then
        List<UpdateComment> comments = commentRepository.getAllComments(update);
        Assertions.assertThat(comments.size()).isEqualTo(1);
        for (UpdateComment updateComment : comments) {
            updateComment.getComment().equals("comment");
        }
    }

    @Test
    @Transactional
    public void 댓글_여러개_작성() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user, project);
        updateRepository.save(project, update);

        //when
        UpdateComment comment1 = makeComment(user, update, "comment");
        commentRepository.save(comment1);
        UpdateComment comment2 = makeComment(user, update, "comment");
        commentRepository.save(comment2);
        UpdateComment comment3 = makeComment(user, update, "comment");
        commentRepository.save(comment3);
        UpdateComment comment4 = makeComment(user, update, "comment");
        commentRepository.save(comment4);
        UpdateComment comment5 = makeComment(user, update, "comment");
        commentRepository.save(comment5);

        //then
        List<UpdateComment> comments = commentRepository.getAllComments(update);
        Assertions.assertThat(comments.size()).isEqualTo(5);
        for (UpdateComment updateComment : comments) {
            updateComment.getComment().equals("comment");
            updateComment.getUser().equals(user);
        }
    }

    @Test
    @Transactional
    public void 댓글_수정() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user, project);
        updateRepository.save(project, update);
        UpdateComment comment = makeComment(user, update, "comment");
        long savedId = commentRepository.save(comment);

        //when
        String modifyString = "modifyComment";
        comment.setComment(modifyString);
        commentRepository.modify(comment);

        //then
        List<UpdateComment> comments = commentRepository.getAllComments(update);
        Assertions.assertThat(comments.size()).isEqualTo(1);
        Assertions.assertThat(comments.get(0).getId()).isEqualTo(savedId);
        Assertions.assertThat(comments.get(0).getComment()).isEqualTo(modifyString);
    }

    @Test
    @Transactional
    public void 댓글_삭제() throws Exception{
        //given
        User user = makeNthUser(1);
        userRepository.save(user);
        Project project = makeProject(user);
        projectRepository.save(project);
        ProjectUpdate update = makeUpdate(user, project);
        updateRepository.save(project, update);
        UpdateComment comment = makeComment(user, update, "comment");
        commentRepository.save(comment);

        //when
        commentRepository.delete(comment);

        //then
        List<UpdateComment> comments = commentRepository.getAllComments(update);
        Assertions.assertThat(comments.isEmpty()).isTrue();
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

    private UpdateComment makeComment(User user, ProjectUpdate update, String string) {
        UpdateComment comment = new UpdateComment();
        comment.setUpdate(update);
        comment.setComment(string);
        comment.setUser(user);

        return comment;
    }

}