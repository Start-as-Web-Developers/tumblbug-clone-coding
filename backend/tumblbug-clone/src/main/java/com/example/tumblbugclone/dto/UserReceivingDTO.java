package com.example.tumblbugclone.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.Date;


@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "userPassword")
public class UserReceivingDTO extends UserSendingDTO {


    private String userPassword;

    //좋아요 - 1대 다 매핑
    //후원 - 1대 다 매핑

    @Override
    public boolean equals(Object o){
        if(!(o instanceof UserSendingDTO))
            return false;
        if(!(o instanceof UserReceivingDTO)){
            return super.equals(o);
        }
        return super.equals(o) && this.userPassword.equals(((UserReceivingDTO) o).userPassword);
    }

}

