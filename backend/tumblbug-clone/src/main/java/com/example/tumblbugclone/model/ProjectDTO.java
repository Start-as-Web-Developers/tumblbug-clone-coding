package com.example.tumblbugclone.model;

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
