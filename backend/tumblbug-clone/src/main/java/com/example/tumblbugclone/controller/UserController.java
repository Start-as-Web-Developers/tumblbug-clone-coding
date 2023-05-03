package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.userexception.*;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping(HttpConst.USER_URI)
public class UserController {
    UserRepository userRepository = UserRepository.getUserRepository();

    @GetMapping()
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
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


    @PatchMapping("/{userIdx}")
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

    }

    @DeleteMapping
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
    }
}
