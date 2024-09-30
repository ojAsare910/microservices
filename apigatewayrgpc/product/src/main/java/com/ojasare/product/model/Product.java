package com.ojasare.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Column(unique = true)
    private String name;

    @NotNull(message = "Product description cannot be null")
    @Size(min = 5, max = 500, message = "Product description must be between 5 and 500 characters")
    private String description;

    @NotNull(message = "Product price cannot be null")
    private Double price;
}
