package com.example.tumblbugclone.controller;

;
import com.example.tumblbugclone.dto.PlanDTO;
import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<String> createProject(@RequestBody ProjectAllDTO project, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long projectId = projectService.saveProject(project, userIndex);
        return ResponseEntity.ok(Long.toString(projectId));
    }

    @GetMapping("/{project-id}")
    public ResponseEntity<String> readProject(@PathVariable("project-id") Long projectId, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        ProjectAllDTO project = projectService.readProject(projectId, userIndex);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(project);

        return ResponseEntity.ok(jsonList);
    }
    @PatchMapping("/{project-id}")
    @Transactional
    public ResponseEntity updateProject(@RequestBody Project project, @PathVariable("project-id") Long projectId, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        project.setProjectId(projectId);
        projectService.updateProject(project, userIndex);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{project-id}")
    @Transactional
    public ResponseEntity deleteProject(@PathVariable("project-id") Long projectId, HttpSession session) throws Exception {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);
        projectService.deleteProject(projectId, userIndex);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("{project-id}/plan")
    public ResponseEntity<String> readProjectPlan(@PathVariable("project-id") Long projectId) throws JsonProcessingException {
        PlanDTO planDTO = projectService.readProjectPlan(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        String plan = objectMapper.writeValueAsString(planDTO);

        return ResponseEntity.ok(plan);
    }

}
