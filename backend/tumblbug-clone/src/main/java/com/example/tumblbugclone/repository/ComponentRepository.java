package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComponentRepository {

    @PersistenceContext
    EntityManager em;

    public long save(Component component) {
        em.persist(component);
        return component.getComponentId();
    }

    public Component findComponentById(long id) throws Exception {
        Component findComponent = em.find(Component.class, id);
        if(findComponent == null)
            throw new EmptyResultDataAccessException(1);
        return findComponent;
    }

    public List<Component> findComponentByProductId(long productId) throws Exception {
        String jpql = "SELECT c FROM Component c WHERE c.product.id = :productId";
        TypedQuery<Component> query = em.createQuery(jpql, Component.class);
        query.setParameter("productId", productId);
        List<Component> componentList = (List<Component>) query.getResultList();
        return componentList;
    }

    public long modify(Component modifiedComponent) {
        Component resultComponent = em.merge(modifiedComponent);
        return resultComponent.getComponentId();
    }

    public void delete(Long id) throws Exception {
        Component deleteComponent = findComponentById(id);
        em.remove(deleteComponent);
    }
}
