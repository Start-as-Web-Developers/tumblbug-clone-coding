package com.example.tumblbugclone.controller;

;
import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.dto.ProjectDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value =HttpConst.PROJECT_URI, produces = "application/json; charset=utf-8")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createProject(@RequestBody ProjectAllDTO project) throws Exception {
        long projectId = projectService.saveProject(project);
        return ResponseEntity.ok(Long.toString(projectId));
    }

    @GetMapping("/{project-id}")
    public ResponseEntity<String> readProject(@PathVariable("project-id") Long projectId) throws Exception {
        ProjectAllDTO project = projectService.readProject(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(project);

        return ResponseEntity.ok(jsonList);
    }
    @PatchMapping("/{project-id}")
    @Transactional
    public ResponseEntity updateProject(@RequestBody Project project, @PathVariable("project-id") Long projectId) throws Exception {
        project.setProjectId(projectId);
        projectService.updateProject(project);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{project-id}")
    @Transactional
    public ResponseEntity deleteProject(@PathVariable("project-id") Long projectId) throws Exception {
        projectService.deleteProject(projectId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
