
package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectlistexception.StartIndexException;
import com.example.tumblbugclone.dto.ProjectCardDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.ProjectCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Iterator;
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
    public ResponseEntity<List<ProjectCardDTO>> getOngoingProject
            (@RequestParam(required = false,name = "start-idx")Integer startIdx, @RequestParam(required = false, name = "sort")String sort){
        List<ProjectCardDTO> projectCards;

        Date today = new Date();

        if(startIdx == null)
            startIdx = 0;
        try {
            projectCards = projectCardService.findOngoingFromIdx(startIdx, sort, today);
        } catch (TumblbugException e) {
            return ResponseEntity
                    .status(e.getErrorStatus())
                    .build();
        }

        Iterator<ProjectCardDTO> iter = projectCards.iterator();
        while(iter.hasNext()){
            ProjectCardDTO card = iter.next();
            System.out.println("card = " + card.getTitle());
        }

        return ResponseEntity.ok()
                .body(projectCards);
    }

    @GetMapping(HttpConst.PRE_LAUNCHING)
    public ResponseEntity<List<ProjectCardDTO>> getPreLaunchingProject
            (@RequestParam(required = false,name = "start-idx") Integer startIdx, @RequestParam(required = false, name = "sort")String sort){
        List<ProjectCardDTO> projectCards;

        if(startIdx == null)
            startIdx = 0;
        try {
            projectCards = projectCardService.findPreLaunchingFromIdx(startIdx, sort);
        } catch (TumblbugException e) {
            return ResponseEntity
                    .status(e.getErrorStatus())
                    .build();
        }
        return ResponseEntity.ok()
                .body(projectCards);
    }
}
