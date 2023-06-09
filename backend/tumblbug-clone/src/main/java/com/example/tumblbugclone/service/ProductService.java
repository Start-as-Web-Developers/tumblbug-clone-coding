package com.example.tumblbugclone.service;


import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectException.ProjectCantModify;
import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.repository.ProductRepository;
import com.example.tumblbugclone.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ComponentService componentService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ComponentService componentService, ProjectRepository projectRepository) {
        this.productRepository = productRepository;
        this.componentService = componentService;
        this.projectRepository = projectRepository;
    }

    public long saveProduct(ProductDTO productDTO, long projectId, long userIndex) throws TumblbugException {

        Project project = projectRepository.findProjectById(projectId);

        if(project.getUser().getUserIdx() != userIndex)
            throw new ProjectCantModify();

        Product product = new Product();
        product.setProject(project);
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

    public ProductDTO readProductById(long productId) throws Exception {

        ProductDTO productDTO = new ProductDTO();
        Product product = productRepository.findProductById(productId);
        makeDTOFromProduct(product, productDTO);
        List<Component> componentList = componentService.readComponent(productId);
        productDTO.setComponent(componentList);

        return productDTO;
    }

    public void deleteProduct(long projectId) throws Exception {
        List<Product> productList = productRepository.findProductByProjectId(projectId);
        for(Product product : productList) {
            componentService.deleteComponent(product.getProductId());
            productRepository.delete(product.getProductId());
        }

    }

    public void deleteProductById(long productId, long userIndex) throws Exception {
        Product product = productRepository.findProductById(productId);

        if(product.getProject().getUser().getUserIdx() != userIndex)
            throw new ProjectCantModify();

        componentService.deleteComponent(productId);
        productRepository.delete(productId);
    }

    public long patchProduct(Product product, long projectId, long userIndex) throws ProjectCantModify {

        Project project = projectRepository.findProjectById(projectId);

        if(project.getUser().getUserIdx() != userIndex)
            throw new ProjectCantModify();

        product.setProject(project);
        return productRepository.modify(product);
    }

    private static void makeDTOFromProduct(Product product, ProductDTO productDTO) {
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
    }
}
