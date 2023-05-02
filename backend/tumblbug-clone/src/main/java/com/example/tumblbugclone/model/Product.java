package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Product {

    private long projectId;
    private long userIdx;
    private long productId;

    public Product(long projectId, long userIdx) {
        this.projectId = projectId;
        this.userIdx = userIdx;
    }
}
