package com.meretskiy.cloud.project.product.service.services;

import com.meretskiy.cloud.project.product.service.model.Product;
import com.meretskiy.cloud.project.product.service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
