package com.example.tumblbugclone.controller;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.managedconst.ExceptionConst;
import com.example.tumblbugclone.managedconst.HttpConst;
import com.example.tumblbugclone.service.CommentService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(HttpConst.UPDATE_COMMENT_URI)
public class CommentController {

    private final CommentService commentservice;

    @Autowired
    public CommentController(CommentService commentservice) {
        this.commentservice = commentservice;
    }

    @PostMapping
    public ResponseEntity saveComment(HttpSession session, @PathVariable(name = "update-id") long updateId, @RequestBody String commentString){
        if (session.isNew()) {
            return ResponseEntity.status(ExceptionConst.LoginRequiredStatus).build();
        }
        long userIdx = (long) session.getAttribute(HttpConst.SESSION_USER_INDEX);
        long commentId = 0;
        try{
            commentId = commentservice.saveComment(userIdx, updateId, commentString);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().body(commentId);
    }

    @PatchMapping(HttpConst.UPDATE_COMMENT_ID)
    public ResponseEntity modifyComment
            (HttpSession session, @PathVariable(name = "comment-id") long commentId,@RequestBody String modifyString){
        if (session.isNew()) {
            return ResponseEntity.status(ExceptionConst.LoginRequiredStatus).build();
        }
        long userIdx = (long) session.getAttribute(HttpConst.SESSION_USER_INDEX);
        try {
            commentservice.modifyComment(userIdx, commentId, modifyString);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(HttpConst.UPDATE_COMMENT_ID)
    public ResponseEntity deleteComment(HttpSession session, @PathVariable(name = "comment-id") long commentId){
        if (session.isNew()) {
            return ResponseEntity.status(ExceptionConst.LoginRequiredStatus).build();
        }
        long userIdx = (long) session.getAttribute(HttpConst.SESSION_USER_INDEX);
        try {
            commentservice.deleteComment(userIdx, commentId);
        } catch (TumblbugException e) {
            return ResponseEntity.status(e.getErrorStatus()).build();
        }

        return ResponseEntity.ok().build();
    }
}
