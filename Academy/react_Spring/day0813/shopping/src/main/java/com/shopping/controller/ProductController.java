package com.shopping.controller;

import com.shopping.entity.Product;
import com.shopping.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shopping")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);

            if (image != null && !image.isEmpty()) {
                // static/img 폴더 절대경로
                String uploadDir = new File("src/main/resources/static/img").getAbsolutePath();

                // UUID + 안전한 파일명
                String safeFileName = UUID.randomUUID().toString() + "_"
                        + image.getOriginalFilename().replaceAll("[^a-zA-Z0-9ㄱ-힣._-]", "_");

                // 파일 저장
                File file = new File(uploadDir, safeFileName);
                image.transferTo(file);

                // DB에는 접근 가능한 상대경로 저장
                product.setImage("/img/" + safeFileName);
            }

            productService.save(product);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 등록 실패");
        }
    }

    // 전체 상품 목록
    @GetMapping
    public ResponseEntity<ProductPageResponse> getProducts(@PageableDefault(size = 10) Pageable pageable) {
        Page<Product> page = productService.findAll(pageable);
        return ResponseEntity.ok(new ProductPageResponse(page.getContent(), page.getTotalPages()));
    }

    // 상품 상세
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ProductPageResponse> searchProducts(
            @RequestParam("query") String query,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<Product> page = productService.searchByName(query, pageable);
        return ResponseEntity.ok(new ProductPageResponse(page.getContent(), page.getTotalPages()));
    }

    // DTO
    @Data
    @AllArgsConstructor
    static class ProductPageResponse {
        private List<Product> content;
        private int totalPages;
    }
}
