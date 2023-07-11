package com.example.tumblbugclone.repository;

import com.example.tumblbugclone.model.Component;
import com.example.tumblbugclone.model.Product;
import com.example.tumblbugclone.model.Project;
import com.example.tumblbugclone.model.User;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ComponentRepositoryTest {

    @Autowired ProductRepository productRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired UserRepository userRepository;
    @Autowired ComponentRepository componentRepository;

    @Test
    @Transactional
    public void save() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        Product product = new Product();
        product.setProject(project);
        product.setName("name");
        product.setPrice(100);

        Component component = new Component();
        component.setProduct(product);
        component.setName("name");
        component.setAmount(1);


        //when
        componentRepository.save(component);
    }

    @Test
    @Transactional
    public void findComponentById() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        Product product = new Product();
        product.setProject(project);
        product.setName("name");
        product.setPrice(100);

        Component component = new Component();
        component.setProduct(product);
        component.setName("name");
        component.setAmount(1);


        //when
        long componentId = componentRepository.save(component);
        Component savedComponent = componentRepository.findComponentById(componentId);

        //then
        Assertions.assertThat(componentId).isEqualTo(savedComponent.getComponentId());

    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void findComponentById_EX() throws Exception {
        //given

        //when
        Component findComponent = componentRepository.findComponentById(1L);

    }

    @Test
    @Transactional
    public void findComponentByProductId() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");
        userRepository.save(user);

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");
        projectRepository.save(project);

        Product product = new Product();
        product.setProject(project);
        product.setName("name");
        product.setPrice(100);
        long productId = productRepository.save(product);

        Component component1 = new Component();
        component1.setProduct(product);
        component1.setName("name1");
        component1.setAmount(1);

        Component component2 = new Component();
        component2.setProduct(product);
        component2.setName("name2");
        component2.setAmount(2);

        componentRepository.save(component1);
        componentRepository.save(component2);

        List<Component> savedComponentList = new ArrayList<>();
        savedComponentList.add(component1);
        savedComponentList.add(component2);

        //When
        List<Component> componentList = componentRepository.findComponentByProductId(productId);

        //then
        Assertions.assertThat(savedComponentList).isEqualTo(componentList);

    }

    @Test
    @Transactional
    public void findComponentByProductId_EX() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");
        userRepository.save(user);

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");
        projectRepository.save(project);

        Product product = new Product();
        product.setProject(project);
        product.setName("name");
        product.setPrice(100);
        long productId = productRepository.save(product);

        Component component1 = new Component();
        component1.setProduct(product);
        component1.setName("name1");
        component1.setAmount(1);

        Component component2 = new Component();
        component2.setProduct(product);
        component2.setName("name2");
        component2.setAmount(2);

        componentRepository.save(component1);

        List<Component> savedComponentList = new ArrayList<>();
        savedComponentList.add(component1);
        savedComponentList.add(component2);

        //When
        List<Component> componentList = componentRepository.findComponentByProductId(productId);

        //then
        Assertions.assertThat(savedComponentList).isNotEqualTo(componentList);

    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void delete() throws Exception {

        //given
        User user = new User();
        user.setUserId("UserId");
        user.setUserName("userName");
        user.setUserEmail("userEmail");
        user.setUserPassword("userPassword");

        Project project = new Project();
        project.setUser(user);
        project.setTitle("title");
        project.setProjectImg("img");
        project.setCategory("category");
        project.setComment("comment");
        project.setGoalMoney(1000L);
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        Product product = new Product();
        product.setProject(project);
        product.setName("name");
        product.setPrice(100);

        Component component = new Component();
        component.setProduct(product);
        component.setName("name");
        component.setAmount(1);


        //when
        long componentId = componentRepository.save(component);

        //then
        componentRepository.delete(componentId);
        componentRepository.findComponentById(componentId);

    }
}
