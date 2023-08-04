package com.example.tumblbugclone.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDTO {
    String userId;
    String userPassword;
}
