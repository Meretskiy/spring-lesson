package com.meretskiy.hibernate.homework.products;

import com.meretskiy.hibernate.homework.PrepareDataApp;

public class ProductApp {
    public static void main(String[] args) {

        ProductDao pD = new ProductDao();
        PrepareDataApp.forcePrepareData();
        System.out.println(pD.findAll());
        System.out.println(pD.findById(2L));
        pD.deleteById(1L);
        System.out.println(pD.findAll());
        System.out.println(pD.saveOrUpdate(new Product("Elephant", 500)));
        System.out.println(pD.findAll());
        GlobalSessionFactory.close();
    }
}
