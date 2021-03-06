package com.meretskiy.cloud.project.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductMicroserviceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductMicroserviceApp.class, args);
    }
}
