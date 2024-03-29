package com.example.tumblbugclone.service;

import com.example.tumblbugclone.model.Like;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.LikeRepository;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserRepository userRepository, ProjectRepository projectRepository){
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    /*
    * 사용자 정보는 어떻게 받아오나?
    * 즉, 세션은 어떤 정보를 저장하고 있어야 하고, 어느 부분에서 사용자 엔티티를 받아와야 하나?
    *
    * 현재의 내 생각 : 세션 ID - 사용자 Index로 매핑되는 세션 ID 값을 쿠키로 전송 받는다.
    * Controller에서 쿠키에 존재하는 세션 Id를 Service에 넘겨 준다.
    * Service에서 세션 Id를 이용해 사용자 Idx를, 사용자 Idx로 사용자 Entity를 반환 받는다.
    *   해당 부분은 SessionService, SessionRepository로 분기 될 수 있음
    * Service에서 사용자 Entity를 이용한다.*/

    public void like(long userIdx, long projectId){
        Project project = projectRepository.findProjectById(projectId);
        User user = userRepository.findUserByIndex(userIdx);

        likeRepository.like(user, project);
    }

    public boolean isLike(long userIdx, long projectId){
        Project project = projectRepository.findProjectById(projectId);
        User user = userRepository.findUserByIndex(userIdx);
        Like likeByParam = likeRepository.findLikeByParam(user, project);
        if(likeByParam == null)
            return false;
        return likeByParam.isActive();
    }

    public int countProjectLike(long projectId){
        Project project = projectRepository.findProjectById(projectId);
        List<Like> likes = likeRepository.getProjectLikeList(project);

        return likes.size();
    }

    /*
    public boolean checkLike(long userIdx, long projectId) {
        Project project = projectRepository.findProjectById(projectId);
        User user = userRepository.findUserByIndex(userIdx);

        Like like = likeRepository.findLikeByParam(user, project);

        return like.isActive();
    }
    */

 
}
