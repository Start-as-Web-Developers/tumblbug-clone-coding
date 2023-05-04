package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Component {

    private long componentId;
    private long productId;
    private long userIdx;
    private String componentName;
    private Integer amount;

    public Component(long productId, long userIdx, String componentName, Integer amount) {
        this.productId = productId;
        this.userIdx = userIdx;
        this.componentName = componentName;
        this.amount = amount;
    }
}
