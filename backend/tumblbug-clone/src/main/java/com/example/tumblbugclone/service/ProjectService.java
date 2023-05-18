package com.example.tumblbugclone.service;

import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.dto.ProjectDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

import static com.example.tumblbugclone.service.Callendar.convertDate;
import static com.example.tumblbugclone.service.Callendar.convertString;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProductService productService, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.productService = productService;
        this.userRepository = userRepository;
    }
    private final ProductService productService;

    //임시 유저 저장 로직
    private final UserRepository userRepository;



    public long saveProject(ProjectAllDTO projectAllDTO) throws ParseException {
        Project project = new Project();
        ProjectDTO projectAtDTO = projectAllDTO.getProject();
        project.setUser(saveUser());
        makeProjectFromDTO(project, projectAtDTO);

        long projectId = projectRepository.save(project);

        List<ProductDTO> productList = projectAllDTO.getProduct();
        for(ProductDTO product : productList) {
            product.setProject(project);
            productService.saveProduct(product);
        }

        return projectId;
    }


    //유저 임시 저장 로직
    private User saveUser() {
        User user = new User();
        user.setUserId("id");
        user.setUserEmail("email");
        user.setUserName("name");
        user.setUserPassword("password");
        user.setGreeting("greeting");
        user.setUserImg("img");
        userRepository.save(user);
        return user;
    }

    public ProjectAllDTO readProject(long projectId) throws Exception {
        ProjectAllDTO projectAll = new ProjectAllDTO();
        ProjectDTO projectDTO = new ProjectDTO();

        Project project = new Project();
        project = projectRepository.findProjectById(projectId);
        makeDTOFromProject(project, projectDTO);

        List<ProductDTO> productDTO = productService.readProduct(projectId);

        projectAll.setProject(projectDTO);
        projectAll.setProduct(productDTO);
        //임시 삽입
        projectAll.setCreater(userRepository.findUserById("id"));

        return projectAll;
    }

    public long updateProject(Project project) throws ParseException {
        project.setUser(userRepository.findUserById("id"));
        return projectRepository.modify(project);
    }

    public void deleteProject(long projectId) throws Exception {
        productService.deleteProduct(projectId);
        projectRepository.delete(projectId);
    }

    private static void makeProjectFromDTO(Project project, ProjectDTO projectAtDTO) throws ParseException {
        project.setTitle(projectAtDTO.getTitle());
        project.setProjectImg(projectAtDTO.getProjectImg());
        project.setCategory(projectAtDTO.getCategory());
        project.setComment(projectAtDTO.getComment());
        project.setGoalMoney(projectAtDTO.getGoalMoney());
        project.setTotalMoney(projectAtDTO.getTotalMoney());
        project.setStartDate(convertDate(projectAtDTO.getStartDate()));
        project.setEndDate(convertDate(projectAtDTO.getEndDate()));
        project.setPlanIntro(projectAtDTO.getPlanIntro());
        project.setPlanBudget(projectAtDTO.getPlanBudget());
        project.setPlanSchedule(projectAtDTO.getPlanSchedule());
        project.setPlanTeam(projectAtDTO.getPlanTeam());
        project.setPlanExplain(projectAtDTO.getPlanExplain());
        project.setPlanGuide(projectAtDTO.getPlanGuide());
    }

    private static void makeDTOFromProject(Project project, ProjectDTO projectAtDTO) throws ParseException {
        projectAtDTO.setProjectId(project.getProjectId());
        projectAtDTO.setTitle(project.getTitle());
        projectAtDTO.setProjectImg(project.getProjectImg());
        projectAtDTO.setCategory(project.getCategory());
        projectAtDTO.setComment(project.getComment());
        projectAtDTO.setGoalMoney(project.getGoalMoney());
        projectAtDTO.setTotalMoney(project.getTotalMoney());
        projectAtDTO.setStartDate(convertString(project.getStartDate()));
        projectAtDTO.setEndDate(convertString(project.getEndDate()));
        projectAtDTO.setPlanIntro(project.getPlanIntro());
        projectAtDTO.setPlanBudget(project.getPlanBudget());
        projectAtDTO.setPlanSchedule(project.getPlanSchedule());
        projectAtDTO.setPlanTeam(project.getPlanTeam());
        projectAtDTO.setPlanExplain(project.getPlanExplain());
        projectAtDTO.setPlanGuide(project.getPlanGuide());


    }
}
