package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.service.ComponentService;
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
@RequestMapping(value = HttpConst.COMPONENT_URI, produces = "application/json; charset=utf-8")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createComponent(@RequestBody Component component, @PathVariable("product-id") long productId) {
        Product product = new Product();
        product.setProductId(productId);

        component.setProduct(product);
        long componentId = componentService.saveComponent(component);
        return ResponseEntity.ok(Long.toString(componentId));
    }

    @GetMapping
    public ResponseEntity<String> readComponent(@PathVariable("product-id") long productId) throws Exception {
        List<Component> componentList = componentService.readComponent(productId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(componentList);

        return ResponseEntity.ok(jsonList);
    }

    @PatchMapping("/{component-id}")
    @Transactional
    public ResponseEntity<String> updateComponent(@RequestBody Component component, @PathVariable("product-id") long productId, @PathVariable("component-id") long componentId) {
        Product product = new Product();
        product.setProductId(productId);

        component.setProduct(product);
        component.setComponentId(componentId);

        long updateComponentId = componentService.updateComponent(component);
        return ResponseEntity.ok(Long.toString(updateComponentId));


    }

    @DeleteMapping("/{component-id}")
    @Transactional
    public ResponseEntity deleteComponent(@PathVariable("component-id") long componentId) throws Exception {
        componentService.deleteComponentById(componentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
