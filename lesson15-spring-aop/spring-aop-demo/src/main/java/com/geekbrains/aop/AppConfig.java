package com.geekbrains.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//включаем поддержку AOP в spring
@EnableAspectJAutoProxy
@ComponentScan("com.geekbrains.aop")
public class AppConfig {
}
