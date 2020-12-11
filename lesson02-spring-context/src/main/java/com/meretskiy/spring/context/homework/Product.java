package com.meretskiy.spring.context.homework;

public class Product {
    private Long id;
    private String productName;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(Product product) {
    }

    public Product(Long id, String productName, int price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("[id = %d, product's name = %s, price = %d RUR]", id, productName, price);
    }
}
