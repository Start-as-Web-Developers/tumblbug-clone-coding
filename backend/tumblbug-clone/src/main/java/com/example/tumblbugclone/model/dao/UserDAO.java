package com.example.tumblbugclone.model.dao;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.Date;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Data
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_IDX")
    private Long userIdx;

    //@NotNull
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    //@NotNull
    @Column(name = "USER_ID",length = 16, unique = true, nullable = false)
    private String userId;

    //@NotNull
    @Column(name = "USER_PASSWORD", length = 16,nullable = false)
    private String userPassword;

    //@NotNull
    @Column(name = "USER_EMAIL", length = 32, unique = true,nullable = false)
    private String userEmail;

    @Column(name = "GREETING")
    private String greeting;

    @Column(name = "USER_IMG")
    private String userImg;

    @Column(name = "LAST_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;


    //좋아요 - 1대 다 매핑
    //후원 - 1대 다 매핑


}
