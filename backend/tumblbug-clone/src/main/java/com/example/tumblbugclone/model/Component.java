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

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

}
