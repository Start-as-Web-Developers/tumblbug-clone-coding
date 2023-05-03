package com.example.tumblbugclone.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Product {

    long projectId;
    long userIdx;

    public Product(long projectId, long userIdx) {
        this.projectId = projectId;
        this.userIdx = userIdx;
    }
}
