package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Like;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class LikeRepository {

    @PersistenceContext
    EntityManager em;

    public long like(User user, Project project){
        Like like = findLikeByParam(user, project);
        if(like == null) {
            like = new Like();
            like.setUser(user);
            like.setProject(project);

        }else{
            if(like.isActive())
                like.setActive(false);
            else
                like.setActive(true);
        }

        em.persist(like);
        return like.getId();
    }

    public Like findLikeById(long id){
        Like like = em.find(Like.class, id);

        return like;
    }

    public Like findLikeByParam(User user, Project project){

        TypedQuery<Like> selectLikeQuery = em.createQuery("select l from Like l where l.user = :user and l.project = :project", Like.class)
                .setParameter("user", user)
                .setParameter("project", project);

        try{
            Like like = selectLikeQuery.getSingleResult();
            return like;
        }catch(NoResultException e){
            return null;
        }
    }

    public List<Like> getProjectLikeList(Project project){
        TypedQuery<Like> selectLikeQuery = em.createQuery("select l from Like l where l.project = :project", Like.class)
                .setParameter("project", project);

        return selectLikeQuery.getResultList();
    }
}
