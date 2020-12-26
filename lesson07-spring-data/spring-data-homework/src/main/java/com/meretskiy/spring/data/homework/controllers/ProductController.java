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
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/search_by_min_cost")
    public List<Product> searchByMinCost(@RequestParam(name = "min_cost") Integer minCost) {
        return productRepository.findAllByCostGreaterThan(minCost);
    }

    @GetMapping("/search_by_max_cost")
    public List<Product> searchByMaxCost(@RequestParam(name = "max_cost") Integer maxCost) {
        return productRepository.findAllByCostIsLessThan(maxCost);
    }

    @GetMapping("/search_by_min_and_max_cost")
    public List<Product> searchByBetweenCost(@RequestParam(name = "min_cost") Integer minCost,
                                             @RequestParam(name = "max_cost") Integer maxCost) {
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
