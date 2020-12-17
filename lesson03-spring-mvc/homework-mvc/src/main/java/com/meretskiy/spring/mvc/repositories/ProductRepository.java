package com.meretskiy.spring.mvc.repositories;

import com.meretskiy.spring.mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProductRepository {

    List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Apple", 99));
        productList.add(new Product(2L, "Bread", 75));
        productList.add(new Product(3L, "Beer", 179));
        productList.add(new Product(4L, "Cheese", 660));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(productList);
    }

    public void save(Product product) {
        productList.add(product);
    }

    public Product getById(Long id) {
        if (productList.size() != 0) {
            for (Product product : productList) {
                if (product.getId() == id) {
                    return product;
                }
            }
        }
        throw new NoSuchElementException("Продукт с таким ID не найден");
    }

    public void deleteById(Long id) {
        productList.removeIf(b -> b.getId().equals(id));
    }
}
