package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.updateException.CantFindUpdateException;
import com.example.tumblbugclone.Exception.updateException.UpdateCantModifyModifiedToFalse;
import com.example.tumblbugclone.Exception.userexception.LoginRequiredException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.ProjectUpdateDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.ProjectUpdateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(HttpConst.UPDATE_URI)
public class UpdateController {

    ProjectUpdateService projectUpdateService;

    @Autowired
    public UpdateController(ProjectUpdateService projectUpdateService){
        this.projectUpdateService = projectUpdateService;
    }

    @GetMapping
    public ResponseEntity findUpdates(@PathVariable("project-id")long projectId){

        List<ProjectUpdateDTO> updates;
        try {
            updates = projectUpdateService.findUpdateList(projectId);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().body(updates);
    }

    @PostMapping()
    public ResponseEntity save(@PathVariable("project-id")long projectId, @RequestBody String updateContent, HttpSession session){
        if(session.isNew()){
            return ResponseEntity
                    .status(ExceptionConst.LoginRequiredStatus)
                    .build();
        }

        long userIndex = (long) session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long updateIndex;

        try {
            updateIndex = projectUpdateService.save(userIndex, projectId, updateContent);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().body(Long.toString(updateIndex));
    }

    @GetMapping(HttpConst.UPDATE_ID)
    public ResponseEntity<ProjectUpdateDTO> findUpdate(@PathVariable("project-id")long projectId, @PathVariable("update-id")long updateId) {
        ProjectUpdateDTO projectUpdateDTO;
        try {
            projectUpdateDTO = projectUpdateService.findProjectUpdateDTO(updateId);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        if(projectId != projectUpdateDTO.getProjectId())
            return ResponseEntity.status(ExceptionConst.NotMatchProjectId).build();

        System.out.println(projectUpdateDTO);
        return ResponseEntity.ok()
                .body(projectUpdateDTO);
    }

    @PatchMapping(HttpConst.UPDATE_ID)
    public ResponseEntity<ProjectUpdateDTO> modifyUpdate(
            @PathVariable("update-id")long updateId, @RequestBody String newContent, HttpSession session){

        long userIdx;
        if(session.isNew()){
            return ResponseEntity.status(ExceptionConst.LoginRequiredStatus).build();
        }
        userIdx = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        ProjectUpdateDTO updateDTO;
        try {
            updateDTO = projectUpdateService.update(userIdx, updateId, newContent);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok()
                .body(updateDTO);
    }

    @DeleteMapping(HttpConst.UPDATE_ID)
    public ResponseEntity deleteUpdate( @PathVariable("update-id")long updateId, HttpSession session){
        long userIdx;
        if(session.isNew()){
            return ResponseEntity.status(ExceptionConst.LoginRequiredStatus).build();
        }
        userIdx = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        try {
            projectUpdateService.delete(userIdx, updateId);
        }catch (TumblbugException e){
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().build();
    }


}
