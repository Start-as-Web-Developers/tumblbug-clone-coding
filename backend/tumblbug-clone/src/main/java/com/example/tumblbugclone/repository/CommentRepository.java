package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.UpdateComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CommentRepository {

    @PersistenceContext
    EntityManager em;

    public long save(UpdateComment comment) {
        em.persist(comment);

        return comment.getId();
    }

    public List<UpdateComment> getComments(ProjectUpdate update){
        List comments = em.createQuery("select c from UpdateComment c where c.update = :update")
                .setParameter("update", update)
                .getResultList();
        return comments;
    }

    public void modify(UpdateComment comment){
        em.merge(comment);
    }

    public void delete(UpdateComment comment){
        em.remove(comment);
    }
}
