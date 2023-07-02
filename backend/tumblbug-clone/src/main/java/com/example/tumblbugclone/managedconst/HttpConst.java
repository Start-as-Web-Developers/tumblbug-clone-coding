package com.example.tumblbugclone.managedconst;

public class HttpConst {
    //==MAIN URL ==//
    public static final String USER_URI = "/user";
    public static final String USER_SIGNUP_URI = "/signup";
    public static final String USER_LOGIN_URI = "/login";
    public static final String USER_LOGOUT_URI = "/logout";

    public static final String PROJECT_URI = "/project";
    public static final String COMMUNITY_URI = "/project/{project-id}/community";
    public static final String PROJECT_LIST_URI = "/projects";
    public static final String PRODUCT_URI = "/project/{project-id}/product";
    public static final String COMPONENT_URI = "/product/{product-id}/component";

    public static final String UPDATE_URI = PROJECT_URI + "/{project-id}/update";
    public static final String UPDATE_ID = "/{update-id}";

    public static final String UPDATE_COMMENT_URI = UPDATE_URI + UPDATE_ID + "/comment";
    public static final String UPDATE_COMMENT_ID = "/{comment-id}";


    //== method URL ==//
    public static final String ON_GOING = "/ongoing";
    public static final String PRE_LAUNCHING = "/prelaunghing";

    //== 헤더 이름 ==//
    public static final String HEADER_NAME_ERROR_MESSAGE = "error-message";
    public static final String SESSION_COOKIE = "JSESSIONID";

    //== 헤더 값 ==//
    public static final String SESSION_USER_INDEX = "user-index";
    public static final String LOGIN_NECESSARY = "login please";
    public static final String USERNAME_IS_NULL = "User name is required.";
    public static final String USERID_IS_NULL = "User Id is required.";
    public static final String USERPASSWORD_IS_NULL = "User Password is required.";
    public static final String USEREMAIL_IS_NULL = "User E-mail is required.";
    public static final String DUPLICATED_USER_ID_MESSAGE = "User Id is duplicated";
    public static final String DUPLICATED_USER_EMAIL_MESSAGE = "User E-mail id already used";
    public static final String NO_USER_FIND_MESSAGE = "Can't find user index";
    public static final String CANT_MODIFY_USER_ID_MESSAGE = "Can't modify user id";
    public static final String UNREGISTER_USER_MESSAGE = "User account is unregisted";
}
