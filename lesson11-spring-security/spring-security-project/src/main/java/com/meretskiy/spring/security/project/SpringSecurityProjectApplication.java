package com.meretskiy.spring.security.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * // Создайте новый проект с spring-boot + spring-security (все на RestController'ах делаем)
 * 	// Форму входа используем стандартную
 * 	// Подключите туда DaoAuthentication
 * 	// Для каждого пользователя сделайте сущность Score в которой
 * 	// указывается некий балл пользователя
 * 	// GET .../app/score/inc - увеличивает балл текущего пользователя
 * 	// GET .../app/score/dec - уменьшает балл текущего пользователя
 * 	// GET .../app/score/get/current - показывает балл вошедшего пользователя
 * 	// GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступно
 * 	// только для залогиненных пользователей)
 */
@SpringBootApplication
public class SpringSecurityProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityProjectApplication.class, args);
    }

}
