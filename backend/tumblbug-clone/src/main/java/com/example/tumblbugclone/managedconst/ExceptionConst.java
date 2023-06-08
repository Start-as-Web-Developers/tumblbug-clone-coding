package com.example.tumblbugclone.managedconst;

public class ExceptionConst { //Tumblbug 내부에 작성하는 것이 좋았을지 고민중
    //== User exception const==//
    public static final int UserCantFindStatus = 411;
    public static final int UserCantModifyIdStatus = 412;
    public static final int UserDTOConvertStatus = 413;
    public static final int UserEmailDuplicatedStatus = 414;
    public static final int UserIdDuplicatedStatus = 415;
    public static final int WrongPasswordStatus = 416;
    public static final int LoginRequiredStatus = 417;

    //== Project card const ==//
    public static final int StartIndexStatus = 421;

    //== update exception const==//
    public static final int UpdateCantModifyId = 631;
    public static final int UpdateCantModifyModifiedToFalse = 632;
    public static final int CantFindUpdate = 633;

    public static final int CommunityCantModify = 644;
}
