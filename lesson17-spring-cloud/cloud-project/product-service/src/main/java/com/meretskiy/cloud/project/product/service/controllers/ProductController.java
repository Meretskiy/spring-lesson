package com.meretskiy.cloud.project.product.service.controllers;

import com.meretskiy.cloud.project.product.service.model.Product;
import com.meretskiy.cloud.project.product.service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*") //позволяем с любых серверов посылать нам запросы и получать на это ответы
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }
}
