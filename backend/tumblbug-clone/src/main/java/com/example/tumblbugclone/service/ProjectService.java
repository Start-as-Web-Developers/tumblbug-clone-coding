package com.example.tumblbugclone.service;

import com.example.tumblbugclone.Exception.TumblbugException;
import com.example.tumblbugclone.Exception.projectException.ProjectCantFindException;
import com.example.tumblbugclone.Exception.projectException.ProjectCantModify;
import com.example.tumblbugclone.Exception.userexception.UserCantFindException;
import com.example.tumblbugclone.dto.PlanDTO;
import com.example.tumblbugclone.dto.ProjectAllDTO;
import com.example.tumblbugclone.dto.ProductDTO;
import com.example.tumblbugclone.dto.ProjectDTO;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import com.example.tumblbugclone.repository.ProjectRepository;
import com.example.tumblbugclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

import static com.example.tumblbugclone.service.Callendar.convertDate;
import static com.example.tumblbugclone.service.Callendar.convertString;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProductService productService, UserRepository userRepository, LikeService likeService, UserService userService) {
        this.projectRepository = projectRepository;
        this.productService = productService;
        this.likeService = likeService;
        this.userService = userService;
    }
    private final ProductService productService;
    private final LikeService likeService;
    private final UserService userService;




    public long saveProject(ProjectAllDTO projectAllDTO, long userIndex) throws ParseException, TumblbugException {
        Project project = new Project();
        ProjectDTO projectAtDTO = projectAllDTO.getProject();

        makeProjectFromDTO(project, projectAtDTO);

        User user = userService.findUserByIndex(userIndex);
        project.setUser(user);

        long projectId = projectRepository.save(project);

        List<ProductDTO> productList = projectAllDTO.getProduct();
        for(ProductDTO product : productList) {
            productService.saveProduct(product, project.getProjectId(), userIndex);
        }

        return projectId;
    }

    public ProjectAllDTO readProject(long projectId, long userIndex) throws Exception {
        ProjectAllDTO projectAll = new ProjectAllDTO();
        ProjectDTO projectDTO = new ProjectDTO();

        Project project = new Project();
        project = projectRepository.findProjectById(projectId);
        makeDTOFromProject(project, projectDTO);

        projectDTO.setTotalLike((long) likeService.countProjectLike(projectId));
        projectDTO.setLike(likeService.isLike(userIndex, projectId));

        if(userIndex == project.getUser().getUserIdx())
            projectDTO.setCreate(true);
        else
            projectDTO.setCreate(false);

        // 현재 후원 정보가 없어 구현은 불가하므로 임시 삽입
        projectDTO.setAchievement(50L);
        projectDTO.setSponsor(50L);

        List<ProductDTO> productDTO = productService.readProduct(projectId);

        projectAll.setProject(projectDTO);
        projectAll.setProduct(productDTO);

        User creater = project.getUser();
        projectAll.setCreater(userService.convertUser2DTO(creater));

        return projectAll;
    }

    public PlanDTO readProjectPlan(long projectId) {
        Project project = projectRepository.findProjectById(projectId);

        PlanDTO planDTO = new PlanDTO();

        planDTO.setPlanBudget(project.getPlanBudget());
        planDTO.setPlanExplain(project.getPlanExplain());
        planDTO.setPlanSchedule(project.getPlanSchedule());
        planDTO.setPlanTeam(project.getPlanTeam());
        planDTO.setPlanIntro(project.getPlanIntro());
        planDTO.setPlanGuide(project.getPlanGuide());

        return planDTO;
    }

    public long updateProject(Project project, long userIndex) throws ParseException, TumblbugException {
        Project targetProject = projectRepository.findProjectById(project.getProjectId());
        if(targetProject.getUser().getUserIdx() != userIndex)
            throw new ProjectCantModify();

        User user = userService.findUserByIndex(userIndex);
        project.setUser(user);
        
        return projectRepository.modify(project);
    }

    public void deleteProject(long projectId, long userIndex) throws Exception {
        Project targetProject = projectRepository.findProjectById(projectId);
        if(targetProject.getUser().getUserIdx() != userIndex)
            throw new ProjectCantModify();
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

    public Project findProjectById(long projectId) throws TumblbugException, ProjectCantFindException {
        Project project;
        try {
            project = projectRepository.findProjectById(projectId);
        } catch (EmptyResultDataAccessException e) {
            throw new ProjectCantFindException();
        }
        return project;
    }
}
