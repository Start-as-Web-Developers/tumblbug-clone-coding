package com.example.tumblbugclone.dto;

import lombok.Data;

@Data
public class ProjectCardDTO {
    //프로젝트 정보
    private Long projectId;
    private String title;
    private String projectImg;
    private String category;
    private String comment;
    //private String startDate;

    //판매자 정보
    private String createrName;
    private Long createrIdx;

    // 후원 현황
    private Long goalMoney;
    private Long totalMoney;
    private String endDate;
        //얘랑 goalMoney중 하나만 넣어도 될 것 같음
    //private Long achievement;
        //굳이 필요 없을 정보 같음
    //private Long sponsor;
        //이것도 사용 안됨
    //private Long totalLike;

    //user 쿠키
    private boolean like;
    //private boolean create;

}
