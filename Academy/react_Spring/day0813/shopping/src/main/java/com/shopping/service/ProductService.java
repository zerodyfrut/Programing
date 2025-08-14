package com.shopping.service;

import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 저장
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // 전체 상품 조회
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // ID로 상품 조회
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
    }

    // 상품 삭제
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> searchByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

}
