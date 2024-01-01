package com.example.ProducRegistrationCRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "Products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    private Long id;
    private String name;
    private int quantity;
    private float value;

}
