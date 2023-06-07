package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectUpdate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProjectUpdateRepository {

    @PersistenceContext
    EntityManager em;

    public long save(Project project, ProjectUpdate update){
        update.setProject(project);
        em.persist(update);

        project.getUpdate().add(update);
        em.persist(project);
        //em.persist(project);

        return update.getId();
    }

    //Read
    public ProjectUpdate findById(long id) {
        ProjectUpdate projectUpdate = em.find(ProjectUpdate.class, id);

        return projectUpdate;
    }

    //updateList
    public List<ProjectUpdate> findUpdateAboutProject(Project project){
        return em.createQuery("select u from ProjectUpdate u where u.project = :project")
                .setParameter("project", project)
                .getResultList();
    }

    //update
    public void update(ProjectUpdate update){
        em.merge(update);
    }

    //delete
    public long remove(ProjectUpdate projectUpdate){
        long updateId = projectUpdate.getId();

        em.remove(projectUpdate);
        return updateId;
    }
}
