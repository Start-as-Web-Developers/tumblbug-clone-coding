package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class ProjectDTO {

    private Long projectId;
    private String title;
    private String projectImg;
    private String category;
    private String comment;
    private Long goalMoney;
    private Long totalMoney;
    private String startDate;
    private String endDate;
    private String planIntro;
    private String planBudget;
    private String planSchedule;
    private String planTeam;
    private String planExplain;
    private String planGuide;

    // 후원 기능의 부재로 구현 불가
    private Long achievement;
    private Long sponsor;

    //user 쿠키
    private Long totalLike;
    private boolean like;
    private boolean create;


}
