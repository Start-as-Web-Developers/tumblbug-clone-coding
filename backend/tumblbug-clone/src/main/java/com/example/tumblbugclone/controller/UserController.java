package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.UserEmailDuplicatedException;
import com.example.tumblbugclone.Exception.UserIdDuplicatedException;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.managedconst.UserConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(HttpConst.USER_URI)
public class UserController {
    UserRepository userRepository = UserRepository.getUserRepository();

    @GetMapping()
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(HttpConst.USER_SIGNUP_URI)
    public ResponseEntity signUp(@RequestBody User newUser){
        try {
            userRepository.save(newUser);
        }catch (Exception e){
            HttpHeaders errorHeader = new HttpHeaders();
            if(e.getClass() == UserIdDuplicatedException.class){
                log.debug(HttpConst.DUPLICATED_USER_ID_MESSAGE);
                errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE);
            }
            else if(e.getClass() == UserEmailDuplicatedException.class){
                log.debug(HttpConst.DUPLICATED_USER_EMAIL_MESSAGE);
                errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE);
            }
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
