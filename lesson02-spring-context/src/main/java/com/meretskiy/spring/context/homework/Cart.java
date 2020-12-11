package com.meretskiy.spring.context.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@Scope(scopeName = "prototype")
public class Cart {

    ProductRepository productRepository;
    List<Product> productCart;

    public void setProductCart(List<Product> productCart) {
        this.productCart = productCart;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public List<Product> getProductCart() {
        return productCart;
    }

    @Autowired
    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void init() {
        productCart = new ArrayList<>();
    }

    public void addProductInCart(Long id) {
        Product p = productRepository.getProductById(id);
        if (p == null) {
            throw new NoSuchElementException("Продукт с таким ID не найден в базе..");
        }
        productCart.add(p);
    }

    public void deleteProductInCart(Long id) {
        Product p = productRepository.getProductById(id);
        if (p == null) {
            throw new NoSuchElementException("Продукт с таким ID не найден в базе..");
        }
        productCart.remove(p);
    }

    public void showProductInCart() {
        for (Product p : productCart) {
            System.out.println(p);
        }
    }


}
