package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

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

    /**
     * @author : 장혁수
     * projectList 반환을 위해 구현된 메서드
     * TODO
     * - sort 사용해서 정렬 구현
     */
    public List<Project> findOngoingList(int startIndex, String sort){
        return findOngoingList(startIndex, sort, new Date(System.currentTimeMillis()));
    }
    public List<Project> findOngoingList(int startIndex, String sort, java.util.Date date){

        CriteriaBuilder  queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> selectQuery = queryBuilder.createQuery(Project.class);
        Root<Project> root = selectQuery.from(Project.class);

        Predicate start_date = queryBuilder.lessThan(root.get("startDate"), date);
        selectQuery.where(start_date);

        List<Project> projects = em.createQuery(selectQuery)
                .setFirstResult(startIndex)
                .setMaxResults(20)
                .getResultList();

        return projects;
    }

    public List<Project> findPrelaunchingList(int startIndex, String sort){
        java.util.Date date = new Date(System.currentTimeMillis());
        return findPrelaunchingList(startIndex, sort, date);
    }
    public List<Project> findPrelaunchingList(int startIndex, String sort, java.util.Date date){

        CriteriaBuilder  queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> selectQuery = queryBuilder.createQuery(Project.class);
        Root<Project> root = selectQuery.from(Project.class);

        Predicate start_date = queryBuilder.greaterThan(root.get("startDate"), date);
        selectQuery.where(start_date);

        List<Project> projects = em.createQuery(selectQuery)
                .setFirstResult(startIndex)
                .setMaxResults(20)
                .getResultList();

        return projects;

    }
}





