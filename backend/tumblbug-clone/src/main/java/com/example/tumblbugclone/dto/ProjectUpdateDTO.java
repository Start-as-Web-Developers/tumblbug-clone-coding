package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectUpdateDTO {

    long id;
    String content;
    UserSendingDTO creater;
    long projectId;
    LocalDate updateDate;
    boolean modified = false;
    //댓글
}
