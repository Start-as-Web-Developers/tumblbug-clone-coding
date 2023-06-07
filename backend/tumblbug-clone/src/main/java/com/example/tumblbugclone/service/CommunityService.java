package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.communityException.CommunityCantFindException;
import com.example.tumblbugclone.Exception.communityException.CommunityCantModify;
import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.CommunityRepository;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public CommunityService(CommunityRepository communityRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    public long writeCommunity(Community community) throws ParseException {

        community.setWriteDate(Callendar.getToday());
        return communityRepository.save(community);
    }

    public List<Community> CommunityList(long projectId) {
        List<Community> communityList =  communityRepository.findCommunityByProjectId(projectId);
        for(Community community : communityList) {
            community.setProject(null);
            User user = community.getUser();
            user.setUserPassword(null);
            community.setUser(user);
        }

        return communityList;
    }

    public long modify(Community community, Long userIndex) throws Exception {
        Community findCommunity = communityRepository.findCommunityById(community.getCommunityId());
        if(findCommunity.getCommunityId() != community.getCommunityId()) {
            throw new CommunityCantFindException();
        }

        if(!Objects.equals(findCommunity.getUser().getUserIdx(), userIndex)) {
            throw new CommunityCantModify();
        }

        User user = new User();
        user.setUserIdx(userIndex);
        community.setUser(user);

        community.setWriteDate(findCommunity.getWriteDate());
        community.setModiDate(Callendar.getToday());

       return communityRepository.modify(community);
    }

    public void delete(long communityId, Long userIndex) throws Exception {
        Community findCommunity = communityRepository.findCommunityById(communityId);

        if(!Objects.equals(findCommunity.getUser().getUserIdx(), userIndex)) {
            throw new CommunityCantModify();
        }

       communityRepository.delete(communityId);
    }



}
