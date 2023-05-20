package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.userexception.*;
import com.example.tumblbugclone.dto.UserReceivingDTO;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.UserService;
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
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping()
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity signUp(@RequestBody UserReceivingDTO newUserDTO){

        User user = null;
        HttpHeaders errorHeader = new HttpHeaders();

        try{
            user = convertUserDTO2User(newUserDTO);
        } catch (UserDTOConvertException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        try{
            userService.join(user);
        }catch (UserIdDuplicatedException e){
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_ID_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }catch (UserEmailDuplicatedException e){
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.DUPLICATED_USER_EMAIL_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        return new ResponseEntity(HttpStatus.OK);
    }



    @PatchMapping("/{userIdx}")
    public ResponseEntity update(@PathVariable String userIdx, @RequestBody UserReceivingDTO modifiedUserDTO){

        User modifiedUser;
        HttpHeaders errorHeader = new HttpHeaders();
        try {
            modifiedUser = convertUserDTO2User(modifiedUserDTO);
        } catch (UserDTOConvertException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        try {
            userService.modify(modifiedUser);
        } catch (UserCantModifyIdException e) {
            errorHeader.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, HttpConst.CANT_MODIFY_USER_ID_MESSAGE);
            return ResponseEntity.badRequest()
                    .headers(errorHeader)
                    .body("");
        }

        return ResponseEntity.ok("");
    }


    @DeleteMapping
    public ResponseEntity unregister(@RequestBody UserReceivingDTO deleteUser){

        HttpHeaders httpHeaders = new HttpHeaders();
        User user;
        try {
            user = convertUserDTO2User(deleteUser);
        } catch (UserDTOConvertException e) {
            httpHeaders.set(HttpConst.HEADER_NAME_ERROR_MESSAGE, e.getMessage());
            return ResponseEntity.badRequest()
                    .headers(httpHeaders)
                    .build();
        }

        userService.unregiste(user);

        return ResponseEntity.ok().build();
    }


    private User convertUserDTO2User(UserDTO newUserDTO) throws UserDTOConvertException {
        User user = new User();
        user.setUserIdx(newUserDTO.getUserIdx());
        if(newUserDTO.getUserName() == null){
            throw new UserDTOConvertException(HttpConst.USERNAME_IS_NULL);
        }
        user.setUserName(newUserDTO.getUserName());

        if(newUserDTO.getUserId() == null){
            throw new UserDTOConvertException(HttpConst.USERID_IS_NULL);
        }
        user.setUserId(newUserDTO.getUserId());

        if(newUserDTO.getUserPassword() == null){
            throw new UserDTOConvertException(HttpConst.USERPASSWORD_IS_NULL);
        }
        user.setUserPassword(newUserDTO.getUserPassword());

        if(newUserDTO.getUserPassword() == null){
            throw new UserDTOConvertException(HttpConst.USEREMAIL_IS_NULL);
        }
        user.setUserEmail(newUserDTO.getUserEmail());

        //추가 정보 변환

        return user;
    }


    //== 리팩토링 완료 ==//
}
