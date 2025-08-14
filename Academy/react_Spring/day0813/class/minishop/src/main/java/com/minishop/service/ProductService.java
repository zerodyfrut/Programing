package com.minishop.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minishop.dao.ProductRepository;
import com.minishop.entity.Product;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public Product saveProduct(Product product, MultipartFile image) throws IOException {
        // 이미지 업로드
        String uploadDir = new ClassPathResource("static/img").getFile().getAbsolutePath();
        
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, image.getBytes());

        product.setImageUrl("/img/" + fileName);

        return repository.save(product);
    }

    public Page<Product> getProducts(Pageable pageable) {
        Page<Product> p = repository.findAll(pageable);

        System.out.println("Fetched " + p.getContent().size() + " products for page " + pageable.getPageNumber());
         
        return p;
    }

    public Optional<Product> getProduct(Long id) {
        return repository.findById(id);
    }
}
