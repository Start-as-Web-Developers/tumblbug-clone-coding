package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.communityException.CommunityCantFindException;
import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.CommunityRepository;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public CommunityService(CommunityRepository communityRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public long writeCommunity(Community community) {
        // 임시 저장
        User user = saveUser();
        community.setUser(user);
        return communityRepository.save(community);
    }

    public List<Community> CommunityList(long projectId) {
        return communityRepository.findCommunityByProjectId(projectId);
    }

    public long modify(Community community) throws Exception {
        Community findCommunity = communityRepository.findCommunityById(community.getCommunityId());
        if(findCommunity.getCommunityId() != community.getCommunityId()) {
            throw new CommunityCantFindException();
        }

       return communityRepository.modify(community);
    }

    public void delete(long communityId) throws Exception {
       communityRepository.delete(communityId);
    }



    private User saveUser() {
        User user = new User();
        user.setUserId("id");
        user.setUserEmail("email");
        user.setUserName("name");
        user.setUserPassword("password");
        user.setGreeting("greeting");
        user.setUserImg("img");
        userRepository.save(user);
        return user;
    }
}
