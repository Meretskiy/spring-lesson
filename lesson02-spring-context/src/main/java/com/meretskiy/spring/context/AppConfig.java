package com.meretskiy.spring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//указыввем, что класс конфигурация для спринг контекста
@Configuration
//указание спрингу где искать объекты которые мы хотим создавать
@ComponentScan("com.meretskiy.spring.context")
public class AppConfig {
    @Autowired
    private StudentService studentService;

    @Bean
    public Box box() {
        Box box = new Box();
        box.setStudentService(studentService);
        box.setSize(5);
        box.setColor("White");
        return box;
    }

    //можно создать два бина одного и того же типа
    @Bean
    public Box box2() {
        Box box = new Box();
        box.setStudentService(studentService);
        box.setSize(5);
        box.setColor("Red");
        return box;
    }

    //можно сделать внешнее создание чужого класса и превратить его в бин
//    @Bean
//    public Connection connection() {
//        try {
//            Class.forName("org.sqlite.jdbc");
//            Connection connection = DriverManager.getConnection("jadbc:sqlite:123.db");
//            return connection;
//        } catch (Exception e) {
//            throw new RuntimeException("The End!!!");
//        }
//    }
}
