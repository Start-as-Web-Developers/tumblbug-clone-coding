/*
package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.ProjectCard;
import com.example.tumblbugclone.service.ProjectCardService;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = HttpConst.PROJECT_LIST_URI, produces = "application/json; charset=utf-8")
public class ProjectCardController {

    ProjectCardService projectCardService = new ProjectCardService();

    @GetMapping
    public ResponseEntity defaultAccess(){
        return ResponseEntity.ok("");
    }

    @GetMapping(HttpConst.ON_GOING)
    public ResponseEntity<List<ProjectCard>> getOngoingProject(@RequestParam(required = false,name = "start-idx") String startIdx){
        List<ProjectCard> projectCards;

        try{
            if(startIdx == null) {
                projectCards = projectCardService.findOngoingFromStart();
            }else{
                Long startIdxNum = Long.parseLong(startIdx);
                ResponseEntity<List<ProjectCard>> response = checkStartIdx(startIdxNum);
                if (response != null)
                    return response;
                projectCards = projectCardService.findOngoingFromIdx(startIdxNum);
            }
        } catch (ParseException e) {
            log.debug("Project date format error");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return ResponseEntity.ok(projectCards);
    }



    @GetMapping(HttpConst.PRE_LAUNCHING)
    public ResponseEntity<List<ProjectCard>> getPreLaunchingProject(@RequestParam(required = false,name = "start-idx") String startIdx){
        List<ProjectCard> projectCards;

        try{
            if(startIdx == null) {
                projectCards = projectCardService.findPreLaunchingFromStart();
            }else{
                Long startIdxNum = Long.parseLong(startIdx);
                ResponseEntity<List<ProjectCard>> response = checkStartIdx(startIdxNum);
                if (response != null)
                    return response;
                projectCards = projectCardService.findPreLaunchingFromIdx(startIdxNum);
            }
        } catch (ParseException e) {
            log.debug("Project date format error");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return ResponseEntity.ok(projectCards);
    }

    private ResponseEntity<List<ProjectCard>> checkStartIdx(Long startIdxNum) {
        if(startIdxNum % 20 != 0){
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, "start-idx 값이 잘못되었습니다.");
            return ResponseEntity
                    .badRequest()
                    .headers(responseHeader).build();
        }
        return null;
    }
}
*/
