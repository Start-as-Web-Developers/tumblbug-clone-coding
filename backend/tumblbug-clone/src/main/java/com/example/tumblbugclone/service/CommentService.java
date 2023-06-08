package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.userexception.UnauthorizedUserException;
import com.example.tumblbugclone.dto.CommentDTO;
import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.UpdateComment;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ProjectUpdateService updateService;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, ProjectUpdateService updateService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.updateService = updateService;
    }


    public long saveComment(long userIdx, long updateId, String content) throws TumblbugException {
        User user;
        ProjectUpdate update;
        try {
            user = userService.findUserByIndex(userIdx);
            update = updateService.findProjectUpdate(updateId);
        } catch (TumblbugException e) {
            throw e;
        }
        UpdateComment comment = new UpdateComment();
        comment.setUser(user);
        comment.setUpdate(update);
        comment.setComment(content);
        comment.setModifiedTime(new Date());

        commentRepository.save(comment);

        return comment.getId();
    }

    public List<CommentDTO> getComments(long updateId) throws TumblbugException {
        ProjectUpdate update;
        try {
            update = updateService.findProjectUpdate(updateId);
        } catch (TumblbugException e) {
           throw e;
        }

        List<UpdateComment> comments = commentRepository.getAllComments(update);
        List<CommentDTO> commentDTOs = new LinkedList<>();

        for (UpdateComment comment : comments) {
            commentDTOs.add(convertCommnet2DTO(comment));
        }
        return commentDTOs;
    }

    public void modifyComment(long userIdx, long commentId, String modifyString) throws TumblbugException{
        UpdateComment comment = commentRepository.getComment(commentId);

        if(comment.getUser().getUserIdx() != userIdx)
            throw new UnauthorizedUserException();
        comment.setComment(modifyString);
        comment.setModifiedTime(new Date());
        commentRepository.modify(comment);
    }

    public void deleteComment(long userIdx, long commentId) throws TumblbugException{
        UpdateComment comment = commentRepository.getComment(commentId);
        if(comment.getUser().getUserIdx() != userIdx)
            throw new UnauthorizedUserException();
        commentRepository.delete(comment);
    }

    private CommentDTO convertCommnet2DTO(UpdateComment comment) throws TumblbugException {
        CommentDTO dto = new CommentDTO();
        dto.setComment(comment.getComment());
        dto.setId(comment.getId());
        dto.setUser(userService.findSendingUserByIndex(comment.getUser().getUserIdx()));
        dto.setModifiedTime(comment.getModifiedTime());

        return dto;
    }
}
