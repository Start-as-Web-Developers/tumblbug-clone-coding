package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.UserIdDuplicatedException;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.managedconst.UserConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    UserRepository userRepository = UserRepository.getUserRepository();

    @GetMapping()
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(User newUser){
        try {
            userRepository.save(newUser);
        }catch (Exception e){
            HttpHeaders errorHeader = new HttpHeaders();
            if(e.getClass() == UserIdDuplicatedException.class){
                errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE);
            }
            else if(e.getClass() == UserEmailDuplicatedException.class){
                errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE);
            }
            return new ResponseEntity(errorHeader, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
