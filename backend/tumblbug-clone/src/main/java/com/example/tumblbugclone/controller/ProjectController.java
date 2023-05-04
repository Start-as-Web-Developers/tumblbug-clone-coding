package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.projectException.ProjectCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.*;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.service.projectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value =HttpConst.PROJECT_URI, produces = "application/json; charset=utf-8")
public class ProjectController {

    ProjectRepository projectRepository = ProjectRepository.getProjectRepository();

    @GetMapping("/{project-id}")
    public ResponseEntity<String> readProject(@PathVariable("project-id") Long projectId) throws ProjectCantFindException, JsonProcessingException, UserCantFindException, UnregisterUserException {
        ProjectDTO projectDTO = projectService.make(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(projectDTO);

        return ResponseEntity.ok(jsonList);
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody ProjectDTO projects) throws Exception {

        Project project = projects.getProjects();
        List<Product> products = projects.getProducts();
        List<Component> components = projects.getComponents();

        projectService.save(project, products, components);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{project-id}")
    public ResponseEntity updateCommunity(@RequestBody Project project, @PathVariable("project-id") Long projectId) throws Exception {
        project.setProjectId(projectId);
        projectRepository.updateProject(project);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{project-id}")
    public ResponseEntity updateCommunity(@PathVariable("project-id") Long projectId) throws Exception {
        projectService.delete(projectId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
