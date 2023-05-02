package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Project {

    Long userIdx;

    Long projectId;
    String projectImg;
    String category;
    String comment;

    Long goalMoney;
    Long totalMoney;

    String startDate;
    String endDate;

    String planIntro;
    String planBudget;
    String PlanSchedule;
    String planTeam;
    String planExplain;
    String planGuide;

    public Project(Long userIdx, String projectImg, String category, String comment, Long goalMoney, Long totalMoney, String startDate, String endDate, String planIntro, String planBudget, String planSchedule, String planTeam, String planExplain, String planGuide) {
        this.userIdx = userIdx;

        this.projectImg = projectImg;
        this.category = category;
        this.comment = comment;

        this.goalMoney = goalMoney;
        this.totalMoney = totalMoney;

        this.startDate = startDate;
        this.endDate = endDate;

        this.planIntro = planIntro;
        this.planBudget = planBudget;
        PlanSchedule = planSchedule;
        this.planTeam = planTeam;
        this.planExplain = planExplain;
        this.planGuide = planGuide;
    }
}
