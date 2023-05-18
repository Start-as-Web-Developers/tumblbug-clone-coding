package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.service.CommunityService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping(HttpConst.COMMUNITY_URI)
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> createCommunity(@RequestBody Community community, @PathVariable("project-id") Long projectId) throws Exception {
        Project project = new Project();
        project.setProjectId(projectId);

        community.setProject(project);
        long communityId = communityService.writeCommunity(community);
        return ResponseEntity.ok(Long.toString(communityId));
    }

    @GetMapping
    public ResponseEntity<String> readCommunity(@PathVariable("project-id") Long projectId) throws JsonProcessingException {
        List<Community> communityList = communityService.CommunityList(projectId);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(communityList);

        return ResponseEntity.ok(jsonList);
    }

    @PatchMapping("/{community-id}")
    @Transactional
    public ResponseEntity<String> updateCommunity(@RequestBody Community community, @PathVariable("project-id") Long projectId, @PathVariable("community-id") Long communityId) throws Exception {
        Project project = new Project();
        project.setProjectId(projectId);

        community.setProject(project);
        community.setCommunityId(communityId);

        long modiCommunityId = communityService.modify(community);
        return ResponseEntity.ok(Long.toString(modiCommunityId));
    }

    @DeleteMapping("/{community-id}")
    @Transactional
    public ResponseEntity deleteCommunity(@PathVariable("community-id") Long communityId) throws Exception {
        communityService.delete(communityId);
        return new ResponseEntity(HttpStatus.OK);
    }



}
