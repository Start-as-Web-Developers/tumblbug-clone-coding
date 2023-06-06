package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.dto.UserLoginDTO;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = HttpConst.USER_URI, produces = "application/json; charset=utf-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUser(HttpSession session) {
        long userIndex = (long)session.getAttribute(HttpConst.SESSION_USER_INDEX);

        UserSendingDTO userByIndex = userService.findUserByIndex(userIndex);

        return ResponseEntity.ok()
                .body(userByIndex);
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity checkUser(@PathVariable String userIdx) {

        UserSendingDTO userByIndex = userService.findUserByIndex(Long.parseLong(userIdx));


        return ResponseEntity.ok()
                .body(userByIndex);
    }

    @PostMapping
    public ResponseEntity signUp(@RequestBody UserReceivingDTO newUserDTO, HttpSession session) {

        long userIndex;
        try {
            userIndex = userService.join(newUserDTO);
        } catch (TumblbugException e) {
            return ResponseEntity
                    .status(e.getErrorStatus())
                    .build();
        }

        HttpHeaders headers = new HttpHeaders();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, userIndex);
        session.setMaxInactiveInterval(60 * 60 * 24);

        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId())
                .domain("zangsu-backend.store")
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(60 * 60 * 24)
                .build();
        headers.set("set-cookie", cookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }


    @PatchMapping("/{userIdx}")
    public ResponseEntity update(@RequestBody UserReceivingDTO modifiedUserDTO) {

        try {
            userService.modify(modifiedUserDTO);
        }catch (TumblbugException e){
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok("");
    }


    @DeleteMapping
    public ResponseEntity unregister(@RequestBody UserReceivingDTO deleteUser) {

        try {
            userService.unregiste(deleteUser);
        }catch (TumblbugException e){
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping(HttpConst.USER_LOGIN_URL)
    public ResponseEntity login(@RequestBody UserLoginDTO loginDTO, HttpSession session) {
        long loginUserIndex;
        try {
            log.info("login Id:{}, PW:{}/", loginDTO.getUserId(), loginDTO.getUserPassword());
            loginUserIndex = userService.login(loginDTO);
        } catch (TumblbugException e){
            return ResponseEntity.status(e.getErrorStatus())
                    .build();
        }

        HttpHeaders headers = new HttpHeaders();
        session.setAttribute(HttpConst.SESSION_USER_INDEX, loginUserIndex);
        session.setMaxInactiveInterval(60 * 60 * 24);

        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", session.getId())
                .domain("zangsu-backend.store")
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(60 * 60 * 24)
                .build();
        headers.set("set-cookie", cookie.toString());

        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }

    @PostMapping(HttpConst.USER_LOGOUT_URL)
    public ResponseEntity logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return ResponseEntity
                    .status(ExceptionConst.LoginRequiredStatus)
                    .build();
        }

        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
