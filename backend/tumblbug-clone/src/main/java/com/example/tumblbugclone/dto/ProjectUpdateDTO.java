package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectUpdateDTO {

    long id;
    String content;
    UserSendingDTO creater;
    long projectId;
    Date updateDate;
    boolean modified = false;
    //댓글
}
