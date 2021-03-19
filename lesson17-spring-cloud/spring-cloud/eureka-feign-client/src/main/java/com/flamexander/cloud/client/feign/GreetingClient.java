package com.flamexander.cloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    Есть некоторый клиент eureka-client, который может обрабатывать запросы @GetMapping("/greeting") и
    @GetMapping("/parametrized/{id}")
    Клиенту eureka с именем eureka-client будет отправляться get запрос на адрес /greeting если будет вызван метод String greeting();
    или запрос на адрес /parametrized/{id} если будет вызван метод String parametrized(@PathVariable(value = "id") String id);
 */
@FeignClient("eureka-client")
public interface GreetingClient {
    @GetMapping("/greeting")
    String greeting();

    @GetMapping("/parametrized/{id}")
    String parametrized(@PathVariable(value = "id") String id);
}
