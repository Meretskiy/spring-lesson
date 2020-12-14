package com.meretskiy.homeworkspringboot.repositories;

import com.meretskiy.homeworkspringboot.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepository {
    private List<Product> products;



    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Bread", 75),
                new Product(2L, "Milk", 90),
                new Product(3L, "Cheese", 290),
                new Product(4L, "Bear", 180),
                new Product(5L, "Meat", 540)
        ));
    }

    public List<Product> findAllProduct() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void deleteProductById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
