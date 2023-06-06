package com.example.tumblbugclone.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class RootController {

    @GetMapping
    public ResponseEntity healthyCheck(){
        return ResponseEntity.ok().build();
    }
}
