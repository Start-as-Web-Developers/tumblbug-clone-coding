package com.example.tumblbugclone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@NoArgsConstructor
@Data
public class UserDTO {

    private Long userIdx;
    private String userName;
    private String userId;
    private String userPassword;
    private String userEmail;
    private String greeting;
    private String userImg;
    private Date lastLogin;
    private boolean isActive;


    //좋아요 - 1대 다 매핑
    //후원 - 1대 다 매핑

}

