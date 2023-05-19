package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.Exception.likeException.NoLikeFounded;
import com.example.tumblbugclone.model.Like;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepository {

    @PersistenceContext
    EntityManager em;

    public long save(User user, Project project){
        Like like = new Like();
        like.setUser(user);
        like.setProject(project);

        em.persist(like);

        return like.getLikeId();
    }

    public void delete(long likeId){
        Like disLike = findLikeById(likeId);
        em.remove(disLike);
    }

    public Like findLikeById(long id){
        return em.find(Like.class, id);
    }

    public long findLike(User user, Project project) throws NoLikeFounded {
        TypedQuery<Like> findQuery = em.createQuery("select l from Like l where l.user = :user and  l.project = :project", Like.class)
                .setParameter("user", user)
                .setParameter("project", project);
        Like like = null;
        try{
             like = findQuery.getSingleResult();
        }catch (NoResultException e) {
            throw new NoLikeFounded();
        }

        return like.getLikeId();
    }

    public List<Like> findLikesByUser(User user){
        return em.createQuery("select l from Like l where l.user = :user", Like.class)
                .setParameter("user", user)
                .getResultList();
    }

    public int countProjectLike(Project project){
        List<Like> likeList = em.createQuery("select l from Like l where l.project = :project", Like.class)
                .setParameter("project", project)
                .getResultList();

        return likeList.size();
    }


}
