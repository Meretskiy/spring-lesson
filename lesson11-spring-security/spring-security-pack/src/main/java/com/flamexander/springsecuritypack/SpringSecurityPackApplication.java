package com.flamexander.springsecuritypack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityPackApplication {
	// Для запуска одной из версий безопасности, в Edit Configurations > VM Options
	// указываете: -Dspring.profiles.active=Имя_профиля
	//
	// Варианты профилей: dao, inmemory, jdbc, simplest
	// Spring автоматически подтянет нужный конфиг класс, и если требуется sql скрипты через flyway

	// --
	// Создайте новый проект с spring-boot + spring-security (все на RestController'ах делаем)
	// Форму входа используем стандартную
	// Подключите туда DaoAuthentication
	// Для каждого пользователя сделайте сущность Score в которой
	// указывается некий балл пользователя
	// GET .../app/score/inc - увеличивает балл текущего пользователя
	// GET .../app/score/dec - уменьшает балл текущего пользователя
	// GET .../app/score/get/current - показывает балл вошедшего пользователя
	// GET .../app/score/get/{id} - показывает балл пользователя с указанным id (доступно
	// только для залогиненных пользователей)	

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPackApplication.class, args);
	}
}
