package com.soulprincess.backend;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 100)
    private String category;

    @Column(nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal price;

    @Column(columnDefinition = "int default 0")
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String image;
}