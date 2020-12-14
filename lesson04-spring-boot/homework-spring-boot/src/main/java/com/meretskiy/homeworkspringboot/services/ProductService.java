package com.meretskiy.homeworkspringboot.services;

import com.meretskiy.homeworkspringboot.model.Product;
import com.meretskiy.homeworkspringboot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
}
