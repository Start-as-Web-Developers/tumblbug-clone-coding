package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    @PersistenceContext
    EntityManager em;

    public long save(Project project) {
        em.persist(project);
        return project.getProjectId();
    }

    public Project findProjectById(long id) throws EmptyResultDataAccessException {

        Project findProject = em.find(Project.class, id);
        if (findProject == null)
            throw new EmptyResultDataAccessException(1);

        return findProject;
    }

    public long modify(Project modifiedProject) {
        Project resultProject = em.merge(modifiedProject);
        return resultProject.getProjectId();
    }

    public void delete(Long projectId) {
        Project deleteProject = findProjectById(projectId);
        em.remove(deleteProject);
    }
}





