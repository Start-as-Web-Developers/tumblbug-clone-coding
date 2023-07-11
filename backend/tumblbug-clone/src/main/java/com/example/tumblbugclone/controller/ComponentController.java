package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.componentException.ComponentCantModify;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.service.ComponentService;
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
@RequestMapping(value = HttpConst.COMPONENT_URI, produces = "application/json; charset=utf-8")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createComponent(@RequestBody Component component, @PathVariable("product-id") long productId, HttpSession session) throws ComponentCantModify {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long componentId;
        try {
            componentId = componentService.saveComponent(component, productId, userIndex);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

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
    public ResponseEntity<String> updateComponent(@RequestBody Component component, @PathVariable("product-id") long productId, @PathVariable("component-id") long componentId, HttpSession session) {

        component.setComponentId(componentId);
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long updateComponentId;

        try {
            updateComponentId = componentService.updateComponent(component, productId, userIndex);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }
        return ResponseEntity.ok(Long.toString(updateComponentId));


    }

    @DeleteMapping("/{component-id}")
    @Transactional
    public ResponseEntity deleteComponent(@PathVariable("product-id") long productId, @PathVariable("component-id") long componentId, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        try {
            componentService.deleteComponentById(componentId, productId, userIndex);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }


        return new ResponseEntity(HttpStatus.OK);
    }
}
