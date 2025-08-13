package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository dao;

    public Page<Product> findAll(int page){
        Pageable paging= PageRequest.of(page, 3);
        return dao.findAll(paging);
    }

    public List<Product> findByName(String name){
        return dao.findByName(name);

    }

    public void deleteById(Long productId) {
        dao.deleteById(productId);
    }
    
}
