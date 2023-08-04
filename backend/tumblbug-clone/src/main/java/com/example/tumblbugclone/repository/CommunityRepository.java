package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.model.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommunityRepository {

    @PersistenceContext
    EntityManager em;

    public long save(Community community) {
        em.persist(community);
        return community.getCommunityId();
    }

    public Community findCommunityById(long id) throws Exception {
        Community findCommunity = em.find(Community.class, id);
        if(findCommunity == null)
            throw new EmptyResultDataAccessException(1);

        return findCommunity;
    }

    public List<Community> findCommunityByProjectId(long projectId) {
        String jpql = "SELECT c FROM Community c WHERE c.project.id = :projectId";
        TypedQuery<Community> query = em.createQuery(jpql, Community.class);
        query.setParameter("projectId", projectId);
        List<Community> communityList = (List<Community>) query.getResultList();
        return communityList;
    }

    public long modify(Community modifiedComponent) {
        Community resultCommunity = em.merge(modifiedComponent);
        return resultCommunity.getCommunityId();
    }

    public void delete(Long id) throws Exception {
        Community deleteCommunity = findCommunityById(id);
        em.remove(deleteCommunity);
    }


}
