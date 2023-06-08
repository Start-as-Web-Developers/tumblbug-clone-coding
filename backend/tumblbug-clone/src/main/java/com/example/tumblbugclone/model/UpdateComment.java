package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "UPDATE_COMMNENT")
@Data
@NoArgsConstructor
public class UpdateComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "UPDATE_ID")
    ProjectUpdate update;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    User user;

    @Column(name = "COMMENT")
    String comment;
}
