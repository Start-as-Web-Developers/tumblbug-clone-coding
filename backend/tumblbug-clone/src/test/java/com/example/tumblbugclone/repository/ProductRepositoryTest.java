package com.example.tumblbugclone.repository;


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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
public class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;
    @Autowired ProjectRepository projectRepository;
    @Autowired UserRepository userRepository;

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
        project.setStartDate(new Date());
        project.setEndDate(new Date());
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

        productRepository.save(product);

    }

    @Test
    @Transactional
    public void findProductById() throws Exception{
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
        project.setStartDate(new Date());
        project.setEndDate(new Date());
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

        //when
        long productId = productRepository.save(product);
        Product savedProduct = productRepository.findProductById(productId);

        //then
        Assertions.assertThat(productId).isEqualTo(savedProduct.getProductId());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void findProductById_EX() throws Exception {
        //given

        //when
        Product findProduct = productRepository.findProductById(1L);

    }

    @Test
    @Transactional
    public void findProductByProjectId() {
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
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        Product product1 = new Product();
        product1.setProject(project);
        product1.setName("name1");
        product1.setPrice(100);

        Product product2 = new Product();
        product2.setProject(project);
        product2.setName("name2");
        product2.setPrice(200);

        long projectId = projectRepository.save(project);
        userRepository.save(user);
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> savedProductList = new ArrayList<>();
        savedProductList.add(product1);
        savedProductList.add(product2);

        //when
        List<Product> productList = productRepository.findProductByProjectId(projectId);

        //then
        Assertions.assertThat(savedProductList).isEqualTo(productList);
    }

    @Test
    @Transactional
    public void findProductByProjectId_EX() {
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
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setPlanIntro("planIntro");
        project.setPlanBudget("planBudget");
        project.setPlanSchedule("planSchedule");
        project.setPlanTeam("planTeam");
        project.setPlanExplain("planExplain");
        project.setPlanGuide("planGuide");

        Product product1 = new Product();
        product1.setProject(project);
        product1.setName("name1");
        product1.setPrice(100);

        Product product2 = new Product();
        product2.setProject(project);
        product2.setName("name2");
        product2.setPrice(200);

        long projectId = projectRepository.save(project);
        userRepository.save(user);
        productRepository.save(product1);

        List<Product> savedProductList = new ArrayList<>();
        savedProductList.add(product1);
        savedProductList.add(product2);

        //when
        List<Product> productList = productRepository.findProductByProjectId(projectId);

        //then
        Assertions.assertThat(savedProductList).isNotEqualTo(productList);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    public void delete() {

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
        project.setStartDate(new Date());
        project.setEndDate(new Date());
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

        //when
        long productId = productRepository.save(product);

        //then
        productRepository.delete(productId);
        productRepository.findProductById(productId);
    }

}
