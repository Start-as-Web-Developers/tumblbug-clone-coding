package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.updateException.CantFindUpdateException;
import com.example.tumblbugclone.Exception.updateException.UpdateCantModifyIdException;
import com.example.tumblbugclone.Exception.updateException.UpdateCantModifyModifiedToFalse;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.ProjectUpdateRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectUpdateService {

    private final ProjectUpdateRepository projectUpdateRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    @Autowired
    public ProjectUpdateService(ProjectUpdateRepository projectUpdateRepository, ProjectRepository projectRepository, UserRepository userRepository){
        this.projectUpdateRepository = projectUpdateRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public long save(long userIndex, long projectId, ProjectUpdateDTO projectUpdateDTO){
        ProjectUpdate projectUpdate = convertDTO2Update(userIndex, projectId, projectUpdateDTO);
        long updateId = projectUpdateRepository.save(projectUpdate.getProject(), projectUpdate);
        return updateId;
    }

    public ProjectUpdateDTO findProjectUpdateDTO(long projectUpdateId) throws CantFindUpdateException {
        ProjectUpdate byId = projectUpdateRepository.findById(projectUpdateId);
        if(byId == null)
            throw new CantFindUpdateException();
        return convertUpdate2DTO(byId);
    }

    public void update(ProjectUpdateDTO projectUpdateDTO) throws UpdateCantModifyModifiedToFalse {
        //사용자의 수정 권한 확인 필요
        ProjectUpdate originalUpdate = projectUpdateRepository.findById(projectUpdateDTO.getId());
        if(originalUpdate.isModified() && !projectUpdateDTO.isModified())
            throw new UpdateCantModifyModifiedToFalse();
        originalUpdate.setUpdateDate(projectUpdateDTO.getUpdateDate());
        originalUpdate.setContent(projectUpdateDTO.getContent());
        originalUpdate.setModified(true);

        projectUpdateRepository.update(originalUpdate);
    }

    public void delete(long projectId){
        //사용자의 삭제 권한 확인 필요
        ProjectUpdate updateById = projectUpdateRepository.findById(projectId);
        projectUpdateRepository.remove(updateById);
    }

    public List<ProjectUpdateDTO> findUpdateList(long projectId) throws CantFindUpdateException {
        List<ProjectUpdate> updates = projectUpdateRepository.findUpdateAboutProject(projectRepository.findProjectById(projectId));
        List<ProjectUpdateDTO> dtos = new LinkedList<>();
        if(updates.isEmpty())
            throw new CantFindUpdateException();

        for (ProjectUpdate update : updates) {
            dtos.add(convertUpdate2DTO(update));
        }

        return dtos;
    }
    private ProjectUpdate convertDTO2Update(long userIndex, long projectId, ProjectUpdateDTO projectUpdateDTO){
        User user = userRepository.findUserByIndex(userIndex);
        Project project = projectRepository.findProjectById(projectId);
        ProjectUpdate projectUpdate = new ProjectUpdate();
        projectUpdate.setProject(project);
        projectUpdate.setCreater(user);
        projectUpdate.setModified(projectUpdateDTO.isModified());
        projectUpdate.setUpdateDate(projectUpdateDTO.getUpdateDate());
        projectUpdate.setContent(projectUpdateDTO.getContent());


        return projectUpdate;
    }

    private ProjectUpdateDTO convertUpdate2DTO(ProjectUpdate projectUpdate) {
        ProjectUpdateDTO projectUpdateDTO = new ProjectUpdateDTO();
        projectUpdateDTO.setId(projectUpdate.getId());
        projectUpdateDTO.setUpdateDate(projectUpdate.getUpdateDate());
        projectUpdateDTO.setCreaterName(projectUpdate.getCreater().getUserName());
        projectUpdateDTO.setContent(projectUpdate.getContent());
        projectUpdateDTO.setProjectId(projectUpdate.getProject().getProjectId());
        projectUpdateDTO.setModified(projectUpdate.isModified());

        return projectUpdateDTO;
    }
}
