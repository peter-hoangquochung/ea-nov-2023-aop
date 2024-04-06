package com.example.Spring_Aop.controller;


import com.example.Spring_Aop.component.ExecutionTime;
import com.example.Spring_Aop.entity.Product;
import com.example.Spring_Aop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@AllArgsConstructor

@RequestMapping("/api/v1/products")

public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ExecutionTime
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/postData")
    public ResponseEntity<String> postData() {
        productService.getAll();
        return ResponseEntity.ok("Data received successfully");
    }
}
