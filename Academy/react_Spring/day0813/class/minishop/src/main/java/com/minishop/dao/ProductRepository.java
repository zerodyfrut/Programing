package com.minishop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minishop.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
