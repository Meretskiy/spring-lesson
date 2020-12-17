package com.meretskiy.homeworkspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. Создать класс Товар (Product), с полями id , title , cost ;
 * 2. Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся
 * товары).
 * 3. Репозиторий должен уметь выдавать список всех товаров и товар по id;
 * 4. Сделать форму для добавления товара в репозиторий и логику работы этой формы;
 * 5. Сделать страницу, на которой отображаются все товары из репозитория.
 */

@SpringBootApplication
public class HomeworkSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkSpringBootApplication.class, args);
	}

}
