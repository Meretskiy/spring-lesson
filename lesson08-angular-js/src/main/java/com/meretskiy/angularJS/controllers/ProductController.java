package com.meretskiy.angularJS.controllers;

import com.meretskiy.angularJS.model.Product;
import com.meretskiy.angularJS.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//всегда будем возвращить на фронт json, а не странички
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice
    ) {
        //пока так
        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productService.findAllByPrise(minPrice, maxPrice);
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable Long id) {
        //делаем get, что-бы достать именно продукт, а не возвращить optional на фронт
        return productService.findProductById(id).get();
    }

    @PostMapping
    //знаем что с фронта придет json product по этому можем прописать RequestBody
    public Product saveNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }
}
