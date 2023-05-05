package com.example.tumblbugclone.repository;


import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.User;

import java.util.*;

public class ProductRepository {

    private static Long id = 0L;
    private static final ProductRepository productRepository = new ProductRepository();
    private static HashMap<Long, Product> productDB = new HashMap<>();
    private static UserRepository userDB = UserRepository.getUserRepository();

    private ProductRepository() {};

    public static ProductRepository getProductRepository() {return productRepository;};


    public void clear() {
        id = 0L;
        productDB.clear();
    }

    public long save(Product product) throws Exception {

        // 테스트를 위한 임시 user 삽입
        userDB.clear();
        User user = new User("hi", "hi", "hi", "hi");
        userDB.save(user);
        product.setUserIdx(1L);

        id++;
        product.setProductId(id);
        productDB.put(id, product);

        return id;
    }

    public List<Product> findProductByProjectId(long projectId) {
        List<Product> products = new ArrayList<>();

        productDB.forEach((key, value) -> {
            if (value.getProjectId() == projectId) {
                products.add(value);
            }
        });

        return products;
    }

    public void deleteProductById(long projectId) {
        List<Long> productKey = new ArrayList<>();
        productDB.forEach((key, value) -> {
            if (value.getProjectId() == projectId) {
                productKey.add(key);
            }
        });

        for(Long key: productKey)
            productDB.remove(key);
    }
}
