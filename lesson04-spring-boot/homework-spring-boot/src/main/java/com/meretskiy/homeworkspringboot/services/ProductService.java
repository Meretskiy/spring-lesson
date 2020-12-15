package com.meretskiy.homeworkspringboot.services;

import com.meretskiy.homeworkspringboot.model.Product;
import com.meretskiy.homeworkspringboot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    public List<Product> findAllProduct(Integer minCost, Integer maxCost) {
        List<Product> out = findAllProduct();
        if (minCost != null) {
            out = out.stream().filter(s -> s.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(s -> s.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
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
