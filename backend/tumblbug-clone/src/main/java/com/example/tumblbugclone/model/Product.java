package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Product {
    private long productId;

    private long projectId;
    private long userIdx;
    private long productIdx; // 프로젝트 안 상대키

    private String name;
    private Integer price;

    public Product(long projectId, long userIdx, long productIdx, String name, Integer price) {
        this.projectId = projectId;
        this.userIdx = userIdx;
        this.productIdx = productIdx;
        this.name = name;
        this.price = price;
    }
}
