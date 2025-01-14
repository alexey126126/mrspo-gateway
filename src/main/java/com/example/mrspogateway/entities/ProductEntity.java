package com.example.mrspogateway.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "products")
public class ProductEntity extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
}
