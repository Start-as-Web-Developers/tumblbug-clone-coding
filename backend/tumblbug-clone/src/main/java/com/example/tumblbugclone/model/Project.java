package com.example.tumblbugclone.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "PROJECT")
@NoArgsConstructor
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_ID")
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @Column(name = "TITLE", length = 100, nullable = false)
    private String title;

    @Column(name = "PROJECT_IMG", nullable = false)
    private String projectImg;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "COMMENT", length = 256, nullable = false)
    private String comment;

    @Column(name = "GOAL_MONEY", nullable = false)
    private Long goalMoney;

    @Column(name = "TOTAL_MONEY")
    private Long totalMoney;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "PLAN_INTRO", nullable = false)
    private String planIntro;

    @Column(name = "PLAN_BUDGET", nullable = false)
    private String planBudget;

    @Column(name = "PLAN_SCHEDULE", nullable = false)
    private String planSchedule;

    @Column(name = "PLAN_TEAM", nullable = false)
    private String planTeam;

    @Column(name = "PLAN_EXPLAIN", nullable = false)
    private String planExplain;

    @Column(name = "PLAN_GUIDE", nullable = false)
    private String planGuide;


}
