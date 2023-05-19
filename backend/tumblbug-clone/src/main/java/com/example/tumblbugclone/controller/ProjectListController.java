package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectListController {

    final private ProjectService projectService;

    @Autowired
    public ProjectListController(ProjectService projectService){this.projectService = projectService;}


}
