package com.shopping.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Data

@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private int price;
    @Column(length = 500)
    private String description;
    private String image; //이미지 경로
    

    @JsonProperty("productId")  // Getter 위에 붙임
    public Long getProductId() {
        return productId;
    }
}
