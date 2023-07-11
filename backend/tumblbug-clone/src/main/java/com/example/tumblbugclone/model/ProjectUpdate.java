package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PROJECT_UPDATE")
@NoArgsConstructor
@Data
@ToString(exclude = {"project", "creater"})
public class ProjectUpdate {

    @Id
    @Column(name = "UPDATE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPDATE_ID")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User creater;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "IS_MODIFIED")
    private boolean modified = false;


    @Column(name = "comment")
    @OneToMany(mappedBy = "update")
    private List<UpdateComment> comment;

}
