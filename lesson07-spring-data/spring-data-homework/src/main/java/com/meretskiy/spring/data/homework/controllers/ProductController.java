package com.meretskiy.spring.data.homework.controllers;

import com.meretskiy.spring.data.homework.model.Product;
import com.meretskiy.spring.data.homework.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "min_cost", required = false) Integer minCost,
                                        @RequestParam(name = "max_cost", required = false) Integer maxCost) {
        if (minCost == null && maxCost == null) {
            return (List<Product>) productRepository.findAll();
        } else if (minCost == null) {
            return productRepository.findAllByCostIsLessThan(maxCost);
        } else if (maxCost == null) {
            return productRepository.findAllByCostGreaterThan(minCost);
        }
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
