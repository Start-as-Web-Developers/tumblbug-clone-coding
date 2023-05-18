package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private long productId;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    /* project가 user를 갖고 있음
    ERD 상에 있으나 제외 가능
    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;
    */


    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Integer price;


}
