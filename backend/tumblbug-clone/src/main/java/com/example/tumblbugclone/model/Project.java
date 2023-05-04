package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Project {

    private Long userIdx;

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

    public Project(Long userIdx, String title, String projectImg, String category, String comment, Long goalMoney, Long totalMoney, String startDate, String endDate, String planIntro, String planBudget, String planSchedule, String planTeam, String planExplain, String planGuide) {
        this.userIdx = userIdx;

        this.title = title;
        this.projectImg = projectImg;
        this.category = category;
        this.comment = comment;

        this.goalMoney = goalMoney;
        this.totalMoney = totalMoney;

        this.startDate = startDate;
        this.endDate = endDate;

        this.planIntro = planIntro;
        this.planBudget = planBudget;
        this.planSchedule = planSchedule;
        this.planTeam = planTeam;
        this.planExplain = planExplain;
        this.planGuide = planGuide;
    }
}
