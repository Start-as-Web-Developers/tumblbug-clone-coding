
package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.projectlistexception.StartIndexException;
import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.service.ProjectCardService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = HttpConst.PROJECT_LIST_URI, produces = "application/json; charset=utf-8")
public class ProjectCardController {

    private final ProjectCardService projectCardService;

    public ProjectCardController(ProjectCardService projectCardService){
        this.projectCardService = projectCardService;
    }

    @GetMapping
    public ResponseEntity defaultAccess(){
        return ResponseEntity.ok("");
    }

    @GetMapping(HttpConst.ON_GOING)
    public ResponseEntity<List<ProjectCardDTO>> getOngoingProject(@RequestParam(required = false,name = "start-idx")int startIdx){
        List<ProjectCardDTO> projectCards;

        try {
            projectCards = projectCardService.findOngoingFromIdx(startIdx);
        } catch (StartIndexException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, "start-idx should be multiple of 20");

            return ResponseEntity.badRequest()
                    .headers(headers)
                    .build();
        }
        return ResponseEntity.ok()
                .body(projectCards);
    }

    @GetMapping(HttpConst.PRE_LAUNCHING)
    public ResponseEntity<List<ProjectCardDTO>> getPreLaunchingProject(@RequestParam(required = false,name = "start-idx") int startIdx){
        List<ProjectCardDTO> projectCards;

        try {
            projectCards = projectCardService.findPreLaunchingFromIdx(startIdx);
        } catch (StartIndexException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, "start-idx should be multiple of 20");

            return ResponseEntity.badRequest()
                    .headers(headers)
                    .build();
        }
        return ResponseEntity.ok()
                .body(projectCards);
    }

}
