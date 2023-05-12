package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProjectAllDTO {

    private ProjectDTO project;
    private List<ProductDTO> product;
    private User creater;
}
