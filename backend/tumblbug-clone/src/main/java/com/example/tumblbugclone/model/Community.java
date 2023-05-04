package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Community {
    private long communityId;
    private long projectId;
    private long userIdx;
    private String comment;
    private String writeDate;
    private String modiDate;

    public Community(long projectId, long userIdx, String comment, String writeDate, String modiDate) {
        this.projectId = projectId;
        this.userIdx = userIdx;
        this.comment = comment;
        this.writeDate = writeDate;
        this.modiDate = modiDate;
    }
}
