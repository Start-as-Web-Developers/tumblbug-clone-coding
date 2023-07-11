package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProductDTO {

    private Long productId;
    private String name;
    private Integer price;
    private List<Component> component;
}
