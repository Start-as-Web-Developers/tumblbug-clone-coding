
package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.projectException.ProjectCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.managedconst.ProjectConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
public class ProjectCardService {
    //private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectCardService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ArrayList<ProjectCardDTO> findOngoingFromIdx(long startIdx){
        return findOngoingFromIdx(startIdx, new Date());
    }

    public ArrayList<ProjectCardDTO> findOngoingFromIdx(long startIdx, Date today){
        ArrayList<ProjectCardDTO> projectList = new ArrayList<>();
        long findIndex = startIdx;

        while(projectList.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE){
            Project findProject;
            try{
                findProject = projectRepository.findProjectById(findIndex);
            }catch (EmptyResultDataAccessException e){
                break;
            }

            if(!findProject.getStartDate().after(today)){
                if(findProject.getEndDate().before(today)){
                    findIndex++;
                    continue;
                }
                System.out.println();
                ProjectCardDTO projectCardDTO = makeCardDTO(findProject);
                projectList.add(projectCardDTO);
            }
            findIndex++;
        }

        return projectList;
    }


    public ArrayList<ProjectCardDTO> findPreLaunchingFromIdx(long startIdx){
        Date today = new Date();

        return findPreLaunchingFromIdx(1l, today);
    }

    public ArrayList<ProjectCardDTO> findPreLaunchingFromIdx(long startIdx, Date today){
        ArrayList<ProjectCardDTO> projectList = new ArrayList<>();
        long findIndex = startIdx;

        while(projectList.size() < ProjectConst.PROJECT_CARDS_MAX_SIZE){
            System.out.println(findIndex);
            Project findProject;
            try{
                findProject= projectRepository.findProjectById(findIndex);
            } catch (EmptyResultDataAccessException e){
                break;
            }

            if(findProject.getStartDate().after(today)){
                ProjectCardDTO projectCardDTO = makeCardDTO(findProject);
                projectList.add(projectCardDTO);
            }
            findIndex++;
        }

        return projectList;
    }
    public ProjectCardDTO makeCardDTO(Project project){
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

