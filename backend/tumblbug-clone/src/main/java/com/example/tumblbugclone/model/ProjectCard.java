package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCard {

    private Long projectId;
    private String title;
    private String projectImg;
    private String category;
    private String createrName;
    private Long createrIdx;
    private String comment;

    private int achivement;
    private Long totalMoney;

    private String startDate;
    private String endDate;

    private int totalLike;
    private boolean like;

    public ProjectCard(Project project, User user){
        this.projectId = project.getProjectId();
        //this.title = project.getProjectTitle();
        this.projectImg = project.getProjectImg();
        this.category = project.getCategory();
        this.createrName = user.getUserName();
        this.createrIdx = user.getUserIdx();
        this.comment = project.getComment();

        this.totalMoney = project.getTotalMoney();
        Long achivementLong = (project.getGoalMoney() * 100) / this.totalMoney;
        this.achivement = achivementLong.intValue();

        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();

        //totalLike 구현
        //Like 구현
    }
}
