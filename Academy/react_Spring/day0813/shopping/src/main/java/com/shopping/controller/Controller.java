package com.shopping.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;
import com.shopping.service.ProductService;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/shopping")
@RequiredArgsConstructor // 쓰는 이유는 모르겠음..
public class Controller {

    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // 미디어의 파일 어쩌구 무슨의미?
    public ResponseEntity<Product> saveproduct(
            @RequestParam String name,
            @RequestParam int price,
            @RequestParam String description,
            @RequestParam MultipartFile image) throws IOException {

        String uploadDir = new ClassPathResource("static/img").getFile().getAbsolutePath();
        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, image.getBytes());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage("/img/" + fileName);

        Product saved = repository.save(product);
        return ResponseEntity.ok(saved);

    }

    @GetMapping
    public Page<Product> getAllProduct(@RequestParam(defaultValue = "0") int page) {
        return service.findAll(page);
    }

    @DeleteMapping("/{productId}")
    public int deleteProduct(@PathVariable Long productId) {

        service.deleteById(productId);
        return 1;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));
        return ResponseEntity.ok(product);
    }

}
