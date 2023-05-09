/*
package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.projectException.ProjectCantFindException;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProjectRepository {

    private static Long id = 0L;
    private static final ProjectRepository projectRepository = new ProjectRepository();
    private static HashMap<Long, Project> projectDB = new HashMap<>();
    private static UserRepository userDB = UserRepository.getUserRepository();


    private ProjectRepository() {};

    public static ProjectRepository getProjectRepository() {return  projectRepository;};

    public void clear() {
        id = 0L;
        projectDB.clear();
    }

    public long save(Project project) throws Exception {

        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        project.setUserIdx(1L);

        id++;
        project.setProjectId(id);
        projectDB.put(id, project);

        return id;
    }

    public Project findProjectById(Long requiredId) throws ProjectCantFindException {
        if(requiredId > id)
            throw new ProjectCantFindException();

        return projectDB.get(requiredId);

    }

    public List<Project> findAllProject() {
        return new ArrayList<>(projectDB.values());
    }

    public long updateProject(Project updateProject) throws Exception {
        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        updateProject.setUserIdx(1L);

        Long projectIdx = updateProject.getProjectId();
        verifyProjectIdx(projectIdx);

        projectDB.put(projectIdx, updateProject);
        return projectIdx;
    }

    public void verifyProjectIdx(Long projectIdx) throws ProjectCantFindException {
        if(projectIdx == null)
            throw new ProjectCantFindException();
        if(projectIdx > id)
            throw new ProjectCantFindException();
    }

    public void deleteProject(Long projectIdx) throws ProjectCantFindException {
        verifyProjectIdx(projectIdx);
        projectDB.remove(projectIdx);
    }
}
*/
