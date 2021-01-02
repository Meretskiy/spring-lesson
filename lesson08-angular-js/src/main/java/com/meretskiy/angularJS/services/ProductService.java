package com.meretskiy.angularJS.services;

import com.meretskiy.angularJS.model.Product;
import com.meretskiy.angularJS.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByPrise(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    public  Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }
}
