package com.example.tumblbugclone.managedconst;

public class ExceptionConst { //Tumblbug 내부에 작성하는 것이 좋았을지 고민중
    //== User exception const==//
    public static final int UserCantFindStatus = 611;
    public static final int UserCantModifyIdStatus = 612;
    public static final int UserDTOConvertStatus = 613;
    public static final int UserEmailDuplicatedStatus = 614;
    public static final int UserIdDuplicatedStatus = 615;
    public static final int WrongPasswordStatus = 616;
    public static final int LoginRequiredStatus = 617;
    public static final int UnauthorizedUser = 618;

    //== Project card const ==//
    public static final int StartIndexStatus = 621;

    //== update exception const==//
    public static final int UpdateCantModifyId = 631;
    public static final int UpdateCantModifyModifiedToFalse = 632;
    public static final int CantFindUpdate = 633;
    public static final int NotMatchProjectId = 634;

    //== community exception const==//
    public static final int CommunityCantModify = 644;

    //== project exception const==//
    public static final int ProjectCantModify = 651;
    public static final int ProjectCantFind = 652;

    public static final int ProjectCantModify = 650;
    public static final int ProjectSortException = 651;
    public static final int ComponentCantModify = 652;

}
