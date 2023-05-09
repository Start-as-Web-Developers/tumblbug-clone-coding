/*
package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.Community;
import com.example.tumblbugclone.repository.CommunityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(HttpConst.COMMUNITY_URI)
public class CommunityController {

    CommunityRepository communityRepository = CommunityRepository.getCommunityRepository();

    @PostMapping
    public ResponseEntity createCommunity(@RequestBody Community community, @PathVariable("project-id") String projectId) {

        community.setProjectId(Long.parseLong(projectId));
        try {
            communityRepository.save(community);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> readCommunity(@PathVariable("project-id") String projectId) throws JsonProcessingException {

        List<Map<String, Object>> communityList = communityRepository.findCommunityByProjectId(Long.valueOf(projectId));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(communityList);


        return ResponseEntity.ok(jsonList);
    }

    @PatchMapping("/{community-id}")
    public ResponseEntity updateCommunity(@RequestBody Community community, @PathVariable("project-id") String projectId, @PathVariable("community-id") String communityId) throws Exception {
        community.setProjectId(Long.parseLong(projectId));
        community.setCommunityId(Long.parseLong(communityId));
        communityRepository.updateCommunity(community);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{community-id}")
    public ResponseEntity updateCommunity(@PathVariable("community-id") String communityId) throws Exception {
        communityRepository.deleteCommunity(Long.valueOf(communityId));
        return new ResponseEntity(HttpStatus.OK);
    }


}
*/
