package com.example.tumblbugclone.service;


import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ComponentService componentService;

    @Autowired
    public ProductService(ProductRepository productRepository, ComponentService componentService) {
        this.productRepository = productRepository;
        this.componentService = componentService;
    }

    public long saveProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.setProject(productDTO.getProject());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        long productId = productRepository.save(product);

        List<Component> componentList = productDTO.getComponent();
        for(Component component : componentList) {
            component.setProduct(product);
            componentService.saveComponent(component);
        }

        return productId;

    }

    public List<ProductDTO> readProduct(long projectId) throws Exception {
        List<ProductDTO> productDTOList = new ArrayList<>();

        List<Product> productList = productRepository.findProductByProjectId(projectId);
        for(Product product : productList) {

            ProductDTO productDTO = new ProductDTO();
            makeDTOFromProduct(product, productDTO);

            List<Component> componentList = componentService.readComponent(product.getProductId());

            productDTO.setComponent(componentList);

            productDTOList.add(productDTO);
        }

        return productDTOList;
    }

    public void deleteProduct(long projectId) throws Exception {
        List<Product> productList = productRepository.findProductByProjectId(projectId);
        for(Product product : productList) {
            componentService.deleteComponent(product.getProductId());
            productRepository.delete(product.getProductId());
        }

    }

    private static void makeDTOFromProduct(Product product, ProductDTO productDTO) {
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
    }
}
