package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LIKES")
@NoArgsConstructor
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "ACTIVE")
    boolean active = true;

}
