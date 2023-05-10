package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "COMMUNITY")
@NoArgsConstructor
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMUNIY_ID")
    private long communityId;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "WRITE_DATE", nullable = false)
    private Date writeDate;

    @Column(name = "MODI_DATE")
    private Date modiDate;

}
