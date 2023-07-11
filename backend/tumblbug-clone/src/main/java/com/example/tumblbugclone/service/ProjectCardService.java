
package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectlistexception.StartIndexException;
import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProjectCardService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectCardService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    public ArrayList<ProjectCardDTO> findOngoingFromIdx(Integer startIdx, String sort) throws TumblbugException {
        return findOngoingFromIdx(startIdx, sort, new Date());
    }

    public ArrayList<ProjectCardDTO> findOngoingFromIdx(Integer startIdx, String sort,  Date today) throws TumblbugException {
        ArrayList<ProjectCardDTO> projectDTOList = new ArrayList<>();
        long findIndex = startIdx;

        if(startIdx == null || startIdx % 20 != 0){
            throw new StartIndexException("startIdx should be multiplier of 20");
        }

        List<Project> ongoingList = projectRepository.findOngoingList(startIdx, sort, today);

        for (Project project : ongoingList) {
            projectDTOList.add(makeCardDTO(project));
        }

        return projectDTOList;
    }


    public ArrayList<ProjectCardDTO> findPreLaunchingFromIdx(int startIdx, String sort) throws TumblbugException {
        Date today = new Date();

        return findPreLaunchingFromIdx(startIdx, sort, today);
    }

    public ArrayList<ProjectCardDTO> findPreLaunchingFromIdx(int startIdx, String sort, Date today) throws TumblbugException {
        ArrayList<ProjectCardDTO> projectDTOList = new ArrayList<>();
        long findIndex = startIdx;

        if(startIdx % 20 != 0){
            throw new StartIndexException("startIdx should be multiplier of 20");
        }

        List<Project> ongoingList = projectRepository.findPrelaunchingList(startIdx, sort, today);

        for (Project project : ongoingList) {
            projectDTOList.add(makeCardDTO(project));
        }

        return projectDTOList;
    }

    private ProjectCardDTO makeCardDTO(Project project){
        ProjectCardDTO dto = new ProjectCardDTO();
        dto.setProjectId(project.getProjectId());
        dto.setTitle(project.getTitle());
        dto.setProjectImg(project.getProjectImg());
        dto.setCategory(project.getCategory());
        dto.setComment(project.getComment());

        dto.setCreaterName(project.getUser().getUserName());
        dto.setCreaterIdx(project.getUser().getUserIdx());

        dto.setTotalMoney(project.getTotalMoney());
        dto.setEndDate(Callendar.convertString(project.getEndDate()));

        //user 쿠키
        //dto.setLike();
        return dto;
    }




}

