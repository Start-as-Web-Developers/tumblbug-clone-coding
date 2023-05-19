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

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class LikeRepositoryTest {

    @Autowired LikeRepository likeRepository;
    @Autowired UserRepository userRepository;
    @Autowired ProjectRepository projectRepository;

    User basicUser1;
    User basicUser2;
    Project basicProject1;
    Project basicProject2;
    Project basicProject3;

    @Test
    @Transactional
    public void 좋아요() throws Exception{
        //given
        make_basic_data();

        //when
        long likeId = likeRepository.save(basicUser1, basicProject1);

        //then
        Assertions.assertThat(likeRepository.findLikeById(likeId).getUser()).isEqualTo(basicUser1);
        Assertions.assertThat(likeRepository.findLikeById(likeId).getProject()).isEqualTo(basicProject1);
    }

    @Test
    @Transactional
    public void 좋아요_취소() throws Exception{
        //given
        make_basic_data();

        //when
        long likeId = likeRepository.save(basicUser1, basicProject1);
        likeRepository.delete(likeId);

        //then
        Assertions.assertThat(likeRepository.findLikeById(likeId)).isNull();
    }
    
    @Test
    @Transactional
    public void 좋아요_조회() throws Exception{
        //given
        make_basic_data();
        
        //when
        likeRepository.save(basicUser1, basicProject1);
        likeRepository.save(basicUser1, basicProject2);
        likeRepository.save(basicUser1, basicProject3);
        
        //then
        Assertions.assertThat(likeRepository.findLikesByUser(basicUser1).size()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void 유저_좋아요_없을때_조회() throws Exception{
        //given
        make_basic_data();

        //when

        //then
        Assertions.assertThat(likeRepository.findLikesByUser(basicUser1).size()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void 좋아요_count() throws Exception{
        //given
        make_basic_data();

        //when
        likeRepository.save(basicUser1, basicProject1);
        likeRepository.save(basicUser2, basicProject1);

        //then
        Assertions.assertThat(likeRepository.countProjectLike(basicProject1)).isEqualTo(2);
    }

    @Test
    @Transactional
    public void 프로젝트_좋아요_없을때_좋아요_count() throws Exception{
        //given
        make_basic_data();

        //when

        //then
        Assertions.assertThat(likeRepository.countProjectLike(basicProject1)).isEqualTo(0);
    }

    @Transactional
    public void make_basic_data(){

        basicUser1 = new User();
        basicUser1.setUserId("ID1");
        basicUser1.setUserName("Name");
        basicUser1.setUserEmail("Email1");
        basicUser1.setUserPassword("Password");

        userRepository.save(basicUser1);

        basicUser2 = new User();
        basicUser2.setUserId("ID2");
        basicUser2.setUserName("Name");
        basicUser2.setUserEmail("Email2");
        basicUser2.setUserPassword("Password");

        userRepository.save(basicUser2);

        basicProject1 = new Project();
        basicProject1.setUser(basicUser1);
        basicProject1.setTitle("title1");
        basicProject1.setProjectImg("img");
        basicProject1.setCategory("category");
        basicProject1.setComment("comment");
        basicProject1.setGoalMoney(1000L);
        basicProject1.setStartDate(new Date());
        basicProject1.setEndDate(new Date());
        basicProject1.setPlanIntro("planIntro");
        basicProject1.setPlanBudget("planBudget");
        basicProject1.setPlanSchedule("planSchedule");
        basicProject1.setPlanTeam("planTeam");
        basicProject1.setPlanExplain("planExplain");
        basicProject1.setPlanGuide("planGuide");

        basicProject2 = new Project();
        basicProject2.setUser(basicUser1);
        basicProject2.setTitle("title2");
        basicProject2.setProjectImg("img");
        basicProject2.setCategory("category");
        basicProject2.setComment("comment");
        basicProject2.setGoalMoney(1000L);
        basicProject2.setStartDate(new Date());
        basicProject2.setEndDate(new Date());
        basicProject2.setPlanIntro("planIntro");
        basicProject2.setPlanBudget("planBudget");
        basicProject2.setPlanSchedule("planSchedule");
        basicProject2.setPlanTeam("planTeam");
        basicProject2.setPlanExplain("planExplain");
        basicProject2.setPlanGuide("planGuide");

        basicProject3 = new Project();
        basicProject3.setUser(basicUser1);
        basicProject3.setTitle("title3");
        basicProject3.setProjectImg("img");
        basicProject3.setCategory("category");
        basicProject3.setComment("comment");
        basicProject3.setGoalMoney(1000L);
        basicProject3.setStartDate(new Date());
        basicProject3.setEndDate(new Date());
        basicProject3.setPlanIntro("planIntro");
        basicProject3.setPlanBudget("planBudget");
        basicProject3.setPlanSchedule("planSchedule");
        basicProject3.setPlanTeam("planTeam");
        basicProject3.setPlanExplain("planExplain");
        basicProject3.setPlanGuide("planGuide");

        projectRepository.save(basicProject1);
        projectRepository.save(basicProject2);
        projectRepository.save(basicProject3);
    }
}