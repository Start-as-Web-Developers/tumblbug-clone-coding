package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import jakarta.persistence.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    EntityManager em;

    public long save(Product product) {
        em.persist(product);
        return product.getProductId();
    }

    public Product findProductById(long id) throws EmptyResultDataAccessException {
        Product findProduct = em.find(Product.class, id);
        if(findProduct == null)
            throw new EmptyResultDataAccessException(1);

        return findProduct;
    }

    public List <Product> findProductByProjectId(long projectId){
        String jpql = "SELECT p FROM Product p WHERE p.project.id = :projectId";
        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        query.setParameter("projectId", projectId);
        List<Product> productList = (List<Product>) query.getResultList();
        return productList;
    }

    public long modify(Product modifiedProduct) {
        Product resultProduct= em.merge(modifiedProduct);
        return resultProduct.getProductId();
    }

    public void delete(Long id) {
        Product deleteProduct = findProductById(id);
        em.remove(deleteProduct);
    }
}
