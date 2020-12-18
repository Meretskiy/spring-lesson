package com.meretskiy.hibernate.homework.products;

public class ProductApp {
    public static void main(String[] args) {

        ProductDao pD = new ProductDao();
        pD.init();
        System.out.println(pD.findAll());
        System.out.println(pD.findById(2L));
        pD.deleteById(1L);
        System.out.println(pD.findAll());
        System.out.println(pD.saveOrUpdate(new Product("Elephant", 500)));
        System.out.println(pD.findAll());
        pD.shutdown();
    }
}
