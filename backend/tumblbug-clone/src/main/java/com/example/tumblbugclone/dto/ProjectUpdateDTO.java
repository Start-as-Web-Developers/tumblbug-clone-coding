package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ProjectUpdateDTO {

    private long id;
    private String content;
    private UserSendingDTO creater;
    private long projectId;
    private Date updateDate;
    private boolean modified = false;
    private List<CommentDTO> comments;
}
