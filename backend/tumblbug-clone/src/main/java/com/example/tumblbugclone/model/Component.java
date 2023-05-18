package com.example.tumblbugclone.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMPONENT")
@NoArgsConstructor
@Data
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPONENT_ID")
    private long componentId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    /*
    product가 갖고 있는 정보
    ERD상에 있으나 생략 가능
    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;
     */

    /*
    product가 갖고 있는 정보
    ERD상에 있으나 생략 가능
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;
     */

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

}
