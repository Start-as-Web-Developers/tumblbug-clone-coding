package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.userexception.*;
import com.example.tumblbugclone.dto.UserDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import com.example.tumblbugclone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(value = HttpConst.USER_URI, produces = "application/json; charset=utf-8")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping()
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity signUp(@RequestBody UserDTO newUser){

        User user = new User();
        HttpHeaders errorHeader = new HttpHeaders();
        boolean notNullIsNull = false;

        if(newUser.getUserName() == null){
            notNullIsNull = true;
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.USERNAME_IS_NULL);
        }
        user.setUserName(newUser.getUserName());

        if(newUser.getUserId() == null){
            notNullIsNull = true;
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.USERID_IS_NULL);
        }
        user.setUserId(newUser.getUserId());

        if(newUser.getUserPassword() == null){
            notNullIsNull = true;
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.USERPASSWORD_IS_NULL);
        }
        user.setUserPassword(newUser.getUserPassword());

        if(newUser.getUserPassword() == null){
            notNullIsNull = true;
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.USEREMAIL_IS_NULL);
        }
        user.setUserEmail(newUser.getUserEmail());

        if(notNullIsNull){
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        try{
            userService.join(user);
        }catch (DataIntegrityViolationException e){
            //Email 중복과 Id 중복이 구분이 안됨
        }


        return new ResponseEntity(HttpStatus.OK);
    }
    //== 리팩토링 완료 ==//

    /*@PostMapping
    public ResponseEntity signUp(@RequestBody User newUser){
        try {
            userRepository.save(newUser);
        }catch (Exception e){
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
        }*//*

        return new ResponseEntity(HttpStatus.OK);
    }*/


    /*@PatchMapping("/{userIdx}")
    public ResponseEntity update(@PathVariable String userIdx, @RequestBody User modifiedUser){

        long modifyUserIdx = Long.parseLong(userIdx);
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            userRepository.findUserByIdx(modifyUserIdx);
            userRepository.modify(modifiedUser);
        }catch(UserCantFindException e){
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.NO_USER_FIND_MESSAGE);

            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        }catch (UserCantModifyIdException e){
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.CANT_MODIFY_USER_ID_MESSAGE);

            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        }catch (UnregisterUserException e){
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.UNREGISTER_USER_MESSAGE);

            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        }

        return ResponseEntity.ok("");

    }*/

    /*@DeleteMapping
    public ResponseEntity unregister(@RequestBody User deleteUser){

        HttpHeaders responseHeader = new HttpHeaders();
        try {
            userRepository.unregister(deleteUser.getUserIdx());
        }catch (UserCantFindException e){
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.NO_USER_FIND_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        } catch (UnregisterUserException e) {
            responseHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.UNREGISTER_USER_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(responseHeader)
                    .body("");
        }

        return ResponseEntity.ok("");
    }*/
}
