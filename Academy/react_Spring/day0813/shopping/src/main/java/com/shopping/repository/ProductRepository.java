package com.shopping.repository;

import com.shopping.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 이름에 특정 단어가 포함된 상품 검색 (대소문자 무시)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name);


}
