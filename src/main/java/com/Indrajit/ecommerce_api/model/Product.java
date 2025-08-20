package com.Indrajit.ecommerce_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    private Integer StockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
