package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.updateException.CantFindUpdateException;
import com.example.tumblbugclone.Exception.updateException.UpdateCantModifyModifiedToFalse;
import com.example.tumblbugclone.Exception.userexception.UnauthorizedUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.ProjectUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectUpdateService {

    private final ProjectUpdateRepository projectUpdateRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;


    @Autowired
    public ProjectUpdateService(ProjectUpdateRepository projectUpdateRepository, ProjectRepository projectRepository, UserService userService){
        this.projectUpdateRepository = projectUpdateRepository;
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    public long save(long userIndex, long projectId, String content) throws TumblbugException {
        User user;
        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        try{
            user = userService.findUserByIndex(userIndex);
        }catch (EmptyResultDataAccessException e){
            throw new UserCantFindException();
        }

        Project project = null;
        try{
            project = projectRepository.findProjectById(projectId);
        }catch(EmptyResultDataAccessException e){
            //추후 추가 예정
            System.out.println("project is not exist");
        }

        projectUpdateDTO.setContent(content);
        projectUpdateDTO.setUpdateDate(new Date());

        ProjectUpdate projectUpdate = convertDTO2Update(user, project, projectUpdateDTO);
        long updateId = projectUpdateRepository.save(projectUpdate.getProject(), projectUpdate);
        return updateId;
    }

    public ProjectUpdateDTO findProjectUpdateDTO(long projectUpdateId) throws TumblbugException {
        return convertUpdate2DTO(findProjectUpdate(projectUpdateId));
    }

    public ProjectUpdate findProjectUpdate(long projectUpdateId) throws TumblbugException {
        ProjectUpdate byId = projectUpdateRepository.findById(projectUpdateId);
        if(byId == null)
            throw new CantFindUpdateException();
        return byId;
    }
    public ProjectUpdateDTO update(long userIdx, long ContentId, String newContent) throws TumblbugException {
        ProjectUpdate originalUpdate = projectUpdateRepository.findById(ContentId);
        if(originalUpdate.getCreater().getUserIdx() != userIdx)
            throw new UnauthorizedUserException();
        originalUpdate.setUpdateDate(new Date());
        originalUpdate.setContent(newContent);
        originalUpdate.setModified(true);

        projectUpdateRepository.update(originalUpdate);

        return convertUpdate2DTO(originalUpdate);
    }

    public void delete(long userIdx, long ContentId) throws TumblbugException {
        //사용자의 삭제 권한 확인 필요
        ProjectUpdate updateById = projectUpdateRepository.findById(ContentId);
        if(updateById == null)
            throw new CantFindUpdateException();
        if(userIdx != updateById.getCreater().getUserIdx())
            throw new UnauthorizedUserException();
        projectUpdateRepository.remove(updateById);
    }

    public List<ProjectUpdateDTO> findUpdateList(long projectId) throws TumblbugException {
        List<ProjectUpdate> updates = projectUpdateRepository.findUpdateAboutProject(projectRepository.findProjectById(projectId));
        List<ProjectUpdateDTO> dtos = new LinkedList<>();
        if(updates.isEmpty())
            throw new CantFindUpdateException();

        for (ProjectUpdate update : updates) {
            dtos.add(convertUpdate2DTO(update));
        }

        return dtos;
    }

    private ProjectUpdate convertDTO2Update(User user, Project project, ProjectUpdateDTO projectUpdateDTO){
        ProjectUpdate projectUpdate = new ProjectUpdate();
        projectUpdate.setProject(project);
        projectUpdate.setCreater(user);
        projectUpdate.setModified(projectUpdateDTO.isModified());
        projectUpdate.setUpdateDate(projectUpdateDTO.getUpdateDate());
        projectUpdate.setContent(projectUpdateDTO.getContent());

        return projectUpdate;
    }

    private ProjectUpdateDTO convertUpdate2DTO(ProjectUpdate projectUpdate) throws TumblbugException {
        UserSendingDTO creater = null;
        try {
            creater = userService.findSendingUserByIndex(projectUpdate.getCreater().getUserIdx());
        } catch (TumblbugException e) {
            throw e;
        }

        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        projectUpdateDTO.setId(projectUpdate.getId());
        projectUpdateDTO.setUpdateDate(projectUpdate.getUpdateDate());
        projectUpdateDTO.setContent(projectUpdate.getContent());
        projectUpdateDTO.setProjectId(projectUpdate.getProject().getProjectId());
        projectUpdateDTO.setModified(projectUpdate.isModified());
        projectUpdateDTO.setCreater(creater);

        return projectUpdateDTO;
    }

}
