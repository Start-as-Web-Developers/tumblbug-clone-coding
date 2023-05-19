package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LIKES")
@Data
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    long likeId;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    User user;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    Project project;

}
