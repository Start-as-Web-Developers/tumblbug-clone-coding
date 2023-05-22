package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.userexception.*;
import com.example.tumblbugclone.dto.UserLoginDTO;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.dto.UserSendingDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping()
    public ResponseEntity test() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity signUp(@RequestBody UserReceivingDTO newUserDTO) {

        HttpHeaders errorHeader = new HttpHeaders();

        try {
            userService.join(newUserDTO);
        } catch (UserIdDuplicatedException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        } catch (UserEmailDuplicatedException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        } catch (UserDTOConvertException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        return new ResponseEntity(HttpStatus.OK);
    }


    @PatchMapping("/{userIdx}")
    public ResponseEntity update(@PathVariable String userIdx, @RequestBody UserReceivingDTO modifiedUserDTO) {

        HttpHeaders errorHeader = new HttpHeaders();
        try {
            userService.modify(modifiedUserDTO);
        } catch (UserCantModifyIdException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.CANT_MODIFY_USER_ID_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        } catch (UserDTOConvertException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());

            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        return ResponseEntity.ok("");
    }


    @DeleteMapping
    public ResponseEntity unregister(@RequestBody UserReceivingDTO deleteUser) {

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            userService.unregiste(deleteUser);
        } catch (UserDTOConvertException e) {
            httpHeaders.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(httpHeaders)
                    .build();
        }

        return ResponseEntity.ok().build();
    }

    //==검증 필요 ==//
    @GetMapping(HttpConst.USER_LOGIN_URL)
    public ResponseEntity login(@RequestBody UserLoginDTO loginDTO, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try {
            userService.login(loginDTO, session);
        } catch (UserCantFindException | WrongPasswordException e) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(headers)
                    .build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(HttpConst.USER_LOGOUT_URL)
    public ResponseEntity logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.LOGIN_NECESSARY);
            return ResponseEntity.badRequest()
                    .headers(headers)
                    .build();
        }

        session.invalidate();
        return ResponseEntity.ok().build();
    }

    //== 리팩토링 완료 ==//
}
