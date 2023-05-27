package com.example.tumblbugclone.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.util.Date;


@NoArgsConstructor
@Data
public class UserSendingDTO {

    private Long userIdx;
    private String userName;
    private String userId;
    private String userEmail;
    private String greeting;
    private String userImg;
    private Date lastLogin;
    private boolean isActive;

    //좋아요 - 1대 다 매핑
    //후원 - 1대 다 매핑

    @Override
    public boolean equals(Object o){
        if(!(o instanceof UserSendingDTO)) {
            return false;
        }

         if(!this.userIdx.equals(((UserSendingDTO) o).userIdx))
             return false;

         if(!this.userName.equals(((UserSendingDTO) o).getUserName()))
             return false;

         if(!this.userId.equals(((UserSendingDTO) o).getUserId()))
             return false;

        if(!this.userIdx.equals(((UserSendingDTO) o).getUserIdx()))
            return false;

        if(!this.userEmail.equals(((UserSendingDTO) o).getUserEmail()))
            return false;

        if((this.getGreeting() != null) == (((UserSendingDTO) o).getGreeting() != null)) {
            if (this.getGreeting() != null && !this.greeting.equals(((UserSendingDTO) o).getGreeting()))
                return false;
        }


        if((this.getUserImg() != null) ==  (((UserSendingDTO) o).getUserImg() != null))
            if(this.getUserImg() != null && !this.userImg.equals(((UserSendingDTO) o).getUserImg()))
                return false;

        if((this.getLastLogin() != null) == (((UserSendingDTO) o).getLastLogin() != null))
            if(this.getLastLogin() != null && !this.lastLogin.equals(((UserSendingDTO) o).getLastLogin())) {
                return false;
            }


        if(this.isActive != ((UserSendingDTO)o).isActive())
            return false;

        return true;
    }
}

