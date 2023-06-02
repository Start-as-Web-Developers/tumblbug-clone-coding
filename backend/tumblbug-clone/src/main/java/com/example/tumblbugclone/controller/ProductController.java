package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.service.ProductService;
import com.example.tumblbugclone.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = HttpConst.PRODUCT_URI, produces = "application/json; charset=utf-8")
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService, ProjectService projectService) {
        this.productService = productService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createProduct(@RequestBody ProjectAllDTO projectAllDTO, @PathVariable("project-id") Long projectId) {
       List<ProductDTO> products = projectAllDTO.getProduct();

        for(ProductDTO productDTO : products) {
            Project project = new Project();
            project.setProjectId(projectId);

            productDTO.setProject(project);
            productService.saveProduct(productDTO);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> readProduct(@PathVariable("project-id") Long projectId) throws Exception {
        List<ProductDTO> productDTOList = productService.readProduct(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(productDTOList);

        return ResponseEntity.ok(jsonList);
    }

    @PatchMapping("/{product-id}")
    @Transactional
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable("project-id") Long projectId) {
        Project project = new Project();
        project.setProjectId(projectId);
        product.setProject(project);

        long productId = productService.patchProduct(product);
        return ResponseEntity.ok(Long.toString(productId));
    }

    @DeleteMapping("/{product-id}")
    @Transactional
    public ResponseEntity<String> deleteProduct(@PathVariable("product-id") Long productId) throws Exception {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(Long.toString(productId));
    }
}
