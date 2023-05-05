package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.projectException.ProjectCantFindException;
import com.example.tumblbugclone.Exception.userexception.UnregisterUserException;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.ProjectDTO;
import com.example.tumblbugclone.model.*;
import com.example.tumblbugclone.repository.ComponentRepository;
import com.example.tumblbugclone.repository.ProductRepository;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class projectService {

    static ProjectRepository projectRepository = ProjectRepository.getProjectRepository();
    static ComponentRepository componentRepository = ComponentRepository.getComponentRepository();
    static ProductRepository productRepository = ProductRepository.getProductRepository();
    static UserRepository userRepository = UserRepository.getUserRepository();

    public static void save(Project project, List<Product> products, List<Component> components) throws Exception {

        long productIdx;
        long productId;

        Long projectId = projectRepository.save(project);

        for(Product product : products) {
            product.setProjectId(projectId);
            productIdx = product.getProductIdx();
            productId = productRepository.save(product);
            for(Component component: components) {

                if(component.getProductIdx() == productIdx) {
                    component.setProductId(productId);
                    component.setProjectId(projectId);
                    componentRepository.save(component);

                }
            }

        }

        return;

    }

    public static ProjectDTO make(long projectId) throws ProjectCantFindException, UserCantFindException, UnregisterUserException {
        Project project = projectRepository.findProjectById(projectId);
        List<Product> products = productRepository.findProductByProjectId(projectId);
        List<Component> components = componentRepository.findComponentByProjectId(projectId);
        User user = userRepository.findUserByIdx(project.getUserIdx());

        ProjectDTO projects = new ProjectDTO(project, products, components, user);
        return projects;
    }

    public static void delete(long projectId) throws ProjectCantFindException {
        projectRepository.deleteProject(projectId);
        productRepository.deleteProductById(projectId);
        componentRepository.deleteComponentById(projectId);
    }

}
