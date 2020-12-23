package com.meretskiy.hibernate.homework;

import com.meretskiy.hibernate.homework.dao.BuyerDao;
import com.meretskiy.hibernate.homework.dao.ProductDao;
import com.meretskiy.hibernate.homework.model.Buyer;
import com.meretskiy.hibernate.homework.model.Product;
import com.meretskiy.hibernate.homework.services.OrderService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        testProductDao(context);
        testBuyerDao(context);
        testOrderService(context);
    }

    private static void testOrderService(AnnotationConfigApplicationContext context) {
        OrderService orderService = context.getBean("orderService", OrderService.class);
        System.out.println(orderService.findOrdersByBuyerId(2L));
        System.out.println(orderService.findBuyersByProductId(3L));
        orderService.addOrder(5L, 1L, 1L);
        System.out.println(orderService.findAll());
    }

    private static void testBuyerDao(AnnotationConfigApplicationContext context) {
        BuyerDao buyerDao = context.getBean("buyerDao", BuyerDao.class);
        System.out.println(buyerDao.findById(2L));
        System.out.println(buyerDao.saveOrUpdate(new Buyer("Sam")));
        System.out.println(buyerDao.findAll());
        buyerDao.deleteById(1L);
        System.out.println(buyerDao.findAll());
    }

    private static void testProductDao(AnnotationConfigApplicationContext context) {
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        System.out.println(productDao.findById(1L));
        System.out.println(productDao.saveOrUpdate(new Product("Elephant", 500L)));
        System.out.println(productDao.findAll());
        productDao.deleteById(2L);
    }
}
