package com.meretskiy.spring.context.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Есть класс Product (id, название, цена).
 * Товары хранятся в бине ProductRepository, в виде List<Product>, при старте в него нужно добавить 5 любых товаров.
 * ProductRepository позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно добавлять
 * и удалять товары по id.
 * Написать консольное приложение, позволяющее управлять корзиной.
 * При каждом запросе корзины из контекста, должна создаваться новая корзина.
 */

public class MainApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Cart cart = context.getBean("cart", Cart.class);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добавить товар в корзину - /add, удалить товар из крорзины - /delete, купить - /buy");
        while (true) {
            String str = br.readLine();
            if (str.equals("/add")) {
                System.out.println("Введите ID товара");
                Long id = Long.parseLong(br.readLine());
                cart.addProductInCart(id);
            }
            if (str.equals("/delete")) {
                System.out.println("Введите ID товара");
                Long id = Long.parseLong(br.readLine());
                cart.deleteProductInCart(id);
            }
            if (str.equals("/show")) {
                cart.showProductInCart();
            }
            if (str.equals("/buy")) {
                System.out.println("Поздравляем с покупкой!");
                return;
            }
        }
    }
}
