package com.example.tumblbugclone.model;

public class Project {
    Long projectId; //프로젝트 id (DB 식별자)
    //User user; //사용자 객체 (이름, 아이디,,,,) or 유저 idx
    String title; //프로젝트 제목 (ERD 수정 필요)
    String projectImg; //프로젝트 이미지 URL
    String category; //enum 타입 이용하는 것도 좋을듯
    String comment; //프로젝트 한줄 소개
    int goalMoney; //목표 금액
    int totalMoney; //누적 금액

    //날짜 (날짜 API or String)
    String startDate;
    String endDate;

    //계획 (이것도 하나의 클래스로 만드는 것이 나을수도?)
    String planIntro;
    String planBudget;
    String planSchedule;
    String planTeam;
    String planExplain;
    String planGuide;
}
