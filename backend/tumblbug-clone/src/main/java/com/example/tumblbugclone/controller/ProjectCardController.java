package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.managedconst.HttpConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(HttpConst.PROJECT_LIST_URI)
public class ProjectCardController {

    @GetMapping()
    public void getOngoingProject(){

    }
}
