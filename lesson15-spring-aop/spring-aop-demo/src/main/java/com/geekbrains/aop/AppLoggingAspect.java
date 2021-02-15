package com.geekbrains.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Аспект класс, с помощью которого мы будем прокидывать наш функционал через весь код.
 */
@Aspect //делает класс аспектом
@Component
public class AppLoggingAspect {

    /**
     * Пример посторения pointcut expression
     */
// "execution(modifier-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(args-pattern)
// throws-pattern?)"
// execution([модификатор_метода(public, *)?] [тип_возврата] [класс?] [имя_метода]([аргументы]) [исключения?]

    /**
     * перед выполнением метода, который имеет тип void, лежит в  классе UserDAO и называется addUser
     * мы хотим выполнить beforeAddUserInUserDAOClass
     */
//    @Before("execution(public void com.geekbrains.aop.UserDAO.addUser())") // pointcut expression
//    public void beforeAddUserInUserDAOClass() {
//        System.out.println("AOP: Поймали добавление пользователя");
//    }

    /**
     * в названии метода можно указывать wildcard "*" (отработает для всех методов заканчивающихся на User)
     */
//    @Before("execution(public void com.geekbrains.aop.UserDAO.*User())") // pointcut expression
//    public void beforeUserModifyInUserDAOClass() {
//        System.out.println("AOP: работа с пользователем в UserDAO");
//    }

    /**
     * вклиниваемся перед любым void методом который есть в классе UserDAO
     */
//    @Before("execution(public void com.geekbrains.aop.UserDAO.*())") // pointcut expression
//    public void beforeAnyMethodWithoutArgsInUserDAOClass() {
//        System.out.println("AOP: любой void метод без аргументов из UserDAO");
//    }

    /**
     * вклиниваемся перед любым методом который есть в классе UserDAO
     */
//    @Before("execution(public * com.geekbrains.aop.UserDAO.*())") // pointcut expression
//    public void beforeAnyMethodWithoutArgsInUserDAOClass() {
//        System.out.println("AOP: любой метод без аргументов из UserDAO");
//    }

    /**
     * вклинивается перед любым void методом из пакета с любым количеством аргументов
     */
//    @Before("execution(public void com.geekbrains.aop.*.*(..))") // pointcut expression
//    public void beforeAnyMethodInUserDAOClass() {
//        System.out.println("AOP: любой void метод с любым количеством аргументов");
//    }

    /**
     * Получение доп информации о вызове метода.
     * Если требуется знать о методе какую либо информацию(название, какие аргументы передали и тд) в который мы
     * вклиниваемся можно заинжектить в метод JoinPoint и из него брать methodSignature, а у нее уже узнать все,
     * что нас интересует про метод.
     */
//    @Before("execution(public void com.geekbrains.aop.UserDAO.*(..))") // pointcut expression
//    public void beforeAnyMethodInUserDAOClassWithDetails(JoinPoint joinPoint) {
//        //выдергиваем из joinPoint метод methodSignature
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        System.out.println("В UserDAO был вызван метод: " + methodSignature);
//        //выдергиваем аргументы
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            System.out.println("Аргументы:");
//            for (Object o : args) {
//                System.out.println(o);
//            }
//        }
//    }

    /**
     * Вклиниваемся после возврата.
     * Можем добавить код после того, как метод getAllUsers вернет результат
     */
//    @AfterReturning(
//            pointcut = "execution(public * com.geekbrains.aop.UserDAO.getAllUsers(..))",
//            returning = "result")
//    public void afterGetBobInfo(JoinPoint joinPoint, List<String> result) {
//        result.set(0, "Donald Duck");
//    }

    /**
     * Вклиниваемся после броска исключений.
     */
//    @AfterThrowing(
//            pointcut = "execution(public * com.geekbrains.aop.UserDAO.*(..))",
//            throwing = "exc")
//    public void afterThrowing(JoinPoint joinPoint, Throwable exc) {
//        System.out.println(exc); // logging
//    }

    /**
     * вклиниваемся после любого метода класса UserDAO с любым количеством аргументов
     */
//    @After("execution(public * com.geekbrains.aop.UserDAO.*(..))")
//    public void afterMethod() {
//        System.out.println("After");
//    }

    /**
     * Встраиваемся вокруг метода. Можем добавлять код до начала метода и после завершения работы метода.
     * Для этого в наш метод инжектим ProceedingJoinPoint, который не только предоставляет данные о перехваченном
     * методе, но и позволяет им поуправлять.
     */
//    @Around("execution(public * com.geekbrains.aop.UserDAO.*(..))")
//    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        //действия перед выполнением метода
//        System.out.println("start profiling");
//        long begin = System.currentTimeMillis();
//
//        //выполнение перехваченного метода
//        Object out = proceedingJoinPoint.proceed();
//
//        //действия после выполнения метода
//        long end = System.currentTimeMillis();
//        long duration = end - begin;
//        System.out.println((MethodSignature) proceedingJoinPoint.getSignature() + " duration: " + duration);
//        System.out.println("end profiling");
//
//        //возврат результата выполнения метода(даже если он void все равно нужно вернуть!)
//        return out;
//    }
}
