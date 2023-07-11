package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Community;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class CommunityRepositoryTest {

    @Autowired CommunityRepository communityRepository;
    @Autowired UserRepository userRepository;
    @Autowired ProjectRepository projectRepository;

    @Test
    @Transactional
    public void save() throws Exception {
        //given
        User user = saveUser();

        Project project = saveProject(user);

        Community community = saveCommunity(project, user, "comment");

        //when
        communityRepository.save(community);
    }



    @Test
    @Transactional
    public void findCommunityById() throws Exception {
        //given
        User user = saveUser();

        Project project = saveProject(user);

        Community community = saveCommunity(project, user, "comment");

        //when
        long communityId = communityRepository.save(community);
        Community savedCommunity = communityRepository.findCommunityById(communityId);

        //then
        Assertions.assertThat(communityId).isEqualTo(savedCommunity.getCommunityId());
    }



    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void findCommunityById_EX() throws Exception {
        //given

        //when
        Community findCommunity = communityRepository.findCommunityById(1L);
    }

    /*@Test
    @Transactional
    public void findCommunityByProjectId() {
        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");
        userRepository.save(user);

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
        projectRepository.save(project);

        Community community1 = new Community();
        community1.setProject(project);
        community1.setUser(user);
        community1.setComment("comment1");
        community1.setModiDate(new Date(2023, 05, 15));
        community1.setWriteDate(new Date(2023, 05, 15));

        Community community2 = new Community();
        community2.setProject(project);
        community2.setUser(user);
        community2.setComment("comment2");
        community2.setModiDate(new Date(2023, 05, 15));
        community2.setWriteDate(new Date(2023, 05, 15));

        //when
        communityRepository.save(community1);
        communityRepository.save(community2);

        List<Community> savedCommunityList = new ArrayList<>();
        savedCommunityList.add(community1);
        savedCommunityList.add(community2);

        List<Community> communityList = communityRepository.findCommunityByProjectId(1L);

        //then
        Assertions.assertThat(savedCommunityList).isEqualTo(communityList);

    }*/

    @Test
    @Transactional
    public void findCommunityByProjectId_EX() throws Exception {
        //given
        User user = saveUser();

        Project project = saveProject(user);

        Community community1 = saveCommunity(project, user, "comment1");

        Community community2 = saveCommunity(project, user, "comment2");

        //when
        communityRepository.save(community1);

        List<Community> savedCommunityList = new ArrayList<>();
        savedCommunityList.add(community1);
        savedCommunityList.add(community2);

        List<Community> communityList = communityRepository.findCommunityByProjectId(1L);

        //then
        Assertions.assertThat(savedCommunityList).isNotEqualTo(communityList);
    }

    @Test
    @Transactional
    public void modify() throws Exception {
        //given
        User user = saveUser();

        Project project = saveProject(user);

        Community community = saveCommunity(project, user, "comment");

        //when
        communityRepository.save(community);
        community.setComment("newComment");

        long communityId = communityRepository.modify(community);
        Community savedCommunity = communityRepository.findCommunityById(communityId);

        //then
        Assertions.assertThat(community).isEqualTo(savedCommunity);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void delete() throws Exception {
        //given
        User user = saveUser();

        Project project = saveProject(user);

        Community community = saveCommunity(project, user, "comment");

        long communityId = communityRepository.save(community);

        //when
        communityRepository.delete(communityId);
        communityRepository.findCommunityById(communityId);
    }

    private User saveUser() {
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");
        userRepository.save(user);
        return user;
    }

    private Community saveCommunity(Project project, User user, String comment) {
        Community community = new Community();
        community.setProject(project);
        community.setUser(user);
        community.setComment(comment);
        community.setModiDate(LocalDate.of(2023, 05, 15));
        community.setWriteDate(LocalDate.of(2023, 05, 15));
        return community;
    }

    private Project saveProject(User user) {
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
        projectRepository.save(project);
        return project;
    }
}
