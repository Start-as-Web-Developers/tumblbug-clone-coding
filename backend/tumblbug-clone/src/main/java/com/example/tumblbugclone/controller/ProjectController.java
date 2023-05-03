package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.userexception.ProjectCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.Exception.userexception.UserCantModifyIdException;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(HttpConst.PROJECT_URI)
public class ProjectController {

    ProjectRepository projectRepository = ProjectRepository.getProjectRepository();


    @GetMapping("/{project-id}")
    public ResponseEntity getProject(@PathVariable String givenProjectIdx) {

        long projectIdx = Long.parseLong(givenProjectIdx);
        HttpHeaders responseHeader = new HttpHeaders();
        Project project = new Project();

        try {
            project = projectRepository.findProjectById(projectIdx);
        } catch (ProjectCantFindException e) {
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.NO_USER_FIND_MESSAGE);

            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        }

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
