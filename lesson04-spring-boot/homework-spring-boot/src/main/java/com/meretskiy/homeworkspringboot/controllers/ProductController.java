package com.meretskiy.homeworkspringboot.controllers;

import com.meretskiy.homeworkspringboot.model.Product;
import com.meretskiy.homeworkspringboot.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
            @RequestParam(required = false, name = "min_cost") Integer minCost,
            @RequestParam(required = false, name = "max_cost") Integer maxCost
            ) {
        model.addAttribute("products", productService.findAllProduct(minCost, maxCost));
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @PostMapping("/add")
    public String addProducts(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }
}
