package com.geekbrains.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDAO userDAO = context.getBean("userDAO", UserDAO.class);

//        System.out.println(userDAO.getX());
//        userDAO.addUser();
//        userDAO.addUser();
//        userDAO.addUser();
//        userDAO.deleteUser();

        System.out.println(userDAO.getAllUsers());

//        System.out.println(userDAO instanceof UserDAO);
//        userDAO.setX(10);
//        userDAO.setX(100);
//        userDAO.setX(1000);

        context.close();
    }
}
