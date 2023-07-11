package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectException.ProjectSortException;
import com.example.tumblbugclone.managedconst.ProjectListSort;
import com.example.tumblbugclone.model.Project;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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
    public List<Project> findOngoingList(int startIndex, String sort) throws TumblbugException {
        return findOngoingList(startIndex, sort, LocalDate.now());
    }
    public List<Project> findOngoingList(int startIndex, String sort, LocalDate date) throws TumblbugException {

        CriteriaBuilder  queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> selectQuery = queryBuilder.createQuery(Project.class);
        Root<Project> root = selectQuery.from(Project.class);

        Predicate start_date = queryBuilder.lessThan(root.get("startDate"), date);
        selectQuery.where(start_date);

        Order order;
        if(sort == null){
            order = null;
        }else if(sort.equals(ProjectListSort.POPULAR.name().toLowerCase(Locale.ROOT))){
            /*Join<Project, Like> likeJoin = root.join("likePeople", JoinType.LEFT);

            selectQuery = selectQuery.multiselect(root, queryBuilder.count(likeJoin).alias("numLikes"))
                    .groupBy(root)
                    .orderBy(queryBuilder.desc(queryBuilder.count(likeJoin)));

            List<Project> projects = em.createQuery(selectQuery)
                    .setFirstResult(startIndex)
                    .setMaxResults(20)
                    .getResultList();

            return projects;*/
            order = null;
            //order = queryBuilder.desc(queryBuilder.size(root.get("likes")));
        }else if(sort.equals(ProjectListSort.NEW.name().toLowerCase(Locale.ROOT))){
            order = queryBuilder.asc(root.get("startDate"));
        }else if(sort.equals(ProjectListSort.IMMINENT)){
            order = queryBuilder.asc(root.get("endDate"));
        }else{
            throw new ProjectSortException();
        }
        if(order != null)
            selectQuery.orderBy(order);


        List<Project> projects = em.createQuery(selectQuery)
                .setFirstResult(startIndex)
                .setMaxResults(20)
                .getResultList();

        return projects;
    }

    public List<Project> findPrelaunchingList(int startIndex, String sort) throws TumblbugException {

        return findPrelaunchingList(startIndex, sort, LocalDate.now());
    }
    public List<Project> findPrelaunchingList(int startIndex, String sort, LocalDate date) throws TumblbugException {

        CriteriaBuilder  queryBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Project> selectQuery = queryBuilder.createQuery(Project.class);
        Root<Project> root = selectQuery.from(Project.class);

        Order order;
        if(sort == null){
            order = null;
        }else if(sort.equals(ProjectListSort.POPULAR.name().toLowerCase(Locale.ROOT))){
            order = queryBuilder.desc(queryBuilder.size(root.get("likes")));
        }else if(sort.equals(ProjectListSort.NEW.name().toLowerCase(Locale.ROOT))){
            order = queryBuilder.asc(root.get("startDate"));
        }else{
            throw new ProjectSortException();
        }

        if(order != null)
            selectQuery.orderBy(order);

        Predicate start_date = queryBuilder.greaterThan(root.get("startDate"), date);
        selectQuery.where(start_date);

        List<Project> projects = em.createQuery(selectQuery)
                .setFirstResult(startIndex)
                .setMaxResults(20)
                .getResultList();

        return projects;

    }
}





