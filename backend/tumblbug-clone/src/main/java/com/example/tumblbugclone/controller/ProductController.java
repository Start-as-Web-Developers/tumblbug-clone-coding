package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectException.ProjectCantModify;
import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.service.ProductService;
import com.example.tumblbugclone.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
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
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createProduct(@RequestBody ProjectAllDTO projectAllDTO, @PathVariable("project-id") Long projectId, HttpSession session) throws TumblbugException {
        List<ProductDTO> products = projectAllDTO.getProduct();
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        for(ProductDTO productDTO : products) {
            try {
                productService.saveProduct(productDTO, projectId, userIndex);
            } catch (TumblbugException e) {
                return ResponseEntity.status(e.getErrorStatus()).build();
            }
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
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable("project-id") Long projectId, HttpSession session) throws ProjectCantModify {

        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long productId;
        try {
            productId = productService.patchProduct(product, projectId, userIndex);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok(Long.toString(productId));
    }

    @DeleteMapping("/{product-id}")
    @Transactional
    public ResponseEntity<String> deleteProduct(@PathVariable("product-id") Long productId, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        try {
            productService.deleteProductById(productId, userIndex);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok(Long.toString(productId));
    }
}
