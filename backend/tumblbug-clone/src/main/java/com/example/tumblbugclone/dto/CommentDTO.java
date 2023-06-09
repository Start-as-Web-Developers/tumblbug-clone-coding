package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.ProjectUpdate;
import com.example.tumblbugclone.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDTO {

    private long id;
    private UserSendingDTO user;
    private String comment;
    private Date modifiedTime;
}
