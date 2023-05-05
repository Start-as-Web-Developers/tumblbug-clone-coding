package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Component {

    private long componentId;

    private long productIdx; // 상대값
    private long productId; // 절대값
    private long userIdx;
    private long projectId;

    private String name;
    private Integer amount;

    public Component(long productIdx, long productId, long userIdx, long projectId, String name, Integer amount) {
        this.productIdx = productIdx;
        this.productId = productId;
        this.userIdx = userIdx;
        this.projectId = projectId;
        this.name = name;
        this.amount = amount;
    }
}
