package com.example.tumblbugclone.dto;

import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProjectDTO {

    private Project projects;
    private List<Product> products;
    private List<Component> components;
    private User creater;

    public ProjectDTO(Project projects, List<Product> products, List<Component> components, User creater) {
        this.projects = projects;
        this.products = products;
        this.components = components;
        this.creater = creater;
    }
}
